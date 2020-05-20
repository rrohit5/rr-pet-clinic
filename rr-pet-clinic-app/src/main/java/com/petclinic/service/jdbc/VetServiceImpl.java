package com.petclinic.service.jdbc;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petclinic.convertor.ModelDTOConvertor;
import com.petclinic.dto.VetDTO;
import com.petclinic.repository.jdbc.VetRepository;
import com.petclinic.service.VetService;

@Service
@Profile({"jdbc"})
public class VetServiceImpl implements VetService {
	
	private VetRepository vetRepository;
	private ModelDTOConvertor convertor;

	public VetServiceImpl(VetRepository vetRepository
						, ModelDTOConvertor convertor) {
		this.vetRepository = vetRepository;
		this.convertor = convertor;
	}

	@Override
	@Transactional(readOnly = true)
	public List<VetDTO> findAll() {
		
		List<VetDTO> list = vetRepository.findAll()
									.stream()
									.map((p) -> convertor.convert(p))
									.collect(Collectors.toList());
		return list;
	}

	@Override
	public VetDTO findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VetDTO save(VetDTO object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(VetDTO object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	
	
}
