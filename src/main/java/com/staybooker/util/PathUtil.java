package com.staybooker.util;

public class PathUtil {
    private static final String JSP_PATH = "WEB-INF/jsp/";
    private static final String STATIC_PATH = "static";

    public static String getJspPage(String pageName) {
        return JSP_PATH + pageName;
    }

    public static String getStaticPage(String folder, String filename) {
        return "%s/%s/%s".formatted(STATIC_PATH, folder, filename);
    }
}
