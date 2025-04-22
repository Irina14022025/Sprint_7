
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.CourierModel;
import model.LoginModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.CourierSteps;

import static TestData.TestValue.*;
import static TestData.TestValue.COURIER_FIRST_NAME;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static steps.CourierSteps.*;

public class LoginCourierTest {
    private CourierModel courier;

    @Before
    public void setup() {
        RestAssured.baseURI = BASE_URL;
        CourierSteps courierSteps = new CourierSteps();
        courier = new CourierModel(COURIER_LOGIN, COURIER_PASSWORD, COURIER_FIRST_NAME);
        createCourier(courier);

    }


    @Test
    @DisplayName("Проверка авторизации с полными существующими данными")
    public void LoginCourierSuccessTest(){
        LoginModel loginModel = LoginModel.from(courier);
        viewLoginCourier(loginModel)
                .then().assertThat()
                .statusCode(200)
                .body("id", notNullValue());
    }


   @Test
   @DisplayName("Проверка авторизации с пустым логином")
    public void LoginCourierEmptyLoginTest(){
        LoginModel loginModel = new LoginModel(COURIER_EMPTY_LOGIN, COURIER_PASSWORD);
        viewLoginCourier(loginModel)
                .then().assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }


    @Test
    @DisplayName("Проверка авторизации с пустым паролем")
    public void LoginCourierEmptyPasswordTest(){
        LoginModel loginModel = new LoginModel(COURIER_LOGIN, COURIER_EMPTY_PASSWORD);
        viewLoginCourier(loginModel)
                .then().assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }


    @Test
    @DisplayName("Проверка авторизации с несуществующим логином")
    public void LoginCourierNotExistLoginTest(){
        LoginModel loginModel = new LoginModel(COURIER_NOT_EXIST_LOGIN, COURIER_PASSWORD);
        viewLoginCourier(loginModel)
                .then().assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }


    @Test
    @DisplayName("Проверка авторизации с несуществующим паролем")
    public void LoginCourierNotExistPasswordTest(){
        LoginModel loginModel = new LoginModel(COURIER_LOGIN, COURIER_NOT_EXIST_PASSWORD);
        viewLoginCourier(loginModel)
                .then().assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }


    @Test
    @DisplayName("Проверка авторизации с несуществующими паролем и логином")
    public void LoginCourierNotExistLoginAndPasswordTest(){
        LoginModel loginModel = new LoginModel(COURIER_NOT_EXIST_LOGIN, COURIER_NOT_EXIST_PASSWORD);
        viewLoginCourier(loginModel)
                .then().assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }


    @After
    public void cleanUp(){
        LoginModel loginModel = LoginModel.from(courier);
        Response response = viewLoginCourier(loginModel);
            int courierId = response.path("id");
                deleteCourier(courierId)
                        .then()
                        .statusCode(200)
                        .body("ok", equalTo(true));
    }
}
