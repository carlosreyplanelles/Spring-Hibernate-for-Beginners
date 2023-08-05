package com.luv2code.springboot.demoSecurity.controller;

import com.luv2code.springboot.demoSecurity.entity.User;
import com.luv2code.springboot.demoSecurity.service.UserService;
import com.luv2code.springboot.demoSecurity.user.WebUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private Logger logger = Logger.getLogger(getClass().getName());

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showRegistrationForm(Model model){
        model.addAttribute("webUser", new WebUser());

        return "register/registration-form";

    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("webUser") WebUser webUser,
            BindingResult bindingResult,
            HttpSession session, Model model){
        String userName = webUser.getUserName();
        logger.info("Processing registration form for: " + userName);

        //form Validation
        if(bindingResult.hasErrors()){
            return "register/registration-form";
        }

        //Check if the user already exists
        User dbUser = userService.findByUsername(userName);
        if(dbUser!=null){
            model.addAttribute("webUser", new WebUser());
            model.addAttribute("registrationError", "User name already exists.");

            logger.warning("User name already exists.");
            return "register/registration-form";
        }

        //create user account and store in database
        userService.save(webUser);
        logger.info("Successfully created user: " + userName);

        // place user in the web http session for later use
        session.setAttribute("user", webUser);

        return "register/registration-confirmation";
    }
}
