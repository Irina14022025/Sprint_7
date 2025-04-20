import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import model.OrderModel;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.OrderSteps;

import java.util.List;

import static TestData.TestValue.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static steps.OrderSteps.createOrder;

@RunWith(Parameterized.class)
public class CreateOrderParamTest {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;

    public CreateOrderParamTest(String firstName, String lastName, String address, String metroStation, String phone,
                                int rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderValues() {
        return new Object[][]{
                {ORDER_FIRST_NAME, ORDER_LAST_NAME, ORDER_ADDRESS, ORDER_METRO_STATION, ORDER_PHONE, ORDER_RENT_TIME,
                        ORDER_DELIVERY_DATE, ORDER_COMMENT, List.of("BLACK")},
                {ORDER_FIRST_NAME, ORDER_LAST_NAME, ORDER_ADDRESS, ORDER_METRO_STATION, ORDER_PHONE, ORDER_RENT_TIME,
                        ORDER_DELIVERY_DATE, ORDER_COMMENT, List.of("BLACK", "GREY")},
                {ORDER_FIRST_NAME, ORDER_LAST_NAME, ORDER_ADDRESS, ORDER_METRO_STATION, ORDER_PHONE, ORDER_RENT_TIME,
                        ORDER_DELIVERY_DATE, ORDER_COMMENT, List.of()}
        };
    }

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }


    @Test
    @DisplayName("Проверка создания заказа с разным вариантом выбора цвета самоката: один цвет, два цвета или без цвета")
    public void createOrderWithDifferentColours(){
        OrderSteps orderSteps = new OrderSteps();
        OrderModel order = new OrderModel(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        createOrder(order)
                .then().assertThat()
                .statusCode(201)
                .body("track", notNullValue());

    }

}
