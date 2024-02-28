package org.loit.api;

import org.loit.config.factory.ApiConfigFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;

import static io.restassured.RestAssured.given;

public final class ReqresApi {

  private ReqresApi() {

  }
  private static final String BASE_URL = ApiConfigFactory.getConfig().apiBaseUrl();


  @Getter
  public static String accessToken;

  @Getter
  public static String loginAccessToken;

  public static  Response postRequest(Object jsonBody, String endpoint, String accessToken) {
    return given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(jsonBody)
            .header("Authorization","Bearer "+accessToken)
            .when()
            .post(BASE_URL+endpoint);
  }
  public static  Response getRequest(String endpoint, String accessToken, String token) {
    return given()
            .accept(ContentType.JSON)
            .header("Authorization","Bearer "+accessToken)
            .when()
            .get(BASE_URL+endpoint);
  }

  public static  Response getRequestLogIn(String endpoint, String cusLoginAccess) {
    return given()
            .accept(ContentType.JSON)
            .header("Authorization","Bearer "+cusLoginAccess)
            .when()
            .get(BASE_URL+endpoint);
  }

  public static  Response geKycTypes(String endpoint, String loginAccessToken) {
    return given()
            .accept(ContentType.JSON)
            .header("Authorization","Bearer "+loginAccessToken)
            .when()
            .get(BASE_URL+endpoint);
  }

  public static  Response getPaymentSources(String endpoint, String loginAccessToken) {
    return given()
            .accept(ContentType.JSON)
            .header("Authorization","Bearer "+loginAccessToken)
            .when()
            .get(BASE_URL+endpoint);
  }

  public static Response putRequest(String id,Object jsonBodyPut,String endpoint){
    return given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam("id", id)
            .body(jsonBodyPut)
            .when()
            .put(BASE_URL+endpoint);
  }

  public static Response deleteRequest(String id,String endpoint){
    return given()
            .pathParam("id",id)
            .accept(ContentType.JSON)
            .when()
            .delete(BASE_URL+endpoint);
  }
  public static  Response postPaymentSources(Object jsonBody, String endpoint, String loginAccessToken) {
    return given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(jsonBody)
            .header("Authorization","Bearer "+loginAccessToken)
            .when()
            .post(BASE_URL+endpoint);
  }
}