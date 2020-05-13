package com.petclinic.repository.jdbc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import com.petclinic.model.Owner;
import com.petclinic.model.Pet;
import com.petclinic.repository.jdbc.util.EntityUtils;

@Repository
@Profile({"jdbc"})
public class JdbcPetRepositoryImpl implements PetRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertPet;

    private OwnerRepository ownerRepository;



    @Autowired
    public JdbcPetRepositoryImpl(DataSource dataSource
    						, OwnerRepository ownerRepository
    						, VisitRepository visitRepository) {
    	
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        this.insertPet = new SimpleJdbcInsert(dataSource)
            .withTableName("pets")
            .usingGeneratedKeyColumns("id");

        this.ownerRepository = ownerRepository;
    }

    

    @Override
    public Optional<Pet> findById(Long id) throws DataAccessException {
    	
        Long ownerId;
        
        try {
        	
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            
            String query = "SELECT owner_id FROM pets WHERE id=:id";
            
            ownerId = this.namedParameterJdbcTemplate.queryForObject(query, params, Long.class);
            
        } catch (EmptyResultDataAccessException ex) {
        	
            throw new ObjectRetrievalFailureException(Pet.class, id);
            
        }
        
        Optional<Owner> optOwner = this.ownerRepository.findById(ownerId);
        
        Owner owner = optOwner.get();
        
        return Optional.ofNullable(EntityUtils.getById(owner.getPets(), Pet.class, id));
    }

    @Override
    public Pet save(Pet pet) throws DataAccessException {
    	
        if (pet.isNew()) {
        	
            Number newKey = this.insertPet.executeAndReturnKey(createPetParameterSource(pet));
            pet.setId(newKey.longValue());
            
        } else {
        	
        	String query = "UPDATE 	pets "
        				+ " SET 	name=:name"
        				+ "			, birth_date=:birth_date"
        				+ "			, type_id=:type_id"
        				+ "			, owner_id=:owner_id "
        				+ " WHERE 	id=:id"; 
        	
            this.namedParameterJdbcTemplate.update(query, createPetParameterSource(pet));
        }
        
        return pet;
    }
    

    /**
     * Creates a {@link MapSqlParameterSource} based on data values from the supplied {@link Pet} instance.
     */
    private MapSqlParameterSource createPetParameterSource(Pet pet) {
    
    	return new MapSqlParameterSource()
			            .addValue("id", pet.getId())
			            .addValue("name", pet.getName())
			            .addValue("birth_date", pet.getBirthDate())
			            .addValue("type_id", pet.getPetType().getId())
			            .addValue("owner_id", pet.getOwner().getId());
    	
    }

}
