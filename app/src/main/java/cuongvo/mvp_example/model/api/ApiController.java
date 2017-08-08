package cuongvo.mvp_example.model.api;

/**
 * Created by cuongvo on 7/22/17.
 * <p>
 * Since the requirement is using my own JSON data, I created these JSON dataset with random data.
 */
public class ApiController {
    private static final String MOCKDATA_CITY_GUIDE_PAGE_1 = "https://api.myjson.com/bins/coakz";
    private static final String MOCKDATA_CITY_GUIDE_PAGE_2 = "https://api.myjson.com/bins/fnglv";
    private static final String MOCKDATA_CITY_GUIDE_PAGE_3 = "https://api.myjson.com/bins/1az2qr";
    private static final String MOCKDATA_CITY_GUIDE_PAGE_4 = "https://api.myjson.com/bins/18aesz";

    private static final String MOCKDATA_SHOP = "https://api.myjson.com/bins/nejob";

    private static final String MOCKDATA_EAT = "https://api.myjson.com/bins/87zqz";

    public static String getCityGuideList() {
        return MOCKDATA_CITY_GUIDE_PAGE_1;
    }

    public static String getEatList() {
        return MOCKDATA_EAT;
    }

    public static String getShopList() {
        return MOCKDATA_SHOP;
    }

    public static String getCityGuideListWithPageNumber(int page) {
        switch (page) {
            case 1:
                return MOCKDATA_CITY_GUIDE_PAGE_2;
            case 2:
                return MOCKDATA_CITY_GUIDE_PAGE_3;
            case 3:
                return MOCKDATA_CITY_GUIDE_PAGE_4;
            default:
                return "";
        }

    }

    public static String getShopListWithPageNumber(int page) {
        return "";

    }

    public static String getEatListWithPageNumber(int page) {
        return "";

    }
}
