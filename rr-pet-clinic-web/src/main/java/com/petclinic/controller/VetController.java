package com.petclinic.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.petclinic.commands.PetTypeCommand;
import com.petclinic.commands.VetCommand;
import com.petclinic.model.Vet;
import com.petclinic.service.VetService;

@Controller
public class VetController {

    private final VetService vetService;
    
    ModelMapper mapper = new ModelMapper();

    public VetController(VetService vetService) {
    	
        this.vetService = vetService;
        
    }

    @RequestMapping({"/vets", "/vets/index", "/vets/index.html", "/vets.html"})
    public String listVets(Model model){

    	Set<Vet> vets = vetService.findAll();
    	
    	Set<VetCommand> vetCommands = vets.stream()
											.map(p -> mapper.map(p, VetCommand.class))
											.collect(Collectors.toSet());
    	
        model.addAttribute("vets", vetCommands);

        return "vets/index";
    }

    @GetMapping("/api/vets")
    public @ResponseBody Set<VetCommand> getVetsJson(){
    	
    	Set<Vet> vets = vetService.findAll();
    	
    	Set<VetCommand> vetCommands = vets.stream()
											.map(p -> mapper.map(p, VetCommand.class))
											.collect(Collectors.toSet());
    	

        return vetCommands;
    }
}
