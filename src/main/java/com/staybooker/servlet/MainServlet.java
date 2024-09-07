package com.staybooker.servlet;

import com.staybooker.dto.ApartmentFilterDto;
import com.staybooker.mapper.ApartmentMapper;
import com.staybooker.service.ApartmentService;
import com.staybooker.util.JspUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    private final ApartmentService apartmentService = ApartmentService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApartmentFilterDto filterDto = ApartmentMapper.toApartmentFilterDto(req);
        req.setAttribute("apartments", apartmentService.getAll(filterDto));
        req.setAttribute("lastPage", apartmentService.getLastPage(filterDto));
        req.setAttribute("currentPage", filterDto.getPage());

        req.getRequestDispatcher(JspUtil.getPage("index.jsp")).forward(req, resp);
    }
}
