package com.staybooker.servlet;

import com.staybooker.util.PathUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/static/*")
public class StaticServlet extends HttpServlet {
    private static final Pattern staticPattern = Pattern.compile(".*/static/(\\w+)/(.*)");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        Matcher matcher = staticPattern.matcher(uri);
        if (matcher.matches()) {
            String folder = matcher.group(1);
            String filename = matcher.group(2);
            System.out.println(folder + "/" + filename);

            ClassLoader classLoader = StaticServlet.class.getClassLoader();
            System.out.println(classLoader);

            try (InputStream resourceAsStream = classLoader.getResourceAsStream(PathUtil.getStaticPage(folder, filename))) {
                System.out.println(resourceAsStream);
                if (resourceAsStream != null) {
                    System.out.println(resourceAsStream.available());
                    resourceAsStream.transferTo(resp.getOutputStream());
                    return;
                }
            }
        }

        super.doGet(req, resp);
    }
}
