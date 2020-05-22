package com.petclinic.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petclinic.commands.OwnerCommand;
import com.petclinic.commands.PetCommand;
import com.petclinic.commands.PetTypeCommand;
import com.petclinic.convertor.CommandDTOConvertor;
import com.petclinic.dto.OwnerDTO;
import com.petclinic.dto.PetDTO;
import com.petclinic.dto.PetTypeDTO;
import com.petclinic.service.OwnerService;
import com.petclinic.service.PetService;
import com.petclinic.service.PetTypeService;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;
    private final CommandDTOConvertor convertor;
    
    
    public PetController(PetService petService, OwnerService ownerService
    				, PetTypeService petTypeService, CommandDTOConvertor convertor) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
        this.convertor = convertor;
    }
    
    
    
    
    
    @ModelAttribute("types")
    public Collection<PetTypeCommand> populatePetTypes() {
    	
    	Collection<PetTypeDTO> petTypes = petTypeService.findAll();
    	
    	Collection<PetTypeCommand> petTypeList = petTypes.stream()
    													.map(p -> convertor.convert(p))
    													.collect(Collectors.toList());
    	
        return petTypeList;
    }

    @ModelAttribute("owner")
    public OwnerCommand findOwner(@PathVariable("ownerId") String ownerId) {
    	
    	OwnerDTO ownerDTO = ownerService.findById(ownerId);
    	
    	OwnerCommand ownerCommand = convertor.convert(ownerDTO); 
    	
    	return ownerCommand;
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    
    
//    @InitBinder("pet")
//    public void initPetBinder(WebDataBinder dataBinder) {
//        dataBinder.setValidator(new PetValidator());
//    }
    
    
    

    @GetMapping("/pets/new")
    public String initCreationForm(@ModelAttribute("owner") OwnerCommand ownerCommand, Model model) {
    	
    	
    	PetCommand petCommand = new PetCommand();
    	ownerCommand.addPet(petCommand);
        model.addAttribute("pet", petCommand);
        
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(@ModelAttribute("owner") OwnerCommand ownerCommand
    							, @Valid @ModelAttribute("pet") PetCommand petCommand
    								, BindingResult result, Model model) {
    	
    	
        if (StringUtils.hasLength(petCommand.getName()) 
        					&& petCommand.isNew() 
        					&& ownerCommand.getPet(petCommand.getName(), true) != null){
        	
            result.rejectValue("name", "duplicate", "already exists");
            
        }
        
        if (result.hasErrors()) {
        	
            model.addAttribute("pet", petCommand);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
            
        } else {
        	
        	OwnerDTO ownerDTO = convertor.convert(ownerCommand);
        	
        	PetDTO petDTO = convertor.convert(petCommand);
        	
        	ownerDTO.addPet(petDTO);
        	
            petService.save(petDTO);

            return "redirect:/owners/" + ownerCommand.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable String petId, Model model) {
    	
    	PetDTO petDTO = petService.findById(petId);
    	
    	PetCommand petCommand = convertor.convert(petDTO);
    	
        model.addAttribute("pet", petCommand);
        
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Valid @ModelAttribute("pet") PetCommand petCommand
    								, BindingResult result
    								, @ModelAttribute("owner") OwnerCommand ownerCommand, Model model) {
    	
        if (result.hasErrors()) {
        	
            model.addAttribute("pet", petCommand);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
            
        } else {
        	
        	PetDTO petDTO = convertor.convert(petCommand);
        	
        	OwnerDTO ownerDTO = convertor.convert(ownerCommand);
        	
        	ownerDTO.addPet(petDTO);
            petService.save(petDTO);
            
//            return "redirect:/owners/{ownerId}";
            return "redirect:/owners/" + ownerCommand.getId();
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
