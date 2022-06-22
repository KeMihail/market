package com.epam.web.controller.user;

import com.epam.service.DefaultService;
import com.epam.springdata.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource(name = "userService")
    DefaultService service;

    @GetMapping(value = "/showForm")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute("user") User user, Model model) {
        service.save(user);
        model.addAttribute("users", service.getAll());
        return "users";
    }

    @GetMapping(value = "/update/{id}")
    public ModelAndView update(@PathVariable(value = "id") Integer id) {
        User user = (User) service.getById(id);
        ModelAndView modelAndView = new ModelAndView("form");
        modelAndView.addObject("user", user);
        service.save(user);
        return modelAndView;
    }

    @GetMapping(value = "/{id}")
    public String getById(@PathVariable(value = "id") Integer id,
                          Model model) {
        User user = (User) service.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("readonly", true);
        return "form";
    }

    @GetMapping(value = "/users")
    public String getAll(Model model) {
        List<User> users = service.getAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id, Model model) {
        User user = (User) service.getById(id);
        service.delete(user);
        model.addAttribute("users", service.getAll());
        return "users";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {

        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {

            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        });
    }
}
