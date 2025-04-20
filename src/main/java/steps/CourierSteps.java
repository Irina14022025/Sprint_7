package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.CourierModel;
import model.LoginModel;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CourierSteps {
    public static final String COURIER_CREATE_PATH = "/api/v1/courier";
    public static final String COURIER_LOGIN_PATH = "/api/v1/courier/login";
    public static final String COURIER_DELETE_PATH = "/api/v1/courier/";

    @Step("Создание курьера POST /api/v1/courier")
    public static Response createCourier(CourierModel courier){
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post(COURIER_CREATE_PATH)
                .then().log().all()
                .extract().response();
    }

    @Step("Авторизация курьера POST /api/v1/courier/login")
    public static Response viewLoginCourier(LoginModel loginModel){
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(loginModel)
                .when()
                .post(COURIER_LOGIN_PATH)
                .then().log().all()
                .extract().response();
    }

    @Step("Удаление курьера DELETE /api/v1/courier/:id")
    public static Response deleteCourier(int courierId){
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(Map.of("id", courierId))
                .when()
                .delete(COURIER_DELETE_PATH + courierId)
                .then().log().all()
                .extract().response();
    }
}
