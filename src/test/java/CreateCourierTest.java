
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.CourierModel;
import model.LoginModel;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import steps.CourierSteps;

import static TestData.TestValue.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static steps.CourierSteps.*;

public class CreateCourierTest {
    private CourierModel courier;
    private CourierModel duplicateCourier;

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }


   @Test
   @DisplayName("Проверка создания курьера со всеми полями")
    public void createCourierSuccessTest(){
        CourierSteps courierSteps = new CourierSteps();
        courier = new CourierModel(COURIER_LOGIN, COURIER_PASSWORD, COURIER_FIRST_NAME);
        createCourier(courier)
                .then()
                .statusCode(201)
                .body("ok", equalTo(true));
    }


   @Test
   @DisplayName("Проверка создания двух одинаковых курьеров")
    public void createDuplicateCourierTest(){
        CourierSteps courierSteps = new CourierSteps();
        courier = new CourierModel(COURIER_LOGIN, COURIER_PASSWORD, COURIER_FIRST_NAME);
        createCourier(courier)
                .then()
                .statusCode(201)
                .body("ok", equalTo(true));
        createCourier(courier)
                .then()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }


    @Test
    @DisplayName("Проверка создания курьера с пустым логином")
    public void createCourierEmptyLoginTest(){
        CourierSteps courierSteps = new CourierSteps();
        courier = new CourierModel(COURIER_EMPTY_LOGIN, COURIER_PASSWORD, COURIER_FIRST_NAME);
        createCourier(courier)
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }



    @Test
    @DisplayName("Проверка создания курьера с пустым паролем")
    public void createCourierEmptyPasswordTest(){
        CourierSteps courierSteps = new CourierSteps();
        courier = new CourierModel(COURIER_LOGIN, COURIER_EMPTY_PASSWORD, COURIER_FIRST_NAME);
        createCourier(courier)
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }


    @Test
    @DisplayName("Проверка создания курьера с существующим логином")
    public void createCourierDuplicateLoginTest(){
        CourierSteps courierSteps = new CourierSteps();
        courier = new CourierModel(COURIER_DUPLICATE_LOGIN, COURIER_PASSWORD, COURIER_FIRST_NAME);
        createCourier(courier)
                .then()
                .statusCode(201)
                .body("ok", equalTo(true));
        duplicateCourier = new CourierModel(COURIER_DUPLICATE_LOGIN, COURIER_PASSWORD, COURIER_EMPTY_FIRST_NAME);
        createCourier(duplicateCourier)
                .then()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    public void cleanUp(){
        CourierSteps courierSteps = new CourierSteps();
        LoginModel loginModel = LoginModel.from(courier);
        Response response = viewLoginCourier(loginModel);
        if (response.getStatusCode() == 200) {
            int courierId = response.path("id");
            if (courierId != 0) {
                deleteCourier(courierId)
                        .then()
                        .statusCode(200)
                        .body("ok", equalTo(true));
            }
        }
    }
}


