package org.nirojan;


import Utils.JsonUtil;
import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        System.out.println(JsonUtil.getJsonValue("invalidEmail"));
    }
}