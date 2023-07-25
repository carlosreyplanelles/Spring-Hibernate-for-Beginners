package com.luv2code.springboot.demoSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showLogin")
    public String showCustomLogin(){
        return "fancy-login";
    }

    //Request mapping fo access denied page
    @GetMapping("/access-denied")
    public String showAccessDenied(){
        return "access-denied";
    }
}
