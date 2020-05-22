package com.petclinic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.petclinic.commands.OwnerCommand;
import com.petclinic.convertor.CommandDTOConvertor;
import com.petclinic.dto.OwnerDTO;
import com.petclinic.service.OwnerService;

@RequestMapping("/owners")
@Controller
public class OwnerController {
	
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    private final OwnerService ownerService;
    private final CommandDTOConvertor convertor;
    
    public OwnerController(OwnerService ownerService, CommandDTOConvertor convertor) {
        this.ownerService = ownerService;
        this.convertor = convertor;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    
    
    
    @GetMapping("/new")
    public String initCreationForm(Model model) {
    	
    	OwnerCommand ownerCommand = new OwnerCommand();
        model.addAttribute("owner", ownerCommand);
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        
    }
    
    @PostMapping("/new")
    public String processCreationForm(@Valid @ModelAttribute("owner") OwnerCommand ownerCommand
    								, BindingResult result) {
    	
        if (result.hasErrors()) {
        	
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
            
        } else {
        	
        	OwnerDTO ownerDTO = convertor.convert(ownerCommand);
        	
            OwnerDTO savedOwner =  ownerService.save(ownerDTO);
            return "redirect:/owners/" + savedOwner.getId();
            
        }
    }
	
    @RequestMapping("/find")
    public String initFindForm(Model model){
    	
        model.addAttribute("owner", new OwnerCommand());
        return "owners/findOwners";
        
    }
    
    @GetMapping
    public String processFindForm(@ModelAttribute("owner") OwnerCommand ownerCommand
    		, BindingResult result, Model model){
    	
        // allow parameterless GET request for /owners to return all records
        if (ownerCommand.getLastName() == null) {
            ownerCommand.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
//        List<OwnerDTO> results = ownerService.findByLastName(ownerCommand.getLastName());
        List<OwnerDTO> results = ownerService.findAllByLastNameLike("%"+ ownerCommand.getLastName() + "%");

        if (results.isEmpty()) {
        	
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
            
        } else if (results.size() == 1) {
        	
            // 1 ownerDTO found
            OwnerDTO ownerDTO = results.get(0);
            return "redirect:/owners/" + ownerDTO.getId();
            
        } else {
        	
            // multiple owners found
            model.addAttribute("selections", results);
            return "owners/ownersList";
            
        }
	}
    
    
    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable String ownerId, Model model) {
    	
    	OwnerCommand ownerCommand = convertor.convert(ownerService.findById(ownerId));
    	
        model.addAttribute("owner", ownerCommand);
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid @ModelAttribute("owner") OwnerCommand ownerCommand
    									, BindingResult result, @PathVariable String ownerId) {
    	
        if (result.hasErrors()) {
        	
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
            
        } else {
        	
        	OwnerDTO ownerDTO = convertor.convert(ownerCommand);
        	
            ownerDTO.setId(ownerId);
            OwnerDTO savedOwner = ownerService.save(ownerDTO);
            
//            return "redirect:/owners/{ownerId}";
            return "redirect:/owners/" + savedOwner.getId();
            
        }
    }

	
	
	@GetMapping("/{ownerId}")
//    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
	 public ModelAndView showOwner(@PathVariable String ownerId) {
		
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        
        OwnerCommand ownerCommand = convertor.convert(ownerService.findById(ownerId)); 
        
        mav.addObject("owner", ownerCommand);
        return mav;
        
    }
	
	
	 

    

   
}
