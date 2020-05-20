package com.petclinic.mongo.model;

import java.time.LocalDate;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@NoArgsConstructor
@AllArgsConstructor
@Builder
//@ToString

@Document
public class VisitMongo {
	
	@Id
	private String id;
	private LocalDate date;
	private String description;
	private String pet;
	
	
	
	public VisitMongo() {
        this.date = LocalDate.now();
    }

    public LocalDate getDate() {
        return date;
    }
    
    public boolean isNew() {
        return this.id == null;
    }
    
}
