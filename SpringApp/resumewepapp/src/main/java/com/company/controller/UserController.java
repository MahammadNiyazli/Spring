package com.company.controller;

import com.company.entity.User;
import com.company.form.UserForm;
import com.company.service.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServiceInter userService;

//    @RequestMapping(method = RequestMethod.GET)
//    public ModelAndView index(@RequestParam(value = "name" , required = false) String name,
//                        @RequestParam(value = "surname" , required = false) String surname,
//                        @RequestParam(value = "nId" , required = false) Integer nId){
//
//        try {
//            List<User> list = userService.getAll(name,surname,nId);
//            ModelAndView mv = new ModelAndView("users");
//            mv.addObject("list",list);
//            return mv;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(@Valid @ModelAttribute("user") UserForm u , BindingResult result){


        try {

            ModelAndView mv = new ModelAndView("users");

            List<User> list = userService.getAll(u.getName(),u.getSurname(),u.getNationalityId());
            mv.addObject("list",list);
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @ModelAttribute("user")
    public UserForm getEmptyUserForm(){
        return new UserForm();

    }
}
