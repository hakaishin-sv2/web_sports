package com.example.web_sports.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/admin")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AdminController {
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String createUserForm() {
        //log.info("Navigating to create user form page");
        return "admin/index";
    }
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String not_found_404() {
        //log.info("Navigating to create user form page");
        return "admin/404";
    }
    @RequestMapping(value = "/user-manager", method = RequestMethod.GET)
    public String user_managerment() {
        //log.info("Navigating to create user form page");
        return "admin/user-manager";
    }
    @RequestMapping(value = "/user-update/{id}", method = RequestMethod.GET)
    public String user_update() {
        //log.info("Navigating to create user form page");
        return "admin/user-update";
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public String showProfile() {
        //log.info("Navigating to create user form page");
        return "admin/profile";
    }

//    @RequestMapping(value = "/edit-profile/{id}", method = RequestMethod.GET)
//    public String editProfile() {
//        //log.info("Navigating to create user form page");
//        return "admin/edit-profile";
//    }


 //test upload 1 file
    @RequestMapping(value = "/edit-profile/{id}", method = RequestMethod.GET)
    public String editProfile() {
        //log.info("Navigating to create user form page");
        return "admin/test-upload";
    }
    // up nhiều file
//@RequestMapping(value = "/edit-profile/{id}", method = RequestMethod.GET)
//public String editProfile() {
//    //log.info("Navigating to create user form page");
//    return "admin/uploa-multiple";
//}

}
