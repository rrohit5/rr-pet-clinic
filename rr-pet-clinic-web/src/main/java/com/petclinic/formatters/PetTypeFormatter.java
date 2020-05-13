package com.petclinic.formatters;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.petclinic.commands.PetTypeCommand;
import com.petclinic.model.PetType;
import com.petclinic.service.PetTypeService;

@Component
public class PetTypeFormatter implements Formatter<PetTypeCommand> {

    private final PetTypeService petTypeService;
    
    ModelMapper mapper = new ModelMapper();

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public String print(PetTypeCommand petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetTypeCommand parse(String text, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = petTypeService.findAll();

        for (PetType type : findPetTypes) {
            if (type.getName().equals(text)) {
            	
            	PetTypeCommand typeCmd = mapper.map(type, PetTypeCommand.class);
            	
                return typeCmd;
            }
        }

        throw new ParseException("type not found: " + text, 0);
    }
}
