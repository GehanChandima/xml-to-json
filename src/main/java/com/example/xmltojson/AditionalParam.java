package com.example.xmltojson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class AditionalParam {
    public static void main(String[] args) throws IOException {


        File jsonFileDefaultJson = new File("src/main/resources/default.json");
        File jsonFileActualJson = new File("src/main/resources/actual.json");

        String contentDefault = new String(Files.readAllBytes(Paths.get(jsonFileDefaultJson.getPath())));
        String contentActual = new String(Files.readAllBytes(Paths.get(jsonFileActualJson.getPath())));

        JSONObject defaultJson = new JSONObject(contentDefault);
        JSONObject actualJson = new JSONObject(contentActual);

        Map<String, Object> extraPathValues = new HashMap<>();
        findExtraPathsAndValues(actualJson, defaultJson, "", extraPathValues);

        for (Map.Entry<String, Object> entry : extraPathValues.entrySet()) {
            System.out.println("Path: " + entry.getKey());
            System.out.println("Value: " + entry.getValue());
            System.out.println();
        }

    }



    public static void findExtraPathsAndValues(JSONObject actual, JSONObject expected, String path, Map<String, Object> extraPathValues) {
        Iterator<String> keys = actual.keys();

        while (keys.hasNext()) {
            String key = keys.next();
            String currentPath = path + "/" + key;

            if (!expected.has(key)) {
                Object value = actual.get(key);
                if (value instanceof JSONObject) {
                    JSONObject valueObject = (JSONObject) value;
                    for (String subKey : valueObject.keySet()) {
                        String subPath = currentPath + "/" + subKey;
                        Object subValue = valueObject.get(subKey);
                        extraPathValues.put(subPath, subValue);
                    }
                } else {
                    extraPathValues.put(currentPath, value);
                }
            } else if (actual.get(key) instanceof JSONObject && expected.get(key) instanceof JSONObject) {
                findExtraPathsAndValues(actual.getJSONObject(key), expected.getJSONObject(key), currentPath, extraPathValues);
            } else if (actual.get(key) instanceof JSONArray && expected.get(key) instanceof JSONArray) {
                // Handle arrays if needed
            }
        }
    }

}

