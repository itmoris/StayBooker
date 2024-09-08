package com.staybooker.servlet;

import com.staybooker.model.dto.UserAuthenticateDto;
import com.staybooker.exception.AuthenticateException;
import com.staybooker.exception.ValidationException;
import com.staybooker.service.UserService;
import com.staybooker.util.PathUtil;
import com.staybooker.validator.UserAuthenticateValidator;
import com.staybooker.validator.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.staybooker.model.mapper.UserMapper.toUserAuthenticateDto;

@Slf4j
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = UserService.getINSTANCE();
    private final Validator<UserAuthenticateDto> validator = UserAuthenticateValidator.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PathUtil.getJspPage("login.jsp")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserAuthenticateDto dto = toUserAuthenticateDto(req);

        try {
            validator.validate(dto);
            userService.authenticate(dto);
            req.getSession().setAttribute("user", dto);
            resp.sendRedirect(req.getContextPath());
        } catch (AuthenticateException | ValidationException e) {
            req.setAttribute("hasError", true);
            req.setAttribute("error", e.getMessage());
            doGet(req, resp);
        }

    }
}
