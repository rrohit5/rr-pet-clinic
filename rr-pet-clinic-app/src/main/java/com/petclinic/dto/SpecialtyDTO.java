package com.petclinic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class SpecialtyDTO {
	
	private String id;
	private String name;
	
	public boolean isNew() {
        return this.id == null;
    }

}
