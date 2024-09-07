package com.staybooker.servlet;

import com.staybooker.dto.UserRegistrationDto;
import com.staybooker.exception.ValidationException;
import com.staybooker.service.UserService;
import com.staybooker.util.PathUtil;
import com.staybooker.validator.RegistrationValidator;
import com.staybooker.validator.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.staybooker.mapper.UserMapper.toUserRegistrationDto;

@Slf4j
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = UserService.getINSTANCE();
    private final Validator<UserRegistrationDto> validator = RegistrationValidator.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PathUtil.getJspPage("registration.jsp")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRegistrationDto dto = toUserRegistrationDto(req);

        try {
            validator.validate(dto);
            userService.registration(dto);
            req.setAttribute("successful", true);
        } catch (ValidationException e) {
            req.setAttribute("hasError", true);
            req.setAttribute("error", e.getMessage());
            req.setAttribute("fullName", dto.getFullName());
            req.setAttribute("email", dto.getEmail());
        }

        doGet(req, resp);
    }
}
