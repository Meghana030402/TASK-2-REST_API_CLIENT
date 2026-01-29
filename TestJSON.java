package com.codtech.weather;

import org.json.JSONObject;

public class TestJSON {
    public static void main(String[] args) {
        JSONObject obj = new JSONObject();
        obj.put("task", "CODTECH");
        System.out.println(obj);
    }
}
