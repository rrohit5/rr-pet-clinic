package com.petclinic.service.sdjpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.model.Owner;
import com.petclinic.repository.sdjpa.OwnerRepositorySDJPA;
import com.petclinic.service.OwnerService;

@Service
@Profile("sdjpa")
public class OwnerServiceSDJPA implements OwnerService {

    private final OwnerRepositorySDJPA ownerRepository;

    public OwnerServiceSDJPA(OwnerRepositorySDJPA ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }

    @Override
    public List<Owner> findAll() {
        List<Owner> owners = new ArrayList<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long aLong) {
        
    /*2&3*/	//Optional<Owner> optionalOwner = ownerRepository.findById(aLong);
   	/*2*/	//return optionalOwner.get();
    /*3*/	//return optionalOwner.orElse(null);
    	
    /*1*/	return ownerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        ownerRepository.deleteById(aLong);
    }
}
