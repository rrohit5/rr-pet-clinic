package com.petclinic.convertor;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.petclinic.dto.OwnerDTO;
import com.petclinic.dto.PetDTO;
import com.petclinic.dto.PetTypeDTO;
import com.petclinic.dto.SpecialtyDTO;
import com.petclinic.dto.VetDTO;
import com.petclinic.dto.VisitDTO;
import com.petclinic.model.Owner;
import com.petclinic.model.Pet;
import com.petclinic.model.PetType;
import com.petclinic.model.Specialty;
import com.petclinic.model.Vet;
import com.petclinic.model.Visit;
import com.petclinic.mongo.model.OwnerMongo;
import com.petclinic.mongo.model.PetMongo;
import com.petclinic.mongo.model.PetTypeMongo;
import com.petclinic.mongo.model.SpecialtyMongo;
import com.petclinic.mongo.model.VetMongo;
import com.petclinic.mongo.model.VisitMongo;

import lombok.NonNull;

@Component
public class ModelDTOConvertor {

	ModelMapper mapper = new ModelMapper();

	public ModelDTOConvertor() {
		
		 Condition<Object, Object> notNull = ctx -> ctx.getSource() != null;

		Converter<Set<PetDTO>, Set<String>> petsConvertor = ctx -> (ctx.getSource().stream().map(PetDTO::getId)
				.collect(Collectors.toSet()));

		Converter<Set<String>, Set<PetDTO>> petsConvertor2 = ctx -> {
			
			Set<String> q = ctx.getSource();
			q = q.stream().filter(id -> id != null).collect(Collectors.toSet());
			Set<PetDTO> w = q.stream().map(id -> PetDTO.builder().id(id).build()).collect(Collectors.toSet());
			
			return w;			
		};
		
//		Converter<Set<String>, Set<PetDTO>> petsConvertor2 = ctx -> (ctx.getSource().stream().filter(id -> id == null)
//				.map(id -> new PetDTO(id)).collect(Collectors.toSet()));

		mapper.createTypeMap(OwnerDTO.class, OwnerMongo.class)
				.addMappings(q -> q.when(notNull).using(petsConvertor).map(OwnerDTO::getPets, OwnerMongo::setPets));

		mapper.createTypeMap(OwnerMongo.class, OwnerDTO.class)
				.addMappings(q -> q.when(notNull).using(petsConvertor2).map(OwnerMongo::getPets, OwnerDTO::setPets));

		Converter<Set<VisitDTO>, Set<String>> visitsConvertor = ctx -> (ctx.getSource().stream().map(VisitDTO::getId)
				.collect(Collectors.toSet()));


		Converter<Set<String>, Set<VisitDTO>> visitsConvertor2 = ctx -> {
			Set<String> q = ctx.getSource();
			q = q.stream().filter(x -> x != null).collect(Collectors.toSet());
			Set<VisitDTO> w = q.stream().map(id -> VisitDTO.builder().id(id).build()).collect(Collectors.toSet());
			
			return w;
	};

		
//		Converter<Set<String>, Set<VisitDTO>> visitsConvertor2 = ctx -> (ctx.getSource().stream().filter(x -> x != null)
//				.map(id -> new VisitDTO(id)).collect(Collectors.toSet()));

		Converter<String, OwnerDTO> ownerConvertor = ctx -> (OwnerDTO.builder().id(ctx.getSource()).build());

		mapper.createTypeMap(PetDTO.class, PetMongo.class).addMappings(q -> {
			q.when(notNull).using(visitsConvertor).map(PetDTO::getVisits, PetMongo::setVisits);
			q.when(notNull).map(src -> src.getOwner().getId(), PetMongo::setOwner);
		});

		mapper.createTypeMap(PetMongo.class, PetDTO.class).addMappings(q -> {
			q.when(notNull).using(visitsConvertor2).map(PetMongo::getVisits, PetDTO::setVisits);
			q.when(notNull).using(ownerConvertor).map(PetMongo::getOwner, PetDTO::setOwner);
			;
		});

		Converter<String, PetDTO> petConvertor = ctx -> (PetDTO.builder().id(ctx.getSource()).build());

		mapper.createTypeMap(VisitDTO.class, VisitMongo.class).addMappings(q -> {
			q.when(notNull).map(src -> src.getPet().getId(), VisitMongo::setPet);
		});

		mapper.createTypeMap(VisitMongo.class, VisitDTO.class).addMappings(q -> {
			q.when(notNull).using(petConvertor).map(VisitMongo::getPet, VisitDTO::setPet);
		});
	}

	
	public OwnerDTO convert(@NonNull Owner s) {

		OwnerDTO d = mapper.map(s, OwnerDTO.class);

		return d;
	}

	public OwnerDTO convertMongo(@NonNull OwnerMongo s) {

		OwnerDTO d = mapper.map(s, OwnerDTO.class);

		return d;
	}

	public Owner convert(@NonNull OwnerDTO s) {

		Owner d = mapper.map(s, Owner.class);

		return d;
	}

	public OwnerMongo convertMongo(@NonNull OwnerDTO s) {

		OwnerMongo d = mapper.map(s, OwnerMongo.class);

		return d;
	}

	public PetDTO convert(@NonNull Pet s) {

		PetDTO d = mapper.map(s, PetDTO.class);

		return d;
	}

	public PetDTO convertMongo(@NonNull PetMongo s) {

		PetDTO d = mapper.map(s, PetDTO.class);

		return d;
	}

	public Pet convert(@NonNull PetDTO s) {

		Pet d = mapper.map(s, Pet.class);

		return d;
	}

	public PetMongo convertMongo(@NonNull PetDTO s) {

		PetMongo d = mapper.map(s, PetMongo.class);

		return d;
	}

	public PetTypeDTO convert(@NonNull PetType s) {

		PetTypeDTO d = mapper.map(s, PetTypeDTO.class);

		return d;
	}

	public PetTypeDTO convertMongo(@NonNull PetTypeMongo s) {

		PetTypeDTO d = mapper.map(s, PetTypeDTO.class);

		return d;
	}

	public PetType convert(@NonNull PetTypeDTO s) {

		PetType d = mapper.map(s, PetType.class);

		return d;
	}

	public PetTypeMongo convertMongo(@NonNull PetTypeDTO s) {

		PetTypeMongo d = mapper.map(s, PetTypeMongo.class);

		return d;
	}

	public SpecialtyDTO convert(@NonNull Specialty s) {

		SpecialtyDTO d = mapper.map(s, SpecialtyDTO.class);

		return d;
	}

	public SpecialtyDTO convertMongo(@NonNull SpecialtyMongo s) {

		SpecialtyDTO d = mapper.map(s, SpecialtyDTO.class);

		return d;
	}

	public Specialty convert(@NonNull SpecialtyDTO s) {

		Specialty d = mapper.map(s, Specialty.class);

		return d;
	}

	public SpecialtyMongo convertMongo(@NonNull SpecialtyDTO s) {

		SpecialtyMongo d = mapper.map(s, SpecialtyMongo.class);

		return d;
	}

	public VetDTO convert(@NonNull Vet s) {

		VetDTO d = mapper.map(s, VetDTO.class);

		return d;
	}

	public VetDTO convertMongo(@NonNull VetMongo s) {

		VetDTO d = mapper.map(s, VetDTO.class);

		return d;
	}

	public Vet convert(@NonNull VetDTO s) {

		Vet d = mapper.map(s, Vet.class);

		return d;
	}

	public VetMongo convertMongo(@NonNull VetDTO s) {

		VetMongo d = mapper.map(s, VetMongo.class);

		return d;
	}

	public VisitDTO convert(@NonNull Visit s) {

		VisitDTO d = mapper.map(s, VisitDTO.class);

		return d;
	}

	public VisitDTO convertMongo(@NonNull VisitMongo s) {

		VisitDTO d = mapper.map(s, VisitDTO.class);

		return d;
	}

	public Visit convert(@NonNull VisitDTO s) {

		Visit d = mapper.map(s, Visit.class);

		return d;
	}

	public VisitMongo convertMongo(@NonNull VisitDTO s) {

		VisitMongo d = mapper.map(s, VisitMongo.class);

		return d;
	}
}
