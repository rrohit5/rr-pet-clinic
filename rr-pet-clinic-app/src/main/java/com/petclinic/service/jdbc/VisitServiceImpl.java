package com.petclinic.service.jdbc;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petclinic.convertor.ModelDTOConvertor;
import com.petclinic.dto.VisitDTO;
import com.petclinic.model.Visit;
import com.petclinic.repository.jdbc.VisitRepository;
import com.petclinic.service.VisitService;

@Service
@Profile({"jdbc"})
public class VisitServiceImpl implements VisitService {
	
	private VisitRepository visitRepository;
	private ModelDTOConvertor convertor;

	public VisitServiceImpl(VisitRepository visitRepository
						, ModelDTOConvertor convertor) {
		this.visitRepository = visitRepository;
		this.convertor = convertor;
	}

	@Override
	@Transactional
	public VisitDTO save(VisitDTO visitDTO) {
		
		Visit visit = convertor.convert(visitDTO);
		visit = visitRepository.save(visit);
		
		return convertor.convert(visit);
	}

	
	@Override
	public List<VisitDTO> findVisitsByPetId(Long petId) {
		
		List<VisitDTO> list = visitRepository.findByPetId(petId)
									.stream()
									.map((p) -> convertor.convert(p))
									.collect(Collectors.toList()); 
		
		return list;
	}

	@Override
	public List<VisitDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VisitDTO findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(VisitDTO object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

}
