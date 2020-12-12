package haowei.springframework.springframework5haoweilaundry.controllers;

import haowei.springframework.springframework5haoweilaundry.model.Cloth;
import haowei.springframework.springframework5haoweilaundry.model.Visit;
import haowei.springframework.springframework5haoweilaundry.services.ClothService;
import haowei.springframework.springframework5haoweilaundry.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

@Controller
@RequestMapping("/owners/{ownerId}/clothes/{clothId}")
public class VisitController {
    public static final String CREATE_OR_UPDATE_VISIT_FORM = "visits/createOrUpdateVisitForm";
    private final VisitService visitService;
    private final ClothService clothService;

    public VisitController(VisitService visitService, ClothService clothService) {
        this.visitService = visitService;
        this.clothService = clothService;
    }

    @InitBinder
    public void dataBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");

        webDataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException{
                setValue(LocalDate.parse(text));
            }
        });
    }

    @ModelAttribute("cloth")
    public Cloth findCloth(@PathVariable("clothId") Long clothId){
        return clothService.findById(clothId);
    }

    @GetMapping("/visits/new")
    public String initCreationForm(@PathVariable("clothId") Long clothId, Model model){
        Visit visit = new Visit();
        Cloth cloth = clothService.findById(clothId);
        visit.setCloth(cloth);
        cloth.getVisits().add(visit);
        model.addAttribute("visit", visit);
        return CREATE_OR_UPDATE_VISIT_FORM;
    }

    @PostMapping("/visits/new")
    public String processCreationForm(Cloth cloth, Visit visit, BindingResult result, Model model){
        visit.setCloth(cloth);
        cloth.getVisits().add(visit);
        if(result.hasErrors()){
            model.addAttribute("visit", visit);
            return CREATE_OR_UPDATE_VISIT_FORM;
        }
        else{
            visitService.save(visit);
            return "redirect:/owners/{ownerId}";
        }
    }

    @GetMapping("/visits/{visitId}/edit")
    public String initUpdateForm(@PathVariable("visitId") Long visitId, Model model){
        model.addAttribute("visit", visitService.findById(visitId));
        return CREATE_OR_UPDATE_VISIT_FORM;
    }

    @PostMapping("/visits/{visitId}/edit")
    public String processUpdateForm(@PathVariable Long visitId, Model model, Visit visit, Cloth cloth, BindingResult result){
        visit.setCloth(cloth);
        if(result.hasErrors()){
            model.addAttribute("visit", visit);
            return CREATE_OR_UPDATE_VISIT_FORM;
        }
        else{
            visit.setId(visitId);
            visitService.save(visit);
            return "redirect:/owners/{ownerId}";
        }
    }
}
