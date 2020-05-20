package com.petclinic.service.jdbc;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petclinic.convertor.ModelDTOConvertor;
import com.petclinic.dto.PetDTO;
import com.petclinic.model.Pet;
import com.petclinic.repository.jdbc.PetRepository;
import com.petclinic.service.PetService;

@Service
@Profile({"jdbc"})
public class PetServiceImpl implements PetService {
	
	private PetRepository petRepository;
	private ModelDTOConvertor convertor;

	public PetServiceImpl(PetRepository petRepository 
						, ModelDTOConvertor convertor) {
		this.petRepository = petRepository;
		this.convertor = convertor;
	}



	@Override
	@Transactional(readOnly = true)
	public PetDTO findById(String id) {
		
		Pet pet = petRepository.findById(Long.valueOf(id)).get(); 

		return convertor.convert(pet);
	}



	@Override
	public List<PetDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public PetDTO save(PetDTO object) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void delete(PetDTO object) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}


	
	

	
}
