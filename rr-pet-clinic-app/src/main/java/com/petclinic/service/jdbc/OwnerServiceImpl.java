package com.petclinic.service.jdbc;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petclinic.convertor.ModelDTOConvertor;
import com.petclinic.dto.OwnerDTO;
import com.petclinic.model.Owner;
import com.petclinic.repository.jdbc.OwnerRepository;
import com.petclinic.service.OwnerService;

@Service
@Profile({"jdbc"})
public class OwnerServiceImpl implements OwnerService {
	
	private OwnerRepository ownerRepository;
	
	private ModelDTOConvertor convertor;

	public OwnerServiceImpl(OwnerRepository ownerRepository
							, ModelDTOConvertor convertor) {
		this.ownerRepository = ownerRepository;
		this.convertor = convertor;
	}

	@Override
	public List<OwnerDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public OwnerDTO findById(String id) {
		
		Owner owner = ownerRepository.findById(Long.valueOf(id)).get(); 

		return convertor.convert(owner);
	}

	@Override
	@Transactional
	public OwnerDTO save(OwnerDTO ownerDTO) {

		Owner owner = convertor.convert(ownerDTO);
		
		owner = ownerRepository.save(owner);
				
		return convertor.convert(owner);
	}

	@Override
	public void delete(OwnerDTO object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<OwnerDTO> findAllByLastNameLike(String lastName) {

		List<OwnerDTO> list = ownerRepository.findAllByLastNameLike(lastName)
									.stream().map((p) -> convertor.convert(p))
									.collect(Collectors.toList());		
		return list;
	}

	@Override
	public OwnerDTO findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}
}
	
