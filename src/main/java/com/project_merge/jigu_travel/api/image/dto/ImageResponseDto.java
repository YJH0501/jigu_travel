package com.project_merge.jigu_travel.api.image.dto;

import java.util.List;

public class ImageResponseDto {
    private String code;
    private String message; // JSON의 "message" 필드와 매핑
    private List<Detection> data; // JSON의 "data" 필드와 매핑

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Detection> getData() {
        return data;
    }

    public void setData(List<Detection> data) {
        this.data = data;
    }
}

class Detection {
    private String className;
    private double confidence;
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    // Getters and Setters
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
}
