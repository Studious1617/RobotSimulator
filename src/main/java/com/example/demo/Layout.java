package com.example.demo;

public class Layout {
    private String emailAddress;
    private String layoutName;
    private String[] layoutData;
    private String directionValue;
    private int layoutID;

    public Layout(int layoutID, String layoutName, String[] layoutData, String direction, String email) {
        this.layoutID = layoutID;
        this.layoutName = layoutName;
        this.layoutData = layoutData;
        this.directionValue = direction;
        this.emailAddress = email;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLayoutName() {
        return layoutName;
    }
    public void setLayoutName(String layoutName) {
        this.layoutName = layoutName;
    }

    public String[] getLayoutData() {
        return layoutData;
    }
    public void setLayoutData(String[] layoutData) {
        this.layoutData = layoutData;
    }

    public String getDirectionValue() {
        return directionValue;
    }
    public void setDirectionValue(String directionValue) {
        this.directionValue = directionValue;
    }

    public int getLayoutID() {
        return layoutID;
    }
    public void setLayoutID(int layoutID) {
        this.layoutID = layoutID;
    }
}
