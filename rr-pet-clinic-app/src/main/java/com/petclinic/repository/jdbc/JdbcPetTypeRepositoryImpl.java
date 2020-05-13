package com.petclinic.repository.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.petclinic.model.PetType;

@Repository
@Profile({"jdbc"})
public class JdbcPetTypeRepositoryImpl implements PetTypeRepository {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private SimpleJdbcInsert insertPetType;

	public JdbcPetTypeRepositoryImpl(DataSource dataSource) {

		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
		this.insertPetType = new SimpleJdbcInsert(dataSource)
	            .withTableName("types")
	            .usingGeneratedKeyColumns("id");

	}

	@Override
    public List<PetType> findPetTypes() throws DataAccessException {
    	
        Map<String, Object> params = new HashMap<>();
        
        String query = "SELECT id, name FROM types ORDER BY name"; 
        
        List<PetType> petTypeList = this.namedParameterJdbcTemplate.query(query, params
				, BeanPropertyRowMapper.newInstance(PetType.class)); 
        
        return petTypeList;
    }
	
	
    @Override
    public PetType save(PetType petType) throws DataAccessException {
    	
        if (petType.isNew()) {
        	
            Number newKey = this.insertPetType.executeAndReturnKey(createPetParameterSource(petType));
            petType.setId(newKey.longValue());
            
        } else {
        	
        	throw new NullPointerException("Pet Type is updating");
        }
        
        return petType;
    }
    
    
    private MapSqlParameterSource createPetParameterSource(PetType petType) {
        
    	return new MapSqlParameterSource()
			            .addValue("id", petType.getId())
			            .addValue("name", petType.getName());
    	
    }

}
