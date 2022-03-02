package in.wynk.framework;


import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class XStreamLanguageMapping {


    private static Map<String, String> shortToLongTermLang = Stream.of(new String[][]{ {"kn", "kannada"}, {"ml", "malayalam"},{"ta", "tamil"}, {"te", "telugu"}}).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    private static Map<String, String> longToShortTermLang = Stream.of(new String[][]{{"kannada", "kn"}, {"malayalam", "ml"}, {"tamil", "ta"}, {"telugu", "te"}}).collect(Collectors.toMap(data -> data[0], data -> data[1]));


    public static String getShotToLongTermLanguage(String shortLang) {
        return shortToLongTermLang.get(shortLang.trim());
    }

    public static String getLongToShortTermLang(String longLang) {
        return longToShortTermLang.get(longLang.trim());
    }

}
