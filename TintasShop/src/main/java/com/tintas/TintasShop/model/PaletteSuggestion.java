package com.tintas.TintasShop.model;

import java.util.List;

public class PaletteSuggestion {
    private String title;
    private List<String> hexCodes;
    private List<String> colorNames;
    private String primary;
    private String accent;
    private String trim;
    private String explanation;

    public PaletteSuggestion() {}

    public PaletteSuggestion(String title, List<String> hexCodes, List<String> colorNames, String primary, String accent, String trim, String explanation) {
        this.title = title;
        this.hexCodes = hexCodes;
        this.colorNames = colorNames;
        this.primary = primary;
        this.accent = accent;
        this.trim = trim;
        this.explanation = explanation;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<String> getHexCodes() { return hexCodes; }
    public void setHexCodes(List<String> hexCodes) { this.hexCodes = hexCodes; }

    public List<String> getColorNames() { return colorNames; }
    public void setColorNames(List<String> colorNames) { this.colorNames = colorNames; }

    public String getPrimary() { return primary; }
    public void setPrimary(String primary) { this.primary = primary; }

    public String getAccent() { return accent; }
    public void setAccent(String accent) { this.accent = accent; }

    public String getTrim() { return trim; }
    public void setTrim(String trim) { this.trim = trim; }

    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }
}
