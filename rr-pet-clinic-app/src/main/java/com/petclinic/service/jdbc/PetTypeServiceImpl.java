package com.petclinic.service.jdbc;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petclinic.convertor.ModelDTOConvertor;
import com.petclinic.dto.PetTypeDTO;
import com.petclinic.model.PetType;
import com.petclinic.repository.jdbc.PetTypeRepository;
import com.petclinic.service.PetTypeService;

@Service
@Profile({"jdbc"})
public class PetTypeServiceImpl implements PetTypeService {
	
	private PetTypeRepository petTypeRepository;
	private ModelDTOConvertor convertor;
	
	
	public PetTypeServiceImpl(PetTypeRepository petTypeRepository
								, ModelDTOConvertor convertor) {
		this.petTypeRepository = petTypeRepository;
		this.convertor = convertor;
	}
	
	

	@Override
	@Transactional(readOnly = true)
	public List<PetTypeDTO> findAll() {
		
		List<PetTypeDTO> list = petTypeRepository.findPetTypes()
									.stream()
									.map((p) -> convertor.convert(p))
									.collect(Collectors.toList());
		return list;
	}

	
	@Override
	public PetTypeDTO save(PetTypeDTO petTypeDTO) {
		
		PetType toSave = convertor.convert(petTypeDTO);
		
		toSave = petTypeRepository.save(toSave);
		
		return convertor.convert(toSave);
	}



	@Override
	public PetTypeDTO findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void delete(PetTypeDTO object) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

}
