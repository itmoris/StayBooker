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
            String staticPath = PathUtil.getStaticPage(folder, filename);

            try(InputStream is = getServletContext().getResourceAsStream(staticPath)) {
                if (is != null) {
                    is.transferTo(resp.getOutputStream());
                    return;
                }
            }
        }

        super.doGet(req, resp);
    }
}
