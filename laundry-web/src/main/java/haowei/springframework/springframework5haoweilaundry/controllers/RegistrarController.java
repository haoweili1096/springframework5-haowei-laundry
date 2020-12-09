package haowei.springframework.springframework5haoweilaundry.controllers;

import haowei.springframework.springframework5haoweilaundry.services.RegistrarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrarController {

    private final RegistrarService registrarService;

    public RegistrarController(RegistrarService registrarService) {
        this.registrarService = registrarService;
    }

    @RequestMapping({"/registrars", "/registrars/index", "registrars/index.html"})
    public String registrarsList(Model model){
        model.addAttribute("registrars", registrarService.findAll());
        return "registrars/index";
    }
}
