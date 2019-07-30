package com.yogo.analytics.component;

import java.util.HashMap;
import java.util.Map;

public class CategoryBuffer {
    private final Map<String,String>buffer=new HashMap<>();

    public Map<String, String> getBuffer() {
        return buffer;
    }
}
