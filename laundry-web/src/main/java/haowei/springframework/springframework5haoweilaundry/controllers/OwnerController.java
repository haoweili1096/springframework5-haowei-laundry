package haowei.springframework.springframework5haoweilaundry.controllers;

import haowei.springframework.springframework5haoweilaundry.model.Owner;
import haowei.springframework.springframework5haoweilaundry.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    public static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    // use spring to automatically bind web variables to objects
    // that are coming into the application.
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        // we do not want web forms to manipulate the ID property
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/find")
    public String findOwners(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){
        // allow parameterless GET request for /owners to return all records
        if(owner.getLastName() == null){
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        // don't forget to add %
        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
        if(results.isEmpty()){
            // no result found
            result.rejectValue("lastName", "notfound", "Not Found");
            return "owners/findOwners";
        }
        else if(results.size() == 1){
            // only 1 owner found
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        }
        else{
            // multiple owners found
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") long ownerId){
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(Owner owner, BindingResult result){
        if(result.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }
        else{
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(Model model, @PathVariable long ownerId){
        model.addAttribute(ownerService.findById(ownerId));
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(Owner owner, BindingResult result, @PathVariable long ownerId){
        if(result.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }
        else{
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }
}
