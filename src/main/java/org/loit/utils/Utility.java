package org.loit.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Utility {

    public static final String[] MOBILE_NUMBER_PREFIXES = new String[]{"070", "071", "072", "074", "075", "076", "077", "078"};

    public static String generateRandomMobileNumber() throws IOException {
        Random random = new Random();
        String mobileNumberPrefix = MOBILE_NUMBER_PREFIXES[random.nextInt(MOBILE_NUMBER_PREFIXES.length)];
        int remainingDigits = 10 - mobileNumberPrefix.length();
        StringBuilder mobileNumber = new StringBuilder(mobileNumberPrefix);
        for (int i = 0; i < remainingDigits; i++) {
            mobileNumber.append(random.nextInt(10));
        }
        return mobileNumber.toString();
    }
    public JSONObject jsonReader(String path) throws IOException, ParseException {
        String jsonBody = new String(Files.readAllBytes(Paths.get(path)));
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(jsonBody);
    }
}
