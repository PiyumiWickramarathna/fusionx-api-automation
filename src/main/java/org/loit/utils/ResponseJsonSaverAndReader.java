package org.loit.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResponseJsonSaverAndReader {

    public static void saveResponseAsJson(Response response, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(response.prettyPrint());
            System.out.println("Response saved as JSON file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    public static String getValueFromJsonFile(String filePath, String jsonPathExpression) {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
            JsonPath jsonPath = new JsonPath(jsonContent);
            return jsonPath.getString(jsonPathExpression);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return null;
        }
    }

    public static String readJsonFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        }
    }

}
