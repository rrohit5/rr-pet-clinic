package com.petclinic.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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
import com.petclinic.model.Owner;
import com.petclinic.service.OwnerService;

@RequestMapping("/owners")
@Controller
public class OwnerController {
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    private final OwnerService ownerService;
    
    ModelMapper mapper = new ModelMapper();

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

//	@RequestMapping({"/owners", "/owners/index", "/owners/index.html"})
//	@RequestMapping({"", "/", "/index", "/index.html"})  //@RequestMapping at class level + this one
//														 // need to add ""
//	public String listOwners(Model model) {
//		
//		model.addAttribute("owners", ownerService.findAll());
//		
//		return "owners/index";
//	}
    
    
    
    
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
        	
        	Owner owner = mapper.map(ownerCommand, Owner.class);
        	
            Owner savedOwner =  ownerService.save(owner);
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
//        List<Owner> results = ownerService.findByLastName(ownerCommand.getLastName());
        List<Owner> results = ownerService.findAllByLastNameLike("%"+ ownerCommand.getLastName() + "%");

        if (results.isEmpty()) {
        	
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
            
        } else if (results.size() == 1) {
        	
            // 1 owner found
            Owner owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
            
        } else {
        	
            // multiple owners found
            model.addAttribute("selections", results);
            return "owners/ownersList";
            
        }
	}
    
    
    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
    	
    	OwnerCommand ownerCommand = mapper.map(ownerService.findById(ownerId), OwnerCommand.class);
    	
        model.addAttribute("owner", ownerCommand);
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid @ModelAttribute("owner") OwnerCommand ownerCommand
    									, BindingResult result, @PathVariable Long ownerId) {
    	
        if (result.hasErrors()) {
        	
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
            
        } else {
        	
        	Owner owner = mapper.map(ownerCommand, Owner.class);
        	
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
            
        }
    }

	
	
	@GetMapping("/{ownerId}")
//    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
	 public ModelAndView showOwner(@PathVariable Long ownerId) {
		
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
        
    }
	
	
	 

    

   
}
