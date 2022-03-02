package in.wynk.API;

import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Driver;
import in.wynk.framework.Reporting;
import io.restassured.response.Response;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class APICommon extends DriverActionUtils {

  static Reporting reporter;
    static   in.wynk.framework.Assert anAssert;
      static  in.wynk.framework.SoftAssert softAssert;
    static ThreadLocal<Driver.HashMapNew> sTestDetails;


    public APICommon(Reporting Reporter, in.wynk.framework.Assert Assert, in.wynk.framework.SoftAssert SoftAssert, ThreadLocal<Driver.HashMapNew> sTestDetails) {
        super(Reporter, Assert, SoftAssert, sTestDetails);
        this.reporter = Reporter;
        this.anAssert = Assert;
        this.softAssert =SoftAssert;
        this.sTestDetails = sTestDetails;
    }

    public static Map<String, String> getUserContentProperties(Response userProfileResponse) {
        Map<String, String> userProperties = userProfileResponse.jsonPath().getMap("userContentProperties");
        return userProperties;
    }
    public static List<String>  getAutosuggestionTitles(Response autoSuggestResponse) {
        List<String> autoSuggestTitles = (autoSuggestResponse.jsonPath().getList("autoSuggestTitles.title"));
        List<String> titles = new ArrayList<>();
        for(String title : autoSuggestTitles){
            titles.add(title.trim());
        }
        return titles;
    }


    public static List<String> getAllPremiumPackageIdsFromAPI(Response channelsContentAPIResponse) {
        List<String> packageIds;
        List<HashMap<String, String>> allChannels = channelsContentAPIResponse.jsonPath().get("channels");
        packageIds = allChannels.stream().filter(m -> m.containsKey("segment")).filter(m -> m.get("segment").trim().equals("ATVPLUS")).map(m -> m.get("id").trim()).collect(Collectors.toList());
        return packageIds;
    }

    public static List<String> getAllChannelNameByGenres(Response channelsContentAPIResponse, String genresName) {
        List<String> packageIds = new ArrayList<>();
        List<HashMap<String, Object>> allChannels = channelsContentAPIResponse.jsonPath().get("channels");
        packageIds = allChannels.stream().filter(m -> m.containsKey("genres")).filter(m -> ((ArrayList) m.get("genres")).contains(genresName.trim())).map(m -> m.get("title")).collect(Collectors.toList()).stream().map(m -> m.toString
                ()).collect(Collectors.toList());
        return packageIds;
    }


    public static List<String> getAllChannelTitleAndIdByGenres(Response channelsContentAPIResponse, String genresName) {
        List<String> packageIds = new ArrayList<>();
        List<HashMap<String, Object>> allChannel = channelsContentAPIResponse.jsonPath().get("channels");
        for (HashMap<String, Object> allChannels : allChannel) {
            if (allChannels.get("genres").toString().contains(genresName)) {
                packageIds.add(" id:-  " + allChannels.get("id").toString() + " & title:- " + allChannels.get("title").toString() + " && lang:- " + allChannels.get("lang").toString() + " &&& genres " + genresName);
            }
        }
        return packageIds;
    }

    public static List<String> getAllGenres(Response channelsContentAPIResponse) {
        List<String> packageIds = new ArrayList<>();
        List<HashMap<String, List>> allChannels = channelsContentAPIResponse.jsonPath().get("channels");
        packageIds = allChannels.stream().filter(m -> m.containsKey("genres")).map(m -> m.get("genres").get(0).toString().trim()).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());
        return packageIds;
    }

    public static List<String> getAllPremiumChannelsList(Response epgContentAPIResponse, List<String> packageIds) {
        List<String> allChannels = new LinkedList<>();
        for (String packages : packageIds) {
            List<HashMap<String, String>> channels = epgContentAPIResponse.jsonPath().get("programGuide." + packages);
            allChannels.addAll(channels.stream().map(m -> m.get("title").trim()).collect(Collectors.toList()));
        }
        return allChannels;
    }


    public static String getChannelInformation(Response epgContentAPIResponse, String packageIds) {
        List<String> allChannelsInfo = new ArrayList<>();
        String channelInfo = "";
        List<HashMap<String, Object>> channels = epgContentAPIResponse.jsonPath().get("programGuide." + packageIds);
        for (HashMap<String, Object> channel : channels) {
            //   allChannelsInfo.add("title :- " + channel.get("title") + " $ startTime :-" + channel.get("startTime") + " $$ endTime :-" + channel.get("endTime"));
            channelInfo = channelInfo + "title :- " + channel.get("title") + " $ startTime :-" + channel.get("startTime") + " $$ endTime :-" + channel.get("endTime") + " || ";
        }
        return channelInfo;
    }

    public static String getBannerPackageId(Response response) {
        String BannerId = "";
        List<HashMap<String, Object>> channels = response.jsonPath().get();
        for (HashMap<String, Object> channel : channels) {
            if (channel.get("format").toString().toLowerCase().contains("banner".toLowerCase())) {
                return StringUtils.substringBetween(channel.get("contents").toString(), "packageId=", ",");
            }

        }
        return BannerId;

    }

    public static String getRailPackageId(Response response, String railName) {
        String BannerId = "";
        List<HashMap<String, Object>> channels = response.jsonPath().get();
        for (HashMap<String, Object> channel : channels) {
            if (channel.get("format").toString().toLowerCase().contains(railName.trim().toLowerCase())) {
                return StringUtils.substringBetween(channel.get("contents").toString(), "packageId=", ",");
            }
        }
        return BannerId;

    }


    public static List<String> getRailsNameFromLayoutAPI(String mobileNumber, Response LayoutResponse) throws Exception {
        API api = new API(reporter,anAssert,softAssert,sTestDetails);
        List<String> railsName = new ArrayList<>();
        List<HashMap<String, Object>> Rails = LayoutResponse.jsonPath().get();
        for (HashMap<String, Object> rail : Rails) {
            HashMap<String, Object> format = (HashMap<String, Object>) rail.get("format");
            List<HashMap<String, Object>> contents = (List<HashMap<String, Object>>) rail.get("contents");
            if (contents.size() > 0) {
                if (format.get("t") != null && !format.get("t").equals("")) {
                    if (api.hitPackagesContentAPI(mobileNumber, contents.get(0).get("packageId").toString().trim(), null).getStatusCode() == 200) {
                        railsName.add(format.get("t").toString().trim().replaceAll(" +", " "));
                    }
                } else {
                    if (format.get("ty").toString().trim().equals("CHANNEL")) {
                        if (api.hitPackagesContentAPI(mobileNumber, contents.get(0).get("packageId").toString().trim(), null).getStatusCode() == 200) {
                            railsName.add(rail.get("name").toString().trim().replaceAll(" +", " "));
                        }
                    }
                }
            }
        }
        return railsName;
    }



    public static List<String> getName_PackID_ID_ValuesFromLayoutAPI(Response LayoutResponse) {
        List<String> railsName = new ArrayList<>();
        List<HashMap<String, Object>> Rails = LayoutResponse.jsonPath().get();

        for (HashMap<String, Object> rail : Rails) {
            String railPackages = "";
            HashMap<String, Object> format = (HashMap<String, Object>) rail.get("format");
            List<HashMap<String, Object>> contents = (List<HashMap<String, Object>>) rail.get("contents");
            for (HashMap<String, Object> content : contents) {
                railPackages = railPackages + "," + content.get("packageId").toString().trim();
            }
            if (!format.get("ty").toString().equals("BANNER") && !format.get("t").toString().equals("")) {
                //  System.out.println(format.get("t").toString().trim() + "|" + railPackages + "||" + rail.get("id").toString());
                railsName.add(format.get("t").toString().trim() + "|" + railPackages + "||" + rail.get("id").toString());
            }

        }
        return railsName;
    }


    public static List<String> getNonLiveRailsNameFromLayoutAPI(Response layoutResponse) {
        List<String> railsName = new ArrayList<>();
        List<HashMap<String, Object>> channels = layoutResponse.jsonPath().get();
        for (HashMap<String, Object> channel : channels) {
            if (!channel.get("contents").toString().contains("LIVE") && !channel.get("format").toString().contains("BANNER")) {
                HashMap<String, Object> hashMap = (HashMap<String, Object>) channel.get("format");
                railsName.add(hashMap.get("t").toString().trim());
            }
        }
        return railsName;
    }


    public static List<String> getContentByPackageId(Response response, String packageID, String key) {
        List<String> contentInfo = new ArrayList<>();
        List<HashMap<String, Object>> contents = response.jsonPath().get("content");
        for (HashMap<String, Object> content : contents) {
            contentInfo.add(content.get(key.trim()).toString());
        }
        return contentInfo;
    }

    public static String getRandomContentURL(Response response) {
        List<HashMap<String, Object>> contents = response.jsonPath().get("content");
        int size = contents.size() - 1;
        int result = 0;
        if (size > 0) {
            result = new Random().nextInt(size);
        }
        System.out.println("https://www.airtelxstream.in/" + contents.get(result).get("programType").toString().trim() + "/" + contents.get(result).get("title").toString().trim() + "/" + contents.get(result).get("playableId").toString().trim() + "");
        return "/" + contents.get(result).get("programType").toString().trim() + "/" + contents.get(result).get("title").toString().trim() + "/" + contents.get(result).get("playableId").toString().trim() + "";
    }

    public static List<String> getContentFromRelatedResponse(Response response, String key) {
        List<String> contentInfo = new ArrayList<>();
        List<HashMap<String, Object>> contents = response.jsonPath().get("PLAYABLE_CONTENT.results");
        for (HashMap<String, Object> content : contents) {
            if (!content.get("programType").toString().equals("LIVETVMOVIE")) {
                contentInfo.add(content.get(key).toString());
            }

        }
        return contentInfo;
    }


    public static List<String> getWatchListData(Response response) {
        List<String> titles = new LinkedList<>();
        ArrayList<HashMap> data = response.jsonPath().get("userContentSyncResponseList");

        // Add Movies and TV SHows
        titles.addAll(data.stream().filter(m -> String.valueOf(m.get("fav")).trim().equals("true"))
                .filter(m -> !((HashMap) m.get("contentResponse")).get("programType").toString().trim()
                        .equals("EPISODE"))
                .map(m -> ((HashMap) m.get("contentResponse")).get("title").toString()).collect(
                        Collectors.toList()));

        // Add Episodes
        titles.addAll(data.stream().filter(m -> String.valueOf(m.get("fav")).trim().equals("true"))
                .filter(m -> ((HashMap) m.get("contentResponse")).get("programType").toString().trim()
                        .equals("EPISODE"))
                .map(m -> ((HashMap) m.get("contentResponse")).get("episodeTvShowName").toString()).collect(
                        Collectors.toList()));
        return titles;
    }

    //scheduleRail

    public static Set<String> getScheduleRailContentNameWithTime(Response EPGResponse, String contentID) throws ParseException {
        Set<String> contentInfo = new HashSet<>();
        List<HashMap<String, Object>> contents = EPGResponse.jsonPath().get("programGuide." + contentID);
        for (HashMap<String, Object> content : contents) {
            Timestamp ts = new Timestamp(Long.valueOf(content.get("startTime").toString()));
            Timestamp ts2 = new Timestamp(Long.valueOf(content.get("endTime").toString()));
            Timestamp date = ts;
            String startTime = new SimpleDateFormat("EEE dd MMM | h:mm a", Locale.ENGLISH).format(date).replace("AM", "am").replace("PM", "pm");
            date = ts2;
            String endTime = new SimpleDateFormat("h:mm a", Locale.ENGLISH).format(date).replace("AM", "am").replace("PM", "pm");
            contentInfo.add(content.get("title").toString() + " - " + startTime + " - " + endTime);
            System.out.println(content.get("title").toString() + " - " + startTime + " - " + endTime);
        }
        return contentInfo;
    }

    public static int getContentCount(Response searchApiResponse) {
       return searchApiResponse.jsonPath().get("totalResults");
    }
}


