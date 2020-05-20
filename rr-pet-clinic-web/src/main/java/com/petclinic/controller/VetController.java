package com.petclinic.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.petclinic.commands.VetCommand;
import com.petclinic.convertor.CommandDTOConvertor;
import com.petclinic.dto.VetDTO;
import com.petclinic.service.VetService;

@Controller
public class VetController {

    private final VetService vetService;
    
    private final CommandDTOConvertor convertor;

    public VetController(VetService vetService, CommandDTOConvertor convertor) {
    	
        this.vetService = vetService;
        this.convertor = convertor;
        
    }

    @RequestMapping({"/vets", "/vets/index", "/vets/index.html", "/vets.html"})
    public String showVetList(Model model){

    	List<VetDTO> vetDTOs = vetService.findAll();
    	
    	Set<VetCommand> vetCommands = vetDTOs.stream()
											.map(p -> convertor.convert(p))
											.collect(Collectors.toSet());
    	
        model.addAttribute("vets", vetCommands);

        return "vets/index";
    }

    @GetMapping({ "/vets.json", "/vets.xml"})
    public @ResponseBody Set<VetCommand> showResourcesVetList(){
    	
    	List<VetDTO> vetDTOs = vetService.findAll();
    	
    	Set<VetCommand> vetCommands = vetDTOs.stream()
											.map(p -> convertor.convert(p))
											.collect(Collectors.toSet());
    	

        return vetCommands;
    }
}
