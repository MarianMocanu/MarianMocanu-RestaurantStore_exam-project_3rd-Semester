package dk.kea.stud.fourplayers.restaurantstore.controllers;

import javax.validation.Valid;

import dk.kea.stud.fourplayers.restaurantstore.security.User;
import dk.kea.stud.fourplayers.restaurantstore.security.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with this email.");
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        } else {
            userService.saveNewUser(user);
            model.addAttribute("registrationSuccess", "User has been registered successfully.");
            model.addAttribute("registrationUsername", user.getEmail());
            System.out.println(user);
            return "login";
        }
    }

    @GetMapping("/login/success")
    public String loginSucess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            //Check if user has role user
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("USER"))) {
                User user = userService.findUserByEmail(authentication.getName());
                //CHECK IF BUSINESS DETAILS DO NOT EXIST and return the profile page
                if (user.getBusinessDetails() == null) {
                    return "redirect:/profile";
                }
                //Else return the shop view
                else {
                    return "redirect:/shop";
                }
            }
            //RETURN ADMIN DASHBOARD IF USER HAS ROLE ADMIN
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) ;
            {
                return "redirect:/admin/dashboard";
            }

        }
        return "redirect:/shop";

    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "misc/access-denied";
    }

//    @GetMapping("/admin/home")
//    public String home(Model model){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());
//        model.addAttribute("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
//        model.addAttribute("adminMessage","Content available only for admins.");
//        return "misc/admin";
//    }

}