package com.petclinic.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import com.petclinic.commands.OwnerCommand;
import com.petclinic.commands.PetCommand;
import com.petclinic.commands.PetTypeCommand;
import com.petclinic.convertor.CommandDTOConvertor;
import com.petclinic.dto.OwnerDTO;
import com.petclinic.dto.PetDTO;
import com.petclinic.dto.PetTypeDTO;
import com.petclinic.service.OwnerService;
import com.petclinic.service.PetService;
import com.petclinic.service.PetTypeService;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @Mock
    PetService petService;
    
    @Mock
    private CommandDTOConvertor convertor1;
    
    private CommandDTOConvertor convertor;

    @Mock
    OwnerService ownerService;

    @Mock
    PetTypeService petTypeService;

    @InjectMocks
    PetController petController;
    
    @MockBean(name = "mvcValidator")
    private Validator mockValidator;

    MockMvc mockMvc;

    OwnerDTO ownerDTO;
    PetDTO petDTO;
    
    OwnerCommand ownerCommand;
    PetCommand petCommand;
    
    List<PetTypeDTO> petTypes;

    @BeforeEach
    void setUp() {
    	
    	convertor = new CommandDTOConvertor();
    	
        ownerDTO = new OwnerDTO();
        ownerDTO.setId("1");
        
        PetTypeDTO petTypeDTO1 = new PetTypeDTO();
        petTypeDTO1.setId("1");
        petTypeDTO1.setName("Dog");
        
        PetTypeDTO petTypeDTO2 = new PetTypeDTO();
        petTypeDTO2.setId("2");
        petTypeDTO2.setName("Cat");
        

        petTypes = new ArrayList<>();
        petTypes.add(petTypeDTO1);
        petTypes.add(petTypeDTO2);
        
        petDTO = new PetDTO();
        petDTO.setId("1");
        
        ownerCommand = convertor.convert(ownerDTO);
        petCommand = convertor.convert(petDTO);

        mockMvc = MockMvcBuilders
                .standaloneSetup(petController)
                .setValidator(mockValidator)
                .build();
    }

    @Test
    void initCreationForm() throws Exception {
    	
    	PetTypeCommand petTypeCommand1 = new PetTypeCommand();
        petTypeCommand1.setId("1");
        petTypeCommand1.setName("Dog");
        
        PetTypeCommand petTypeCommand2 = new PetTypeCommand();
        petTypeCommand2.setId("2");
        petTypeCommand2.setName("Cat");
        
        when(convertor1.convert(any(PetTypeDTO.class))).thenReturn(petTypeCommand1, petTypeCommand2);
        
        when(convertor1.convert(any(OwnerDTO.class))).thenReturn(ownerCommand);
    	
        when(ownerService.findById(anyString())).thenReturn(ownerDTO);
        
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("pets/createOrUpdatePetForm"));
    }

    @Test
    void processCreationForm() throws Exception {
    	
    	PetTypeCommand petTypeCommand1 = new PetTypeCommand();
        petTypeCommand1.setId("1");
        petTypeCommand1.setName("Dog");
        
        PetTypeCommand petTypeCommand2 = new PetTypeCommand();
        petTypeCommand2.setId("2");
        petTypeCommand2.setName("Cat");
        
        when(convertor1.convert(any(PetTypeDTO.class))).thenReturn(petTypeCommand1, petTypeCommand2);
        when(convertor1.convert(any(OwnerDTO.class))).thenReturn(ownerCommand);
    	
    	when(convertor1.convert(any(OwnerCommand.class))).thenReturn(ownerDTO);
    	
    	when(convertor1.convert(any(PetCommand.class))).thenReturn(petDTO);
    	
        when(ownerService.findById(anyString())).thenReturn(ownerDTO);
        
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(post("/owners/1/pets/new")
    			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
    			.param("id", "")
                .param("name", "name")
//                .param("petType", "petType")
                //.param("address", "address")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService).save(any());
    }

    @Test
    void initUpdateForm() throws Exception {
    	
    	PetTypeCommand petTypeCommand1 = new PetTypeCommand();
        petTypeCommand1.setId("1");
        petTypeCommand1.setName("Dog");
        
        PetTypeCommand petTypeCommand2 = new PetTypeCommand();
        petTypeCommand2.setId("2");
        petTypeCommand2.setName("Cat");
        
        when(convertor1.convert(any(PetTypeDTO.class))).thenReturn(petTypeCommand1, petTypeCommand2);
    	
    	PetDTO petDTO2 = new PetDTO();
    	petDTO2.setId("2");
    	
    	PetCommand petCommand2 = new PetCommand();
    	petCommand2.setId("2");
    	
    	when(convertor1.convert(any(PetDTO.class))).thenReturn(petCommand2);
    	when(convertor1.convert(any(OwnerDTO.class))).thenReturn(ownerCommand);
    	
        when(ownerService.findById(anyString())).thenReturn(ownerDTO);
        
        when(petTypeService.findAll()).thenReturn(petTypes);
        
        when(petService.findById(anyString())).thenReturn(petDTO2);

        mockMvc.perform(get("/owners/1/pets/2/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("pets/createOrUpdatePetForm"));
    }

    @Test
    void processUpdateForm() throws Exception {
    	
    	PetTypeCommand petTypeCommand1 = new PetTypeCommand();
        petTypeCommand1.setId("1");
        petTypeCommand1.setName("Dog");
        
        PetTypeCommand petTypeCommand2 = new PetTypeCommand();
        petTypeCommand2.setId("2");
        petTypeCommand2.setName("Cat");
        
        when(convertor1.convert(any(PetTypeDTO.class))).thenReturn(petTypeCommand1, petTypeCommand2);
    	
    	when(convertor1.convert(any(OwnerCommand.class))).thenReturn(ownerDTO);
    	
    	when(convertor1.convert(any(OwnerDTO.class))).thenReturn(ownerCommand);
    	
    	when(convertor1.convert(any(PetCommand.class))).thenReturn(petDTO);
    	
    	when(ownerService.findById(anyString())).thenReturn(ownerDTO);
        
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(post("/owners/1/pets/2/edit")
    			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
    			.param("id", "")
                .param("name", "name")
//                .param("petType", "petType")
                //.param("address", "address")
               )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService).save(any());
    }

    @Test
    void populatePetTypes() {
        //todo impl
    }

    @Test
    void findOwner() {
        //todo impl
    }

    @Test
    void initOwnerBinder() {
        //todo impl
    }
}