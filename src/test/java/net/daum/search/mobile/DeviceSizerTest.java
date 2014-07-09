package net.daum.search.mobile;

import org.junit.Assert;
import org.junit.Test;

public class DeviceSizerTest {

    @Test
    public void parse_UA_first_time() {
        // iPhone
        String ua = "Mozilla/5.0 (iPhone; CPU iPhone OS 5_1_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9B206 Safari/7534.48.3";

        DeviceSizer parser = new DeviceSizer();
        Device device = parser.parse(ua);

        Assert.assertEquals(320, device.getPortraitWidth());
        Assert.assertEquals(320/4, device.getPortraitSize());
        Assert.assertEquals(568, device.getLandscapeWidth());
        Assert.assertEquals(568/4, device.getLandscapeSize());
    }

    @Test
    public void test_A_level_mobile_devices() {
        // Galaxy S3
        String galaxy_s3_ua_string = "Mozilla/5.0 (Linux; U; Android 4.0.4; en-gb; GT-I9300 Build/IMM76D) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
        Device device = new DeviceSizer().parse(galaxy_s3_ua_string);
        Assert.assertEquals(720/8, device.getSummarySize());
        Assert.assertEquals(1280/8 - 720/8, device.getSummaryExtSize());

        // Galaxy S2
        String galaxy_s2 = "Mozilla/5.0 (Linux; U; Android 2.3; xx-xx; GT-I9100 Build/GRH78) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
        device = new DeviceSizer().parse(galaxy_s2);
        Assert.assertEquals("480x800x6", device.getViewportSize());

        // Galaxy S
        String galaxy_s = "Mozilla/5.0 (Linux; U; Android 2.1; xx-xx; GT-I9000 Build/ECLAIR) AppleWebKit/525.10+ (KHTML, like Gecko) Version/3.0.4 Mobile Safari/523.12.2";
        device = new DeviceSizer().parse(galaxy_s);
        Assert.assertEquals("480x800x6", device.getViewportSize());

        // SKY Vega Racer (SKT)
        String ua = "Mozilla/5.0 (Linux; U; Android 2.3; xx-xx; IM-A760S Build/GINGERBREAD) AppleWebKit/525.10 (KHTML, like Gecko) Version/3.0.4 Mobile Safari/523.12.2";
        device = new DeviceSizer().parse(ua);
        Assert.assertEquals("480x800x6", device.getViewportSize());

        // SKY Vega Racer (LG U+)
        ua = "Mozilla/5.0 (Linux; U; Android 2.3; xx-xx; IM-A780L Build/GINGERBREAD) AppleWebKit/525.10 (KHTML, like Gecko) Version/3.0.4 Mobile Safari/523.12.2";
        device = new DeviceSizer().parse(ua);
        Assert.assertEquals("480x800x6", device.getViewportSize());

        // SKY Vega Racer (KT)
        ua = "Mozilla/5.0 (Linux; U; Android 2.3; xx-xx; IM-A770K Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
        device = new DeviceSizer().parse(ua);
        Assert.assertEquals("480x800x6", device.getViewportSize());

        // Optimus LTE
        ua = "Mozilla/5.0 (Linux; U; Android 2.3; xx-xx; LG-P930 Build/GRJ90) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
        device = new DeviceSizer().parse(ua);
        Assert.assertEquals("720x1280x8", device.getViewportSize());

        // Optimus LTE III
        ua = "Mozilla/5.0 (Linux; U; Android 4.1; xx-xx; LG-F260S Build/JZO54K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
        device = new DeviceSizer().parse(ua);
        Assert.assertEquals("720x1280x8", device.getViewportSize());

        // Optimus LTE
        ua = "Mozilla/5.0 (Linux; U; Android 2.3; xx-xx; LG-LU6200 Build/GRJ90) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
        device = new DeviceSizer().parse(ua);
        Assert.assertEquals("720x1280x8", device.getViewportSize());

        // Optimus LTE
        ua = "Mozilla/5.0 (Linux; U; Android 2.3; xx-xx; LG-P936/V10c Build/GRJ90) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
        device = new DeviceSizer().parse(ua);
        Assert.assertEquals("720x1280x8", device.getViewportSize());

        // Optimus Vu
        ua = "Mozilla/5.0 (Linux; U; Android 4.0; xx-xx; LG-P895 Build/IMM76D) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
        device = new DeviceSizer().parse(ua);
        Assert.assertEquals("768x1024x8", device.getViewportSize());

        // Optimus Vu
        ua = "Mozilla/5.0 (Linux; U; Android 2.3; xx-xx; LG-F100L Build/GRK39F) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
        device = new DeviceSizer().parse(ua);
        Assert.assertEquals("768x1024x8", device.getViewportSize());

        // Optimus Vu II
        ua = "Mozilla/5.0 (Linux; U; Android 4.0; xx-xx; LG-F200K Build/IMM76L) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
        device = new DeviceSizer().parse(ua);
        Assert.assertEquals("768x1024x8", device.getViewportSize());

        // Galaxy S II
        ua = "Mozilla/5.0 (Linux; U; Android 4.0; xx-xx; SAMSUNG-SGH-I777 Build/IML74K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
        device = new DeviceSizer().parse(ua);
        Assert.assertEquals("480x800x6", device.getViewportSize());

        // Optimus One
        ua = "Mozilla/5.0 (Linux; U; Android 2.2; xx-xx; LG-P500 Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1 MMS/LG-Android-MMS-V1.0/1.2";
        device = new DeviceSizer().parse(ua);
        Assert.assertEquals("320x480", device.getViewportSize());
        ua = "Mozilla/5.0 (Linux; U; Android 2.2; xx-xx; LG-P504 Build/FRG83G) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
        device = new DeviceSizer().parse(ua);
        Assert.assertEquals("320x480", device.getViewportSize());
        ua = "Mozilla/5.0 (Linux; U; Android 2.2; xx-xx; LG-LU3700 Build/FRF91) AppleWebKit/525.10+ (KHTML, like Gecko) Version/3.0.4 Mobile Safari/523.12.2";
        device = new DeviceSizer().parse(ua);
        Assert.assertEquals("320x480", device.getViewportSize());
        ua = "Mozilla/5.0 (Linux; U; Android 2.2; xx-xx; LG-SU370 Build/FRF91) AppleWebKit/525.10+ (KHTML, like Gecko) Version/3.0.4 Mobile Safari/523.12.2";
        device = new DeviceSizer().parse(ua);
        Assert.assertEquals("320x480", device.getViewportSize());
        ua = "Mozilla/5.0 (Linux; U; Android 2.2; xx-xx; LG-P500h Build/FRG83) AppleWebKit/525.10+ (KHTML, like Gecko) Version/3.0.4 Mobile Safari/523.12.2";
        device = new DeviceSizer().parse(ua);
        Assert.assertEquals("320x480", device.getViewportSize());

        // Galaxy Note 3
        ua = "Mozilla/5.0 (Linux; U; Android 4.3; xx-xx; SM-N900T Build/JSS15J) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
        device = new DeviceSizer().parse(ua);
        Assert.assertEquals("1080x1920x8", device.getViewportSize());

        // Galaxy Note 2
        ua = "Mozilla/5.0 (Linux; U; Android 4.1; xx-xx; GT-N7100 Build/JRO03C) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
        device = new DeviceSizer().parse(ua);
        Assert.assertEquals("720x1280", device.getViewportSize());

        // Galaxy Nexus
        ua = "Mozilla/5.0 (Linux; U; Android 4.0; xx-xx; Galaxy Nexus Build/ITL41F) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
        device = new DeviceSizer().parse(ua);
        Assert.assertEquals("720x1280x6", device.getViewportSize());

        // Google Nexus S
        ua = "Mozilla/5.0 (Linux; U; Android 2.3; xx-xx; Nexus S Build/GRH41B) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
        device = new DeviceSizer().parse(ua);
        Assert.assertEquals("480x800", device.getViewportSize());

        // Other
        String ua_problem = "afajfajflajflaj";
        device = new DeviceSizer().parse(ua_problem);
        Assert.assertEquals(320/4, device.getSummarySize());
        Assert.assertEquals(480/4 - 320/4, device.getSummaryExtSize());
    }

    @Test
    public void test_Summary_Size() {
        // Galaxy S3
        String galaxy_s3_ua_string = "Mozilla/5.0 (Linux; U; Android 4.0.4; en-gb; GT-I9300 Build/IMM76D) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
        Device device = new DeviceSizer().parse(galaxy_s3_ua_string);
        // 720x1280x8
        Assert.assertEquals(54, device.getTitleSummarySize());
        Assert.assertEquals(42, device.getTitleSummaryExtSize());
        Assert.assertEquals(81, device.getContentSummarySize());
        Assert.assertEquals(63, device.getContentSummaryExtSize());
    }
}