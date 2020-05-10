package com.petclinic.controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.petclinic.commands.PetCommand;
import com.petclinic.commands.VisitCommand;
import com.petclinic.model.Pet;
import com.petclinic.model.Visit;
import com.petclinic.service.PetService;
import com.petclinic.service.VisitService;

@Controller
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;
    
    ModelMapper mapper = new ModelMapper();

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void dataBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");

        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException{
                setValue(LocalDate.parse(text));
            }
        });
    }

    /**
     * Called before each and every @RequestMapping annotated method.
     * 2 goals:
     * - Make sure we always have fresh data
     * - Since we do not use the session scope, make sure that Pet object always has an id
     * (Even though id is not part of the form fields)
     *
     * @param petId
     * @return Pet
     */
    @ModelAttribute("visit")
    public VisitCommand loadPetWithVisit(@PathVariable("petId") Long petId, Map<String, Object> model) {
        
    	Pet pet = petService.findById(petId);
    	
    	PetCommand petCommand = mapper.map(pet, PetCommand.class);
    	
        VisitCommand visitCommand = new VisitCommand();
        petCommand.addVisit(visitCommand);
        
        
        
        model.put("pet", petCommand);
                
        return visitCommand;
    }

    // Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable("petId") Long petId, Map<String, Object> model) {
    	
        return "pets/createOrUpdateVisitForm";
    }

    // Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid @ModelAttribute("visit") VisitCommand visitCommand
    									, BindingResult result) {
    	
        if (result.hasErrors()) {
        	
            return "pets/createOrUpdateVisitForm";
            
        } else {
        	
        	Visit visit = mapper.map(visitCommand, Visit.class);
        	
            visitService.save(visit);

            return "redirect:/owners/{ownerId}";
        }
    }
}
