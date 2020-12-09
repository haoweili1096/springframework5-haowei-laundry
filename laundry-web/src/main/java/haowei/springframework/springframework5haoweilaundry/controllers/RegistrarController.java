package haowei.springframework.springframework5haoweilaundry.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrarController {
    @RequestMapping({"/registrars", "/registrars/index", "registrars/index.html"})
    public String registrarsList(){
        return "registrars/index";
    }
}
