package com.petclinic.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
import com.petclinic.model.Owner;
import com.petclinic.model.Pet;
import com.petclinic.model.PetType;
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
    
    ModelMapper mapper = new ModelMapper();

    
    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    
    
    
    
    
    
    @ModelAttribute("types")
    public Collection<PetTypeCommand> populatePetTypes() {
    	
    	Collection<PetType> petTypes = petTypeService.findAll();
    	
    	Collection<PetTypeCommand> petTypeList = petTypes.stream()
    													.map(p -> mapper.map(p, PetTypeCommand.class))
    													.collect(Collectors.toList());
    	
        return petTypeList;
    }

    @ModelAttribute("owner")
    public OwnerCommand findOwner(@PathVariable("ownerId") Long ownerId) {
        return mapper.map(ownerService.findById(ownerId), OwnerCommand.class);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    
    
    
    
    
    

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
    								, BindingResult result, ModelMap model) {
    	
    	
        if (StringUtils.hasLength(petCommand.getName()) 
        					&& petCommand.isNew() 
        					&& ownerCommand.getPet(petCommand.getName(), true) != null){
        	
            result.rejectValue("name", "duplicate", "already exists");
            
        }
        
        ownerCommand.addPet(petCommand);
        
        if (result.hasErrors()) {
        	
            model.put("pet", petCommand);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
            
        } else {
        	
        	Owner owner = mapper.map(ownerCommand, Owner.class);
        	
        	Pet pet = mapper.map(petCommand, Pet.class);
        	
        	owner.addPet(pet);
        	
            petService.save(pet);

            return "redirect:/owners/" + ownerCommand.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model) {
    	
    	Pet pet = petService.findById(petId);
    	
    	PetCommand petCommand = mapper.map(pet, PetCommand.class);
    	
        model.addAttribute("pet", petCommand);
        
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Valid @ModelAttribute("pet") PetCommand petCommand
    								, BindingResult result
    								, @ModelAttribute("owner") OwnerCommand ownerCommand, Model model) {
    	
        if (result.hasErrors()) {
        	
//            pet.setOwner(owner);
            model.addAttribute("pet", petCommand);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
            
        } else {
        	
        	Pet pet = mapper.map(petCommand, Pet.class);
        	
        	Owner owner = mapper.map(ownerCommand, Owner.class);
        	
        	owner.addPet(pet);
            petService.save(pet);
            
            return "redirect:/owners/" + ownerCommand.getId();
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
