package com.staybooker.servlet;

import com.staybooker.dto.UserAuthenticateDto;
import com.staybooker.exception.AuthenticateException;
import com.staybooker.service.UserService;
import com.staybooker.util.JspUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.staybooker.mapper.UserMapper.toUserAuthenticateDto;

@Slf4j
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = UserService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspUtil.getPage("login.jsp")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserAuthenticateDto dto = toUserAuthenticateDto(req);

        try {
            userService.authenticate(dto);
            req.getSession().setAttribute("user", dto);
            resp.sendRedirect(req.getContextPath());
        } catch (AuthenticateException e) {
            req.setAttribute("hasError", true);
            req.setAttribute("error", e.getMessage());
            doGet(req, resp);
        }

    }
}
