package com.example.demo;

public class Layout {
    private String layoutEmail;
    private String layoutName;
    private String[] layoutData;
    private String layoutDirection;
    private int layoutID;

    public Layout(int layoutID, String layoutName, String[] layoutData, String direction, String email) {
        this.layoutID = layoutID;
        this.layoutName = layoutName;
        this.layoutData = layoutData;
        this.layoutDirection = direction;
        this.layoutEmail = email;
    }

    public int getLayoutID() {
        return layoutID;
    }
    public void setLayoutID(int layoutID) {
        this.layoutID = layoutID;
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

    public String getLayoutDirection() {
        return layoutDirection;
    }
    public void setLayoutDirection(String layoutDirection) {
        this.layoutDirection = layoutDirection;
    }

    public String getLayoutEmail() {
        return layoutEmail;
    }
    public void setLayoutEmail(String layoutEmail) {
        this.layoutEmail = layoutEmail;
    }
}
