package haowei.springframework.springframework5haoweilaundry.controllers;

import haowei.springframework.springframework5haoweilaundry.model.Cloth;
import haowei.springframework.springframework5haoweilaundry.model.ClothType;
import haowei.springframework.springframework5haoweilaundry.model.Owner;
import haowei.springframework.springframework5haoweilaundry.services.ClothService;
import haowei.springframework.springframework5haoweilaundry.services.ClothTypeService;
import haowei.springframework.springframework5haoweilaundry.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class ClothController {
    public static final String VIEWS_CLOTHES_CREATE_OR_UPDATE_FORM = "clothes/createOrUpdateClothForm";

    private final ClothService clothService;
    private final ClothTypeService clothTypeService;
    private final OwnerService ownerService;

    public ClothController(ClothService clothService, ClothTypeService clothTypeService, OwnerService ownerService) {
        this.clothService = clothService;
        this.clothTypeService = clothTypeService;
        this.ownerService = ownerService;
    }

    // @ModelAttribute will be executed before any method in this controller
    // here it is the same as get a variable
    @ModelAttribute("types")
    public Collection<ClothType> populateClothTypes(){
        return clothTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId){
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }

    @GetMapping("/clothes/new")
    public String initCreationForm(Model model, Owner owner){
        Cloth cloth = new Cloth();
        owner.getClothes().add(cloth);
        cloth.setOwner(owner);// if missing this line, the owner in new cloth page will be null null
        model.addAttribute("cloth", cloth);
        return VIEWS_CLOTHES_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/clothes/new")
    public String processCreationForm(Model model, Owner owner, BindingResult result, Cloth cloth){
        // these two lines are important for showing the creation in the Clothes and Visits
        cloth.setOwner(owner);
        owner.getClothes().add(cloth);
        if(result.hasErrors()){
            model.addAttribute("cloth", cloth);
            return VIEWS_CLOTHES_CREATE_OR_UPDATE_FORM;
        }
        else{
            clothService.save(cloth);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/clothes/{clothId}/edit")
    public String initUpdateClothForm(Model model, @PathVariable Long clothId){
        model.addAttribute("cloth", clothService.findById(clothId));
        return VIEWS_CLOTHES_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/clothes/{clothId}/edit")
    public String processUpdateClothForm(Model model, Owner owner, BindingResult result, Cloth cloth){
        cloth.setOwner(owner);
        if(result.hasErrors()){
            model.addAttribute("cloth", cloth);
            return VIEWS_CLOTHES_CREATE_OR_UPDATE_FORM;
        }
        else{
            clothService.save(cloth);
            return "redirect:/owners/" + owner.getId();
        }
    }
}
