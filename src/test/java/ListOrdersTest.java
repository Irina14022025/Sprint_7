
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import steps.OrderSteps;

import static TestData.TestValue.BASE_URL;
import static org.hamcrest.CoreMatchers.notNullValue;
import static steps.OrderSteps.viewListOrder;

public class ListOrdersTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }


    @Test
    @DisplayName("Проверка получения списка заказов")
    public void checkThatListOrdersNotNullValueTest(){
        OrderSteps orderSteps = new OrderSteps();
        viewListOrder()
                .then().assertThat()
                .statusCode(200)
                .body("orders", notNullValue());
    }
}
