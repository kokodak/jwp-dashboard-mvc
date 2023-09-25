package com.techcourse.controller;

import context.org.springframework.stereotype.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.org.springframework.web.bind.annotation.RequestMapping;
import web.org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexViewController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndexView(final HttpServletRequest req,
                               final HttpServletResponse res) {
        return "index.jsp";
    }
}
