package com.staybooker.servlet;

import com.staybooker.model.dto.ApartmentBriefDto;
import com.staybooker.model.dto.ApartmentFilterDto;
import com.staybooker.exception.ValidationException;
import com.staybooker.model.mapper.ApartmentMapper;
import com.staybooker.service.ApartmentService;
import com.staybooker.util.PathUtil;
import com.staybooker.validator.ApartmentFilterValidator;
import com.staybooker.validator.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    private final ApartmentService apartmentService = ApartmentService.getINSTANCE();
    private final Validator<ApartmentFilterDto> validator = ApartmentFilterValidator.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApartmentFilterDto filterDto = ApartmentMapper.toApartmentFilterDto(req);

        try {
            validator.validate(filterDto);
            List<ApartmentBriefDto> all = apartmentService.getAll(filterDto);
            long lastPage = apartmentService.getLastPage(filterDto);

            req.setAttribute("apartments", all);
            req.setAttribute("lastPage", lastPage);
            req.setAttribute("currentPage", filterDto.getPage());
        } catch (ValidationException e) {
            req.setAttribute("hasError", true);
            req.setAttribute("error", e.getMessage());
            req.setAttribute("errorField", e.getField());
        }

        req.getRequestDispatcher(PathUtil.getJspPage("index.jsp")).forward(req, resp);
    }
}
