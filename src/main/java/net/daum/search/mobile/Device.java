package net.daum.search.mobile;

import lombok.Data;

@Data
public class Device {
    private int pixelsPerInch;

    private String userAgent;
    private String deviceName;

    private String viewportSize;
    private int portraitWidth;      // vertical
    private int landscapeWidth;     // horizontal
}