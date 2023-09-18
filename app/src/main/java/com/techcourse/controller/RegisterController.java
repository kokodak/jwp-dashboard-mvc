package com.techcourse.controller;

import com.techcourse.domain.User;
import com.techcourse.repository.InMemoryUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.org.springframework.web.bind.annotation.RequestMapping;
import web.org.springframework.web.bind.annotation.RequestMethod;
import webmvc.org.springframework.web.servlet.ModelAndView;
import webmvc.org.springframework.web.servlet.mvc.asis.Controller;
import webmvc.org.springframework.web.servlet.view.JspView;

@context.org.springframework.stereotype.Controller
public class RegisterController implements Controller {

    @Override
    public String execute(final HttpServletRequest req,
                          final HttpServletResponse res) throws Exception {
        final var user = new User(2,
                                  req.getParameter("account"),
                                  req.getParameter("password"),
                                  req.getParameter("email"));
        InMemoryUserRepository.save(user);

        return "redirect:/index.jsp";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView save(final HttpServletRequest request,
                             final HttpServletResponse response) throws Exception {
        final String viewName = execute(request, response);
        return new ModelAndView(new JspView(viewName));
    }
}
