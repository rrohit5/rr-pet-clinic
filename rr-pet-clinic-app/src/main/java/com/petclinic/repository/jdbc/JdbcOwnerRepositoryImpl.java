package com.petclinic.repository.jdbc;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import com.petclinic.model.Owner;
import com.petclinic.model.Pet;
import com.petclinic.model.PetType;
import com.petclinic.model.Visit;
import com.petclinic.repository.jdbc.jdbcmodel.JdbcPet;
import com.petclinic.repository.jdbc.resultsetextractor.JdbcPetVisitExtractor;
import com.petclinic.repository.jdbc.util.EntityUtils;

@Repository
@Profile({"jdbc"})
public class JdbcOwnerRepositoryImpl implements OwnerRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertOwner;

    @Autowired
    public JdbcOwnerRepositoryImpl(DataSource dataSource) {

        this.insertOwner = new SimpleJdbcInsert(dataSource)
            .withTableName("owners")
            .usingGeneratedKeyColumns("id");

        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    }


    @Override
    public List<Owner> findAllByLastNameLike(String lastName) throws DataAccessException {
    	
        Map<String, Object> params = new HashMap<>();
        params.put("lastName", lastName + "%");
        
        String query = "SELECT 	id, first_name, last_name, address, city, telephone "
        			 + "FROM 	owners "
        			 + "WHERE 	last_name like :lastName";
        
        List<Owner> owners = this.namedParameterJdbcTemplate.query(query, params
        										, BeanPropertyRowMapper.newInstance(Owner.class));
        
        loadOwnersPetsAndVisits(owners);
        
        return owners;
    }

    
    @Override
    public Optional<Owner> findById(Long id) throws DataAccessException {
        
    	Owner owner;
        
    	try {
    		
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            
            String query = "SELECT 	id, first_name, last_name, address, city, telephone "
            		 	 + "FROM 	owners "
            		 	 + "WHERE 	id= :id";
            
            owner = this.namedParameterJdbcTemplate.queryForObject(query, params
            									, BeanPropertyRowMapper.newInstance(Owner.class));
            
        } catch (EmptyResultDataAccessException ex) {
        	
            throw new ObjectRetrievalFailureException(Owner.class, id);
            
        }
    	
        loadPetsAndVisits(owner);
        
        return Optional.ofNullable(owner);
    }
    
    
    @Override
    public Owner save(Owner owner) throws DataAccessException {
    	
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(owner);
        
        if (owner.isNew()) {
        	
            Number newKey = this.insertOwner.executeAndReturnKey(parameterSource);
            owner.setId(newKey.longValue());
            
        } else {
        	
        	String query = "UPDATE 	owners "
        				 + "SET 	first_name=:firstName"
        				 	   + ", last_name=:lastName"
        				 	   + ", address=:address"
        				 	   + ", city=:city"
        				 	   + ", telephone=:telephone "
        				 + "WHERE 	id=:id";
        	
            this.namedParameterJdbcTemplate.update(query, parameterSource);
            
        }
        
        return owner;
    }
    

    
    
    
    
    
    public Collection<PetType> getPetTypes() throws DataAccessException {
    	
    	String query =  "SELECT id, name FROM types ORDER BY name"; 
    	
        return this.namedParameterJdbcTemplate.query(query, new HashMap<String, Object>(),
        								BeanPropertyRowMapper.newInstance(PetType.class));
    }
    
    
    public void loadPetsAndVisits(final Owner owner) {
    	
        Map<String, Object> params = new HashMap<>();
        params.put("id", owner.getId());
        
        String query = "SELECT 	pets.id, name, birth_date, type_id, owner_id, visits.id as visit_id"
        			+ "			, visit_date, description, pet_id "
        			+ "	FROM 	pets "
        			+ " LEFT OUTER JOIN	 visits ON pets.id = pet_id "
        			+ "	WHERE 	owner_id=:id "
        			+ "	ORDER BY pet_id"; 
        		
        final List<JdbcPet> pets = this.namedParameterJdbcTemplate.query(query, params
        																, new JdbcPetVisitExtractor());
        
        Collection<PetType> petTypes = getPetTypes();
        
        for (JdbcPet pet : pets) {
        	
        	PetType petType = EntityUtils.getById(petTypes, PetType.class, pet.getTypeId());
            pet.setPetType(petType);
            owner.addPet(pet);
        }
    }

    

    

    /**
     * Loads the {@link Pet} and {@link Visit} data for the supplied {@link List} of {@link Owner Owners}.
     *
     * @param owners the list of owners for whom the pet and visit data should be loaded
     * @see #loadPetsAndVisits(Owner)
     */
    private void loadOwnersPetsAndVisits(List<Owner> owners) {
    	
        for (Owner owner : owners) {
            loadPetsAndVisits(owner);
        }
        
    }


}
