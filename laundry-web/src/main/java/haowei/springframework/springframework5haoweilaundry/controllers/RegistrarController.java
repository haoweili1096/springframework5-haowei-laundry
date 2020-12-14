package haowei.springframework.springframework5haoweilaundry.controllers;

import haowei.springframework.springframework5haoweilaundry.model.Registrar;
import haowei.springframework.springframework5haoweilaundry.services.RegistrarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registrars")
public class RegistrarController {

    public static final String CREATE_OR_UPDATE_REGISTRAR_FORM = "registrars/createOrUpdateRegistrarForm";

    private final RegistrarService registrarService;

    public RegistrarController(RegistrarService registrarService) {
        this.registrarService = registrarService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        // we do not want web forms to manipulate the ID property
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String registrarsList(Model model){
        model.addAttribute("registrars", registrarService.findAll());
        return "registrars/index";
    }

    @GetMapping("/new")
    public String initCreationForm(Model model){
        model.addAttribute("registrar", Registrar.builder().build());
        return CREATE_OR_UPDATE_REGISTRAR_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(Registrar registrar, BindingResult result){
        if(result.hasErrors()){
            return CREATE_OR_UPDATE_REGISTRAR_FORM;
        }
        else{
            registrarService.save(registrar);
            return "redirect:/registrars";
        }
    }

    @GetMapping("/{registrarId}/edit")
    public String initUpdateRegistrarForm(Model model, @PathVariable Long registrarId){
        model.addAttribute("registrar", registrarService.findById(registrarId));
        return CREATE_OR_UPDATE_REGISTRAR_FORM;
    }

    @PostMapping("/{registrarId}/edit")
    public String processUpdateRegistrarForm(Registrar registrar, BindingResult result, @PathVariable Long registrarId){
        if(result.hasErrors()){
            return CREATE_OR_UPDATE_REGISTRAR_FORM;
        }
        else{
            registrar.setId(registrarId);
            registrarService.save(registrar);
            return "redirect:/registrars";
        }
    }
}
