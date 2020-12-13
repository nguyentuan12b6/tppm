package com.example.example10;

public class Item_labels {
    private int color;
    private String labels_name;

    public Item_labels(int color, String labels_name) {
        this.color = color;
        this.labels_name = labels_name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getLabels_name() {
        return labels_name;
    }

    public void setLabels_name(String labels_name) {
        this.labels_name = labels_name;
    }
}
