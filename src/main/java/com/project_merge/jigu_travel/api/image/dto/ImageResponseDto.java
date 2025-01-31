package com.project_merge.jigu_travel.api.image.dto;

import java.util.List;

public class ImageResponseDto {
    private String code;
    private String message;
    private List<Detection> data;

    public ImageResponseDto() {}

    public ImageResponseDto(String code, String message, List<Detection> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

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

    public static class Detection {
        private String className;
        private double confidence;
        private int x1;
        private int y1;
        private int x2;
        private int y2;

        public Detection() {}

        public Detection(String className, double confidence, int x1, int y1, int x2, int y2) {
            this.className = className;
            this.confidence = confidence;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

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
}
