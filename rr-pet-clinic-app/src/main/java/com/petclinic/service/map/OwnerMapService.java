package com.petclinic.service.map;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.model.Owner;
import com.petclinic.model.Pet;

/*
 * 1- AbstractMapService methods are not marked public while the Intf CrudService are public by default.
 * 		Both has same methods and so here mark all intf methods as public
 */
@Service
@Profile({"map"})
public class OwnerMapService extends AbstractMapService<Owner, Long>{ 

//implements CrudService<Owner, Long>{
	
	
	private final PetTypeMapService petTypeService;
    private final PetMapService petService;

    public OwnerMapService(PetTypeMapService petTypeService, PetMapService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }
    
    

	//remove ID parameter as we are generating it automatically
	//intf save mthd and abs cls save mtdh is different.. 
    //[10-feb-2020]- Changed Owner to have Set<Pet>. So add code to save pet as well while saving the owner.
    //			   - Pet Has a PetType...save that as well and get the ids if new
	@Override
	public Owner save(Owner object) {
		//[10-feb-2020]- return super.save(object);
//		return super.save(object.getId(), object);
		
		if(object != null){
            if (object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if (pet.getPetType() != null){
                        if(pet.getPetType().getId() == null){
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("Pet Type is required");
                    }

                    if(pet.getId() == null){
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }

            return super.save(object);

        } else {
            return null;
        }
		
	}

	@Override
	public List<Owner> findAll() {
		return super.findAll();
	}

	@Override
	public Owner findById(Long id) {
		return super.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Owner object) {
		super.delete(object);
	}

	public Owner findByLastName(String lastName) {
		
		 return this.findAll()
	                .stream()
	                .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
	                .findFirst()
	                .orElse(null);
	}
	
    public List<Owner> findAllByLastNameLike(String lastName) {

        //todo - impl
        return null;
    }
	

}
