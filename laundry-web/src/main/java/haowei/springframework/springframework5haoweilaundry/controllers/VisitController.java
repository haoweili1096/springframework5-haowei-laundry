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

    // Make sure we always have fresh data
    // Since we do not use the session scope, make sure that Cloth object always has an id
    @ModelAttribute("visit")
    public Visit loadClothWithVisit(@PathVariable("clothId") Long clothId, Model model){
        Cloth cloth = clothService.findById(clothId);
        model.addAttribute("cloth", cloth);
        Visit visit = new Visit();
        visit.setCloth(cloth);
        cloth.getVisits().add(visit);
        return visit;
    }

    @GetMapping("/owners/{ownerId}/clothes/{clothId}/visits/new")
    public String initCreationForm(@PathVariable("clothId") Long clothId, Model model){
        return CREATE_OR_UPDATE_VISIT_FORM;
    }

    @PostMapping("/owners/{ownerId}/clothes/{clothId}/visits/new")
    public String processCreationForm(Visit visit, BindingResult result){
        if(result.hasErrors()){
            return CREATE_OR_UPDATE_VISIT_FORM;
        }
        else{
            visitService.save(visit);
            return "redirect:/owners/{ownerId}";
        }
    }
}
