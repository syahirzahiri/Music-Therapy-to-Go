package com.muzammil.musictherapytogo;

public class InsightData {

    private String timestamp;
    private String result;

    public InsightData() {
    }

    public InsightData(String timestamp, String result) {
        this.timestamp = timestamp;
        this.result = result;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
