package net.daum.search.mobile;

import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeviceViewport {

    private final String OTHER = "other";
    private final String OTHER_VIEWPORT_SIZE = "720x1280x329";
    private String deviceName;

    // regex patterns
    List<DevicePattern> patterns = new ArrayList<DevicePattern>();
    private void addPattern(String regex, String deviceReplacement) {
        patterns.add(new DevicePattern(Pattern.compile(regex), deviceReplacement));
    }

    public DeviceViewport() {
        /*
        #########
        # Android General Device Matching (far from perfect)
        #########
        */
        addPattern("Android[\\- ][\\d]+\\.[\\d]+; [A-Za-z]{2}\\-[A-Za-z]{0,2}; WOWMobile (.+) Build", null);
        addPattern("Android[\\- ][\\d]+\\.[\\d]+\\-update1; [A-Za-z]{2}\\-[A-Za-z]{0,2}; (.+) Build", null);
        addPattern("Android[\\- ][\\d]+\\.[\\d]+\\.[\\d]+; [A-Za-z]{2}\\-[A-Za-z]{0,2}; (.+) Build", null);
        addPattern("Android[\\- ][\\d]+\\.[\\d]+\\.[\\d]+;[A-Za-z]{2}\\-[A-Za-z]{0,2};(.+) Build", null);
        addPattern("Android[\\- ][\\d]+\\.[\\d]+; [A-Za-z]{2}\\-[A-Za-z]{0,2}; (.+) Build", null);
        addPattern("Android[\\- ][\\d]+\\.[\\d]+\\.[\\d]+; (.+) Build", null);
        addPattern("Android[\\- ][\\d]+\\.[\\d]+; (.+) Build", null);

        /*
        ##########
        # complete but probably catches spoofs
        # iSTUFF
        ##########
        # ipad and ipod must be parsed before iphone
        # cannot determine specific device type from ua string. (3g, 3gs, 4, etc)
        */
        addPattern("(iPad) Simulator;", null);
        addPattern("(iPad);", null);
        addPattern("(iPhone) Simulator;", null);
        addPattern("(iPhone);", null);
    }

    // is abbr from StringUtils.equals(deviceName, xx)
    private boolean m(String expectedDeviceName) {
        return StringUtils.equals(this.deviceName, expectedDeviceName);
    }

    // make `Device` that contains parsed results
    private Device makeDevice(String deviceName, String userAgent) {
        String viewportSize = OTHER_VIEWPORT_SIZE;

        this.deviceName = deviceName;
        if (m("iPhone")) {                      // 4 inch
            viewportSize = "320x568x163";

        } else if (m("GT-I9500")) {             // Galaxy S4
            viewportSize = "1080x1920x441";

        } else if (m("GT-I9300") ||             // 4.8 inch (Galaxy S3)
                m("Galaxy Nexus") ||            // Galaxy Nexus
                m("LG-P930") ||                 // Optimus LTE
                m("LG-F260S") ||                // Optimus LTE III
                m("LG-LU6200") ||               // Optimus LTE
                m("LG-P936/V10c")) {            // Optimus LTE
            viewportSize = "720x1280x306";

        } else if (m("GT-I9100") ||             // Galaxy S2
                m("SAMSUNG-SGH-I777") ||        // Galaxy S II
                m("GT-I9000") ||                // Galaxy S
                m("IM-A760S") ||                // SKY Vega Racer (SKT)
                m("IM-A780L") ||                // SKY Vega Racer (LG U+)
                m("IM-A770K")) {                // SKY Vega Racer (KT)
            viewportSize = "480x800x217";

        } else if (m("LG-P895") ||              // Optimus Vu
                m("LG-F100L") ||                // Optimus Vu
                m("LG-F200K")) {                // Optimus Vu II
            viewportSize = "768x1024x256";

        } else if (m("SM-N900T")) {             // Galaxy Note 3
            viewportSize = "1080x1920x386";

        } else if (m("GT-N7100")) {             // Galaxy Note 2
            viewportSize = "720x1280x256";

        } else if (m("Nexus S")) {              // Google Nexus S
            viewportSize = "480x800x233";

        } else if (m("iPad")) {                 // 12 inch
            viewportSize = "768x1024x132";

        } else {
            viewportSize = OTHER_VIEWPORT_SIZE;
        }

        Device device = new Device();
        device.setDeviceName(deviceName);
        device.setUserAgent(userAgent);
        device.setViewportSize(viewportSize);       // viewport raw data 
                                                    // eg. 768x1024x300

        String[] size = viewportSize.split("x");    // eg. size[0] = 768, size[1] = 1024, size[2] = 300
        device.setPortraitWidth(Integer.parseInt(size[0]));
        device.setLandscapeWidth(Integer.parseInt(size[1]));
        device.setPixelsPerInch(Integer.parseInt(size[2]));

        return device;
    }

    // match regex patterns with device's user-agent.
    public Device parse(String agentString) {
        String deviceName = null;
        if (agentString != null) {
            for (DevicePattern p : this.patterns) {
                if ((deviceName = p.match(agentString)) != null) {
                    break;
                }
            }
        }
        if (deviceName == null) deviceName = OTHER;

        return makeDevice(deviceName, agentString);
    }

//  --

    protected static class DevicePattern {
        private final Pattern pattern;
        private final String deviceReplacement;

        public DevicePattern(Pattern pattern, String deviceReplacement) {
            this.pattern = pattern;
            this.deviceReplacement = deviceReplacement;
        }

        public String match(String agentString) {
            Matcher matcher = pattern.matcher(agentString);

            if (!matcher.find()) {
                return null;
            }

            String deviceName = null;
            if (deviceReplacement != null) {
                if (deviceReplacement.contains("$1") && matcher.groupCount() >= 1 && matcher.group(1) != null) {
                    deviceName = deviceReplacement.replaceFirst("\\$1", Matcher.quoteReplacement(matcher.group(1)));
                } else {
                    deviceName = deviceReplacement;
                }
            } else if (matcher.groupCount() >= 1) {
                deviceName = matcher.group(1);
            }
            return deviceName;
        }
    }
}
