package gruppe6.eksamensprojekt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

    @GetMapping(value = "/")
    public String dashboard(){
        return "dashboard.html";
    }
}
