package in.wynk.API;

import in.wynk.common.DriverActionUtils;
import in.wynk.common.RestAssuredClient;
import in.wynk.framework.Driver.HashMapNew;
import in.wynk.framework.DriverFactory;
import in.wynk.framework.Reporting;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class API extends DriverActionUtils {


    public API(Reporting Reporter, in.wynk.framework.Assert Assert, in.wynk.framework.SoftAssert SoftAssert, ThreadLocal<HashMapNew> sTestDetails) {
        super(Reporter, Assert, SoftAssert, sTestDetails);
    }


    public Response hitSyncDiffAPI(String phoneNumber) {
        Response response = null;
        try {
            String payload = "{\"favourites\":{\"add\":[],\"remove\":[]},\"recents\":{\"add\":[],\"remove\":[]}}";
            String url = DriverFactory.getTestDetails().get("SYNC_HOST").trim() + "/v4/user/content/sync?appId=WEB&diff=true";
            Map<String, String> headers = new HashMap<String, String>();
            String token = TvUtknGenerator.getUtkn(phoneNumber, "POST", url, payload);
            headers.put("x-atv-did", "c456a833-5739-4662-825d-02a4bc42df52|BROWSER|WEBOS|10.15.7|1|1.0.0");
            headers.put("x-org-id", "rajtv");
            headers.put("x-atv-utkn", token);

            System.out.println("Token: " + token);
            RestAssuredClient restAssuredClient = new RestAssuredClient(Reporter, Assert, SoftAssert,
                    sTestDetails);
            response = restAssuredClient
                    .post(url, "application/json", headers, payload, true);
            System.out.println(
                    "Status code: " + response.getStatusCode() + "\n" + response.getBody().asString());
            System.out.println("End");
        } catch (Exception e) {
            Reporter.log("Hitting User Profile API", "Response should be valid Json", "Exception Occured",
                    "Fail");
        }
        return response;
    }


    public Response hitRelatedContentAPI(String phoneNumber, String playableId, Map<String, String> userContentPropertiesParams) {

        Response response = null;
        try {
//
            String url = DriverFactory.getTestDetails().get("SEARCH_HOST").trim() + "/app/v3/search/related?contentId=" + playableId + "&bn=20&dt=BROWSER&os=WEBOS&ln=en&appId=WEB&type=TVSHOW";
            Map<String, String> headers = new HashMap<String, String>();

            headers.put("x-atv-did", "36f73c61-2a20-48ac-b7fa-24000c0d2fcd|BROWSER|WEBOS|10.14.6|18|18.0.0");

            String token = TvUtknGenerator.getUtkn(phoneNumber, "GET", url);

            headers.put("x-atv-utkn", token);
            System.out.println("Token: " + token);

            RestAssuredClient restAssuredClient = new RestAssuredClient(Reporter, Assert, SoftAssert,
                    sTestDetails);
            RequestSpecification requestSpecification = RestAssured.given().headers(headers)
                    .urlEncodingEnabled(false).log().all();
            response = restAssuredClient.get(url, requestSpecification, headers, true);
            System.out.println(
                    "Status code: " + response.getStatusCode() + "\n" + response.getBody().asString());
            System.out.println("End");
        } catch (Exception e) {
            Reporter.log("Hitting User Profile API", "Response should be valid Json", "Exception Occured",
                    "Fail");
        }
        return response;

    }

    public Response hitLoginAPI(String phoneNumber, String OTP) throws Exception {
        Response response = null;
        String url = DriverFactory.getTestDetails().get("API_MASTER_HOST").trim() + "/v5/user/login?appId=WEB";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-atv-did", "c456a833-5739-4662-825d-02a4bc42df52|BROWSER|WEBOS|10.15.7|1|1.0.0");
        headers.put("x-org-id", "rajtv");
        headers.put("x-atv-utkn", TvUtknGenerator.getUtkn(phoneNumber, "POST", url));
        RestAssuredClient restAssuredClient = new RestAssuredClient(Reporter, Assert, SoftAssert, sTestDetails);
        response = restAssuredClient.post(url, "application/json", headers, "{\"msisdn\":\"" + phoneNumber + "\",\"otp\":\"" + OTP + "\"}", false);
        System.out.println("logoutAPI -- " + response.statusCode() + " " + response.getBody().toString());

//        System.out.println("Login Resp ---> " + response.asString());
//        System.out.println("Login Resp status code ---> " + response.getStatusCode());
        return response;
    }


    public Response hitAutoSuggestAPI(String phoneNumber,Map<String, String> queryParams , String query) throws Exception {
        Response response = null;
        queryParams.put("q" ,  query);
        String url = DriverFactory.getTestDetails().get("SEARCH_HOST").trim() + "/app/v3/search/autoSuggestion?appId=WEB";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-atv-utkn", TvUtknGenerator.getUtkn(phoneNumber, "GET", url));
        headers.put("x-atv-did", "c456a833-5739-4662-825d-02a4bc42df52|BROWSER|WEBOS|10.15.7|1|1.0.0");
        headers.put("x-org-id", "rajtv");
        RestAssuredClient restAssuredClient = new RestAssuredClient(Reporter, Assert, SoftAssert, sTestDetails);
        response = restAssuredClient.get(url, "application/json", headers, queryParams, true);
        System.out.println("autoSuggestAPI -- " + response.statusCode() + " " + response.getBody().toString());


        return response;
    }

  public Response hitSearchAPI(String phoneNumber,Map<String, String> queryParams , String query) throws Exception {
        Response response = null;
        queryParams.put("q" ,  query);
        String url = DriverFactory.getTestDetails().get("SEARCH_HOST").trim() + "/app/v3/search/atv/query?appId=WEB";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-atv-utkn", TvUtknGenerator.getUtkn(phoneNumber, "GET", url));
        headers.put("x-atv-did", "c456a833-5739-4662-825d-02a4bc42df52|BROWSER|WEBOS|10.15.7|1|1.0.0");
        headers.put("x-org-id", "rajtv");
        RestAssuredClient restAssuredClient = new RestAssuredClient(Reporter, Assert, SoftAssert, sTestDetails);
        response = restAssuredClient.get(url, "application/json", headers, queryParams, true);
        System.out.println("autoSuggestAPI -- " + response.statusCode() + " " + response.getBody().toString());


        return response;
    }

    public Response hitContentAPI(String ContentId , String phoneNumber) throws Exception {
        Response response = null;
        Map<String, String > queryParams = new HashMap<>();
        queryParams.put("id" ,  ContentId);
        queryParams.put("appId" ,  "WEB");
        String url = DriverFactory.getTestDetails().get("CONTENT_HOST").trim() + "app/v1/content?id="+ContentId+"&appId=WEB";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-atv-utkn", TvUtknGenerator.getUtkn(phoneNumber, "GET", url));
        headers.put("x-atv-did", "c456a833-5739-4662-825d-02a4bc42df52|BROWSER|WEBOS|10.15.7|1|1.0.0");
        headers.put("x-org-id", "rajtv");
        RestAssuredClient restAssuredClient = new RestAssuredClient(Reporter, Assert, SoftAssert, sTestDetails);
        response = restAssuredClient.get(url, "application/json", headers, queryParams, true);
        System.out.println("contentApi -- " + response.statusCode() + " " + response.getBody().toString());
        return response;
    }

    String utkn = "";

    public Response hitPackagesContentAPI(String MobileNumber, String packageIds, Map<String, String> userContentPropertiesParams) throws Exception {
        Response response = null;
        String url = DriverFactory.getTestDetails().get("CONTENT_HOST").trim() + "/app/v1/package?id=" + packageIds + "&isMax=true&appId=WEB&os=WEBOS&bn=20&dt=BROWSER&offset=0";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-atv-did", "36f73c61-2a20-48ac-b7fa-24000c0d2fcd|BROWSER|WEBOS|10.14.6|18|18.0.0");
        if (utkn.equals("")) {
            utkn = TvUtknGenerator.getUtkn(MobileNumber, "GET", url);
            headers.put("x-atv-utkn", utkn);
        } else {
            headers.put("x-atv-utkn", utkn);
        }
        RestAssuredClient restAssuredClient = new RestAssuredClient(Reporter, Assert, SoftAssert, sTestDetails);
        response = restAssuredClient.get(url, "application/json", headers, new HashMap<>(), true);
        return response;
    }

    public Response hitUserProfileConfigAPI(String phoneNumber) {
        Response response = null;
        String userConfigApiUrl = DriverFactory.getTestDetails().get("API_MASTER_HOST").trim() + "/v5/user/profile/userConfig?appId=WEB&cache=false";
        try {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("x-atv-did", "c456a833-5739-4662-825d-02a4bc42df52|BROWSER|WEBOS|10.15.7|1|1.0.0");
            headers.put("x-atv-utkn", TvUtknGenerator.getUtkn(phoneNumber, "POST", userConfigApiUrl));
            headers.put("x-org-id", "rajtv");
            RestAssuredClient restAssuredClient = new RestAssuredClient(Reporter, Assert, SoftAssert, sTestDetails);

            RequestSpecification requestSpecification = RestAssured.given().headers(headers).urlEncodingEnabled(false).log().all();
            response = restAssuredClient.post(userConfigApiUrl, requestSpecification, headers, "", true);
            response.statusCode();
            System.out.println("userProfileConfig -- " + response.statusCode() + " " + response.getBody().toString());

        } catch (Exception e) {
            Reporter.log("Hitting User Profile API", "Response should be valid Json", "Exception Occured",
                    "Fail");
        }
        return response;
    }

    public Response hitAppConfigAPI(String phoneNumber, Map<String, String> queryParams) {
        Response response = null;
        try {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("x-atv-did", "asdssddas|Phone|Android|28|12620|1.24.1");
            headers.put("x-atv-utkn",
                    TvUtknGenerator
                            .getUtkn(phoneNumber, "POST", DriverFactory.getTestDetails().get("API_MASTER_HOST").trim() + "/v3/user/profile/userConfig"));
            headers.put("cache-control", "no-cache");
            String userConfigApiUrl = DriverFactory.getTestDetails().get("CONTENT_HOST").trim() + "/app/v1/config/appConfig";
            RestAssuredClient restAssuredClient = new RestAssuredClient(Reporter, Assert, SoftAssert,
                    sTestDetails);
            response = restAssuredClient
                    .get(userConfigApiUrl, "application/json", headers, queryParams, true);
        } catch (Exception e) {
            Reporter.log("Hitting User Profile API", "Response should be valid Json", "Exception Occured",
                    "Fail");
        }
        return response;
    }


    public Response hitPageLayoutAPI(String phoneNumber, Map<String, String> queryParams, String pageId, String lang) {
        Response response = null;
         queryParams.put("pageId", pageId);
         queryParams.put("lg","en");
         queryParams.remove("mwTvPack");
         queryParams.remove("cp");
        // queryParams.put("")
        try {
            Map<String, String> headers = new HashMap<String, String>();
            String url = DriverFactory.getTestDetails().get("LAYOUT_HOST").trim() + "/app/v2/layout?appId=WEB";
            headers.put("x-atv-did", "17b8f460-5ade-477f-9014-56bba61cb5a8|BROWSER|WEBOS|10.15.7|41|41.0.0");
            headers.put("x-atv-utkn", TvUtknGenerator.getUtkn(phoneNumber, "GET", url));
            headers.put("cache-control", "max-age=0");
            RestAssuredClient restAssuredClient = new RestAssuredClient(Reporter, Assert, SoftAssert,
                    sTestDetails);
            response = restAssuredClient.get(url, "application/json", headers, queryParams, true);
            System.out.println("PageLayoutAPI -- " + response.statusCode() + " " + response.getBody().toString());
        } catch (Exception e) {
            Reporter.log("Hitting User Profile API", "Response should be valid Json", "Exception Occured",
                    "Fail");
        }
        return response;
    }

    public Response hitChannelsContentAPI(String phoneNumber, Map<String, String> queryParams) {
        Response response = null;
        try {
//            String url = DriverFactory.getTestDetails().get("CONTENT_HOST").trim() + "/app/v1/content/channels?mwTvPack=" + queryParams
//                    .get("mwTvPack") + "&dt=" + queryParams.get("dt") + "&os=" + queryParams.get("os")
//                    + "&ln=" + queryParams.get("ln") + "&bn=" + queryParams.get("bn")
//                    + "&appId=MOBILITY&mwTvPack=" + queryParams.get("mwTvPack") + "&dt=" + queryParams
//                    .get("dt") + "&os=" + queryParams.get("os") + "&ln=" + queryParams.get("ln") + "&bn="
//                    + queryParams.get("bn");

            String url = DriverFactory.getTestDetails().get("CONTENT_HOST").trim() + "/app/v1/content/channels?mwTvPack=200292&dt=BROWSER&os=WEBOS&ln=en,hi&bn=18&appId=WEB";


            Map<String, String> headers = new HashMap<String, String>();
            headers.put("x-atv-did", "36f73c61-2a20-48ac-b7fa-24000c0d2fcd|BROWSER|WEBOS|10.14.6|18|18.0.0");
            headers.put("x-atv-utkn", TvUtknGenerator.getUtkn(phoneNumber, "POST", url));
            headers.put("cache-control", "no-cache");
            RestAssuredClient restAssuredClient = new RestAssuredClient(Reporter, Assert, SoftAssert,
                    sTestDetails);
            response = restAssuredClient.get(url, "application/json", headers, new HashMap<>(), true);
        } catch (Exception e) {
            Reporter.log("Hitting User Profile API", "Response should be valid Json", "Exception Occured",
                    "Fail");
        }
        return response;
    }

    public Response hitEPGContentAPI(String phoneNumber, Map<String, String> queryParams) {
        Response response = null;
        long startTime = 0;
        long endTime = 0;
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat crunchifyFormat = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");
        String currentTime = crunchifyFormat.format(today);
        Date newDate = DateUtils.addMinutes(today, 30);
        String newTime = crunchifyFormat.format(newDate);
        try {
            Date date = crunchifyFormat.parse(currentTime);
            startTime = date.getTime();
            newDate = crunchifyFormat.parse(newTime);
            endTime = newDate.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
//            String url =
//                    DriverFactory.getTestDetails().get("CONTENT_HOST").trim() + "/app/v1/content/epg?startTime=" + startTime + "&endTime="
//                            + endTime + "&appId=MOBILITY&mwTvPack=" + queryParams.get("mwTvPack") + "&dt="
//                            + queryParams.get("dt") + "&os=" + queryParams.get("os") + "&ln=" + queryParams
//                            .get("ln") + "&bn=" + queryParams.get("bn");
            String url = DriverFactory.getTestDetails().get("CONTENT_HOST").trim() + "/app/v1/content/epg?mwTvPack=200292&dt=BROWSER&os=WEBOS&ln=en,hi&bn=18&startTime=" + startTime + "&endTime=" + endTime + "&appId=WEB";

            Map<String, String> headers = new HashMap<String, String>();
            headers.put("x-atv-did", "36f73c61-2a20-48ac-b7fa-24000c0d2fcd|BROWSER|WEBOS|10.14.6|18|18.0.0");
            headers.put("x-atv-utkn", TvUtknGenerator.getUtkn(phoneNumber, "POST", url));
            headers.put("cache-control", "no-cache");
            RestAssuredClient restAssuredClient = new RestAssuredClient(Reporter, Assert, SoftAssert,
                    sTestDetails);
            response = restAssuredClient.get(url, "application/json", headers, new HashMap<>(), true);
        } catch (Exception e) {
            Reporter.log("Hitting User Profile API", "Response should be valid Json", "Exception Occured",
                    "Fail");
        }
        return response;
    }


    /**
     * @param MobileNumber          10 Digit Airtel Mobile Number
     * @param QueryParamsTypeWithID Example :-  channelId=MWTV_LIVETVCHANNEL_611
     * @param startTime
     * @param endTime
     * @return EPG Response based one Playable Content ID
     * @throws Exception
     */
    public Response hitEPGContentAPI(String MobileNumber, String QueryParamsTypeWithID, String startTime, String endTime) throws Exception {
        Response response = null;
        String URL = DriverFactory.getTestDetails().get("CONTENT_HOST").trim() + "/app/v1/content/channel/epg?" + QueryParamsTypeWithID + "&startTime=" + startTime + "&endTime=" + endTime + "&appId=WEB";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-atv-did", "36f73c61-2a20-48ac-b7fa-24000c0d2fcd|BROWSER|WEBOS|10.14.6|18|18.0.0");
        headers.put("x-atv-utkn", TvUtknGenerator.getUtkn(MobileNumber, "POST", URL));
        RestAssuredClient restAssuredClient = new RestAssuredClient(Reporter, Assert, SoftAssert, sTestDetails);
        response = restAssuredClient.get(URL, "application/json", headers, new HashMap<>(), true);
        return response;
    }

    public Response hitSyncRecommendationAPI(String phoneNumber, Map<String, String> queryParams,
                                             Map<String, String> railToIdMapping) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry entry : railToIdMapping.entrySet()) {
            if (entry.getKey().equals("More Like {0}") || entry.getKey().equals("Recommended TV Shows")
                    || entry.getKey().equals("Recommended Movies")) {
                stringBuffer.append(entry.getValue() + ",");
            }
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        queryParams.put("railIds", stringBuffer.toString());
        queryParams.put("isMax", "false");
        queryParams.put("appId", "MOBILITY");
        queryParams.remove("pageId");

        Response response = null;
        try {
            String url = DriverFactory.getTestDetails().get("SYNC_HOST").trim() + "/v2/user/recommendation/content?cl=dl&ut=XXX&lg=bh&op=AIRTEL&rg=true&cp=airtel_tv,altbalaji,curiositystream,erosnow,fastfilmz,hoichoi,hooq,hotstar,hungama,lionsgateplay,mwtv,ndtv,shemaroome,ultra,zee5&os=ANDROID&dt=phone&bn=12620&pacp=&pncp=&refresh=true&recInProg=false&currSeg=ATVPLUS&railIds=5d9361c6e4b02d179c52d28b,5d0c4688e4b007ac3fd762ed,5d0b5d00e4b0d265967e8fe4&isMax=false&appId=MOBILITY";
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("x-atv-did", "27438046b468fb6c|Phone|Android|28|12583|1.18.0");
            String token = TvUtknGenerator.getUtkn(phoneNumber, "GET", url);
            headers.put("x-atv-utkn", token);
            System.out.println("Token: " + token);
            RestAssuredClient restAssuredClient = new RestAssuredClient(Reporter, Assert, SoftAssert,
                    sTestDetails);
            RequestSpecification requestSpecification = RestAssured.given().headers(headers)
                    .urlEncodingEnabled(false).log().all();
            response = restAssuredClient.get(url, requestSpecification, headers, true);
            System.out.println(
                    "Status code: " + response.getStatusCode() + "\n" + response.getBody().asString());
            System.out.println("End");
        } catch (Exception e) {
            Reporter.log("Hitting User Profile API", "Response should be valid Json", "Exception Occured",
                    "Fail");
        }
        return response;
    }

    public String getChannelListForLanguage(String language) {
        Response rs = null;
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json, text/plain, */*");

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("mwTvPack", "200292");
        queryParams.put("dt", "phone");
        queryParams.put("os", "ANDROID");
        queryParams.put("ln", language);
        queryParams.put("bn", "12615");
        queryParams.put("appId", "MOBILITY");
        queryParams.put("mwTvPack", "200292");
        queryParams.put("dt", "phone");
        queryParams.put("os", "ANDROID");
        queryParams.put("ln", language);
        queryParams.put("bn", "12615");
        queryParams.put("appId", "MOBILITY");

        try {
            RestAssuredClient restAssuredClient = new RestAssuredClient(Reporter, Assert, SoftAssert,
                    sTestDetails);
            rs = restAssuredClient
                    .get(DriverFactory.getTestDetails().get("CONTENT_HOST").trim() + "/app/v1/content/channels", "application/json",
                            headers, queryParams,
                            true);
        } catch (Exception e) {
            Reporter
                    .log("Hitting API to get Channel list on the basis of language", "true", "false", "fail");
        }
        String channelLists = rs.getBody().asString();
        return channelLists;
    }

    public String getLanguagesFromAPI(String phoneNumber) throws Exception {
        String langValueFromAPI = null;
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-atv-did", "asdssddas|STICK|ANDROID|23|19|1.5.0");
        headers.put("x-atv-utkn",
                TvUtknGenerator.getUtkn(phoneNumber, "POST", "/v2/user/profile/userConfig"));
        headers.put("cache-control", "no-cache");
        String env = TvUtknGenerator.getConfigXMLValue("env");
        String userConfigApiUrl = null;
        if (env.equalsIgnoreCase("PROD") || env.equalsIgnoreCase("PREPROD")) {
            userConfigApiUrl = DriverFactory.getTestDetails().get("API_MASTER_HOST").trim() + "/v2/user/profile/userConfig";
        } else if (env.equalsIgnoreCase("LOCAL") || env.equalsIgnoreCase("DEV")) {
            userConfigApiUrl = "https://apimaster-dev2.wynk.in/v2/user/profile/userConfig";
        }
        RestAssuredClient restAssuredClient = new RestAssuredClient(Reporter, Assert, SoftAssert,
                sTestDetails);
        Response response = restAssuredClient
                .post(userConfigApiUrl, "application/json", headers, "", true);
        langValueFromAPI = response.path("userInfo.lang").toString(); // eg. key - "results.id[0]"
        return langValueFromAPI;
    }


}
