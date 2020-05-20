package com.petclinic.convertor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.petclinic.commands.OwnerCommand;
import com.petclinic.commands.PetCommand;
import com.petclinic.commands.PetTypeCommand;
import com.petclinic.commands.SpecialtyCommand;
import com.petclinic.commands.VetCommand;
import com.petclinic.commands.VisitCommand;
import com.petclinic.dto.OwnerDTO;
import com.petclinic.dto.PetDTO;
import com.petclinic.dto.PetTypeDTO;
import com.petclinic.dto.SpecialtyDTO;
import com.petclinic.dto.VetDTO;
import com.petclinic.dto.VisitDTO;

@Component
public class CommandDTOConvertor {

	ModelMapper mapper;
	
	

	public CommandDTOConvertor() {
		this.mapper = new ModelMapper();
		
		mapper.createTypeMap(SpecialtyDTO.class, SpecialtyCommand.class)
		.addMappings(q -> q.map(SpecialtyDTO::getName, SpecialtyCommand::setSpecialtyName));		
		
		mapper.createTypeMap(SpecialtyCommand.class, SpecialtyDTO.class)
		.addMappings(q -> q.map(SpecialtyCommand::getSpecialtyName, SpecialtyDTO::setName));
	}

	public OwnerDTO convert(OwnerCommand s) {

		OwnerDTO d = mapper.map(s, OwnerDTO.class);

		return d;
	}
	
	public OwnerCommand convert(OwnerDTO s) {

		OwnerCommand d = mapper.map(s, OwnerCommand.class);

		return d;
	}
	
	
	
	
	
	
	public PetDTO convert(PetCommand s) {

		PetDTO d = mapper.map(s, PetDTO.class);

		return d;
	}
	
	public PetCommand convert(PetDTO s) {

		PetCommand d = mapper.map(s, PetCommand.class);

		return d;
	}
	
	
	
	
	

	public PetTypeDTO convert(PetTypeCommand s) {

		PetTypeDTO d = mapper.map(s, PetTypeDTO.class);

		return d;
	}
	
	public PetTypeCommand convert(PetTypeDTO s) {

		PetTypeCommand d = mapper.map(s, PetTypeCommand.class);

		return d;
	}	
	
	
	
	
	
	public SpecialtyDTO convert(SpecialtyCommand s) {

		SpecialtyDTO d = mapper.map(s, SpecialtyDTO.class);

		return d;
	}
	
	public SpecialtyCommand convert(SpecialtyDTO s) {

		SpecialtyCommand d = mapper.map(s, SpecialtyCommand.class);

		return d;
	}
	
	
	
	
	
	public VetDTO convert(VetCommand s) {

		VetDTO d = mapper.map(s, VetDTO.class);

		return d;
	}
	
	public VetCommand convert(VetDTO s) {

		VetCommand d = mapper.map(s, VetCommand.class);

		return d;
	}	
	
	
	
	
	public VisitDTO convert(VisitCommand s) {

		VisitDTO d = mapper.map(s, VisitDTO.class);

		return d;
	}
	
	public VisitCommand convert(VisitDTO s) {

		VisitCommand d = mapper.map(s, VisitCommand.class);

		return d;
	}
}
