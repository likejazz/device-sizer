package net.daum.search.mobile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Device {
    private final double TITLE_SUMMARY_RATIO = 0.6;
    private final double CONTENT_SUMMARY_RATIO = 0.9;

    @Setter private int pixelsPerChar = 4;

    @Getter @Setter private String userAgent;
    @Getter @Setter private String deviceName;

    @Getter @Setter private String viewportSize;
    @Getter @Setter private int portraitWidth;      // vertical
    @Getter @Setter private int landscapeWidth;     // horizontal

    public int getPortraitSize() {
        return this.portraitWidth / this.pixelsPerChar;
    }
    public int getLandscapeSize() {
        return this.landscapeWidth / this.pixelsPerChar;
    }

    public int getSummarySize() {
        return getPortraitSize();
    }
    public int getSummaryExtSize() {
        return getLandscapeSize() - getPortraitSize();
    }

    public int getTitleSummarySize() {
        return (int) (getSummarySize() * TITLE_SUMMARY_RATIO);
    }
    public int getTitleSummaryExtSize() {
        return (int) (getSummaryExtSize() * TITLE_SUMMARY_RATIO);
    }
    public int getContentSummarySize() {
        return (int) (getSummarySize() * CONTENT_SUMMARY_RATIO);
    }
    public int getContentSummaryExtSize() {
        return (int) (getSummaryExtSize() * CONTENT_SUMMARY_RATIO);
    }
}