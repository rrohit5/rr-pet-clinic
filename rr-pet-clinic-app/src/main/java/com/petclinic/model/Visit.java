package com.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@NoArgsConstructor
@AllArgsConstructor
//@Builder
//@ToString

@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

	@Column(name = "visit_date")
	private LocalDate date;

	@Column(name = "description")
	private String description;

	@ManyToOne/*(fetch = FetchType.LAZY)*/
	@JoinColumn(name = "pet_id")
	private Pet pet;
	
	public Visit() {
        this.date = LocalDate.now();
    }

    public LocalDate getDate() {
        return date;
    }
    
}
