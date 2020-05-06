package com.petclinic.service.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.model.Specialty;
import com.petclinic.model.Vet;
import com.petclinic.service.SpecialtyService;
import com.petclinic.service.VetService;

@Service
@Profile({"default", "map"})
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService{ 
//implements CrudService<Vet, Long> {

	private final SpecialtyService specialtyService;

	public VetMapService(SpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}

	@Override
	public Vet save(Vet object) {
		
		//[10-feb-2020]- Vet has Specialty So add code to save Specialty as well while saving the Vet.
		if (object.getSpecialties().size() > 0) {
			object.getSpecialties().forEach(speciality -> {
				if (speciality.getId() == null) {
					Specialty savedSpecialty = specialtyService.save(speciality);
					speciality.setId(savedSpecialty.getId());
				}
			});
		}
		
		
		//remove ID parameter as we are generating it automatically
		return super.save(object);
//		return super.save(object.getId(), object);
	}

	@Override
	public Set<Vet> findAll() {
		return super.findAll();
	}

	@Override
	public Vet findById(Long id) {
		return super.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Vet object) {
		super.delete(object);
	}

}
