package com.back;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Rq {
    private final String actionName;
    private final Map<String, String> paramsMap;

    // 생성자
    public Rq(String cmd) {
        String[] cmdBits = cmd.split("\\?",2);
        actionName = cmdBits[0];
        String queryString = cmdBits.length > 1 ? cmdBits[1].trim() : "";

        // 스트림으로 map 생성
        paramsMap = Arrays.stream(queryString.split("&"))
                .map(part -> part.split("=",2))
                .filter(bits -> bits.length == 2 && !bits[0].trim().isEmpty() && !bits[1].trim().isEmpty())
                .collect(Collectors.toMap( // map으로 바꿈
                        bits -> bits[0].trim(), // key
                        bits -> bits[1].trim() // value
                ));
    }

    public String getActionName(){
        return actionName;
    }

    public String getParam(String paramName, String defaultValue){
        return paramsMap.getOrDefault(paramName, defaultValue);
        // .getOrDefault(key,defaultValue) ->
        // paramName이 있으면 반환, 없으면 defaultValue 반환.
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        String value = getParam(paramName, "");

        if (value.isEmpty()) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
