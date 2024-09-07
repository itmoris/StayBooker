package com.staybooker.util;

public class JspUtil {
    private static final String JSP_PATH = "WEB-INF/jsp/";

    public static String getPage(String pageName) {
        return JSP_PATH + pageName;
    }
}
