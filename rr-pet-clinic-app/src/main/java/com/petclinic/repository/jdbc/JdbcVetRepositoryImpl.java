/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.petclinic.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.petclinic.model.Specialty;
import com.petclinic.model.Vet;
import com.petclinic.repository.jdbc.util.EntityUtils;

/**
 * A simple JDBC-based implementation of the {@link VetRepository} interface.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @author Sam Brannen
 * @author Thomas Risberg
 * @author Mark Fisher
 * @author Michael Isvy
 */
@Repository
@Profile({"jdbc"})
public class JdbcVetRepositoryImpl implements VetRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcVetRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Refresh the cache of Vets that the ClinicService is holding.
     */
    @Override
    public List<Vet> findAll() throws DataAccessException {
    	
        List<Vet> vets = new ArrayList<>();
        
        // Retrieve the list of all vets.
        String query = "SELECT id, first_name, last_name FROM vets ORDER BY last_name,first_name";
        
        vets.addAll(this.jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Vet.class)));

        
        // Retrieve the list of all possible specialties.
        String query2 = "SELECT id, name FROM specialties";
        
        final List<Specialty> specialties = this.jdbcTemplate.query(query2
        									, BeanPropertyRowMapper.newInstance(Specialty.class));

        // Build each vet's list of specialties.
        
        for (Vet vet : vets) {
        	
        	String query3 = "SELECT specialty_id FROM vet_specialties WHERE vet_id=?"; 
        			
            final List<Long> vetSpecialtiesIds = this.jdbcTemplate.query(query3
            							, new BeanPropertyRowMapper<Long>() {
							                    @Override
							                    public Long mapRow(ResultSet rs, int row) throws SQLException {
							                        return rs.getLong(1);
							                    }
                						}
            							, vet.getId());
            
            for (Long specialtyId : vetSpecialtiesIds) {
            	
                Specialty specialty = EntityUtils.getById(specialties, Specialty.class, specialtyId);
                vet.addSpecialty(specialty);
                
            }
        }
        return vets;
    }
}
