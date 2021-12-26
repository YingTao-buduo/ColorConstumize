package com.yt.colorconstumize.color;

public enum ColorKey {
    TARGET_VIEW_1(0, 51, 68),
    TARGET_VIEW_2(255, 255, 255);

    private int r;
    private int g;
    private int b;

    ColorKey(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}