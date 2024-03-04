package org.loit.utils;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.loit.config.factory.ApiConfigFactory;

import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class LoginAccessToken {
    public String LoginAccessTokenCreation() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonBody = (JSONObject) parser.parse(new FileReader(ApiConfigFactory.getConfig().pathCusLoginAccessToken()));

        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", jsonBody.get("grant_type"))
                .formParam("username",jsonBody.get("username"))  // Use the randomMobileNumber for login
                .formParam("password", jsonBody.get("password"))
                .formParam("client_id", jsonBody.get("client_id"))
                .formParam("client_secret", jsonBody.get("client_secret"))
                .cookie("cookie", jsonBody.get("cookie"))
                .when()
                .post(ApiConfigFactory.getConfig().tokenUrl())
                .then()
                .log().all()
                .extract().response();

        String loginAccessToken = response.jsonPath().getString("access_token");
        System.out.println("LoginAccessToken: " + loginAccessToken);
        return loginAccessToken;
    }
}
