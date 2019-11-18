package dk.kea.stud.fourplayers.restaurantstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping(value={"/","/index"})
    public String index(){
        return "misc/index";
    }

}
