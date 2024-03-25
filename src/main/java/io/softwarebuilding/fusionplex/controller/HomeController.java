package io.softwarebuilding.fusionplex.controller;

import io.softwarebuilding.fusionplex.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showPage() {

        return "index";
    }

    @GetMapping("/login")
    public String showLogin( final Model model,
                             @RequestParam(value = "logout", required = false) final String logout,
                             @RequestParam(value = "error", required = false) final String error ) {


        model.addAttribute( "user", new UserDto() );
        model.addAttribute( "formAction", "/login" );

        if ( logout != null ) {
            model.addAttribute( "alertMessage", "You have been successfully logged out" );
            model.addAttribute( "alertType", "success" );
        }

        if ( error != null ) {
            model.addAttribute( "alertMessage", "Invalid username or password" );
            model.addAttribute( "alertType", "danger" );
        }

        return "login";
    }

}
