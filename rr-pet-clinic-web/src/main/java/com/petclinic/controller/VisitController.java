package com.petclinic.controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.Map;

import javax.validation.Valid;

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
import com.petclinic.convertor.CommandDTOConvertor;
import com.petclinic.dto.PetDTO;
import com.petclinic.dto.VisitDTO;
import com.petclinic.service.PetService;
import com.petclinic.service.VisitService;

@Controller
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;
    
    private final CommandDTOConvertor convertor;

    public VisitController(VisitService visitService, PetService petService
    						, CommandDTOConvertor convertor) {
        this.visitService = visitService;
        this.petService = petService;
        this.convertor = convertor;
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
     * - Since we do not use the session scope, make sure that PetDTO object always has an id
     * (Even though id is not part of the form fields)
     *
     * @param petId
     * @return PetDTO
     */
    @ModelAttribute("visit")
    public VisitCommand loadPetWithVisit(@PathVariable("petId") String petId, Map<String, Object> model) {
        
    	PetDTO petDTO = petService.findById(petId);
    	
    	PetCommand petCommand = convertor.convert(petDTO);
    	
        VisitCommand visitCommand = new VisitCommand();
        petCommand.addVisit(visitCommand);
        
        
        
        model.put("pet", petCommand);
                
        return visitCommand;
    }

    // Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable("petId") String petId, Map<String, Object> model) {
    	
        return "pets/createOrUpdateVisitForm";
    }

    // Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid @ModelAttribute("visit") VisitCommand visitCommand
    									, BindingResult result) {
    	
        if (result.hasErrors()) {
        	
            return "pets/createOrUpdateVisitForm";
            
        } else {
        	
        	VisitDTO visitDTO = convertor.convert(visitCommand);
        	
            visitService.save(visitDTO);

            return "redirect:/owners/{ownerId}";
        }
    }
}
