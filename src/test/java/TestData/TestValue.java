package TestData;

public class TestValue {
    public static final String BASE_URL = "http://qa-scooter.praktikum-services.ru";

    // данные для создания курьера
    public static final String COURIER_LOGIN = "test_login_" + System.currentTimeMillis();
    public static final String COURIER_EMPTY_LOGIN = "";
    public static final String COURIER_DUPLICATE_LOGIN = "duplicate_login13";
    public static final String COURIER_NOT_EXIST_LOGIN = "not_exist_login_" + System.currentTimeMillis();
    public static final String COURIER_PASSWORD = "1234";
    public static final String COURIER_NOT_EXIST_PASSWORD = "password_" + System.currentTimeMillis();
    public static final String COURIER_EMPTY_PASSWORD = "";
    public static final String COURIER_FIRST_NAME = "Stepan";
    public static final String COURIER_EMPTY_FIRST_NAME = "";

    //данные для создания заказа
    public static final String ORDER_FIRST_NAME = "Иван";
    public static final String ORDER_LAST_NAME = "Иванов";
    public static final String ORDER_ADDRESS = "Строителей, 13";
    public static final String ORDER_METRO_STATION = "4";
    public static final String ORDER_PHONE = "8 800 555 35 35";
    public static final int ORDER_RENT_TIME = 4;
    public static final String ORDER_DELIVERY_DATE = "2025-04-30";
    public static final String ORDER_COMMENT = "Koд от ворот - 1342";

}
