package dk.kea.stud.fourplayers.restaurantstore.security;

import dk.kea.stud.fourplayers.restaurantstore.model.BusinessDetails;
import dk.kea.stud.fourplayers.restaurantstore.model.CategoryRepository;
import dk.kea.stud.fourplayers.restaurantstore.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {
    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(authentication.getName());
        BusinessDetails businessDetails;
        if (user.getBusinessDetails() == null) {
            businessDetails = new BusinessDetails();
        } else {
            businessDetails = user.getBusinessDetails();
        }
        model.addAttribute("businessDetails", businessDetails);
        return "profile/viewProfile";
    }

    @PostMapping("/profile/update")
    public String setProfile(@ModelAttribute BusinessDetails businessDetails)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(authentication.getName());
        user.setBusinessDetails(businessDetails);
        userService.saveUser(user);
        return "redirect:/profile";
    }
}
