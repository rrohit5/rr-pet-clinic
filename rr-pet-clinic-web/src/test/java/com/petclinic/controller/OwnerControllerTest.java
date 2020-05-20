package com.petclinic.controller;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.petclinic.commands.OwnerCommand;
import com.petclinic.convertor.CommandDTOConvertor;
import com.petclinic.dto.OwnerDTO;
import com.petclinic.service.OwnerService;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;
    
    @Mock
    private CommandDTOConvertor convertor1;
    
    @InjectMocks
    OwnerController controller;

    Set<OwnerDTO> owners;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    	
        owners = new HashSet<>();
        
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setId("1");        
        
        OwnerDTO owner2 = new OwnerDTO();
        owner2.setId("2");
        
        owners.add(ownerDTO);
        owners.add(owner2);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    /*   @Test
   void listOwners() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));

    }

    @Test
    void listOwnersByIndex() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }
*/
    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(ownerService);
    }

    @Test
    void processFindFormReturnMany() throws Exception {
    	
    	OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setId("1");        
        
        OwnerDTO owner2 = new OwnerDTO();
        owner2.setId("2");
    	
        when(ownerService.findAllByLastNameLike(anyString()))
                .thenReturn(Arrays.asList(ownerDTO, owner2));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("selections", hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {
    	
    	OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setId("1");        
        
        OwnerDTO owner2 = new OwnerDTO();
        owner2.setId("2");
        
        when(ownerService.findAllByLastNameLike(anyString()))
        		.thenReturn(Arrays.asList(ownerDTO));

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    void processFindFormEmptyReturnMany() throws Exception {
    	
    	OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setId("1");        
        
        OwnerDTO owner2 = new OwnerDTO();
        owner2.setId("2");
        
        when(ownerService.findAllByLastNameLike(anyString()))
                .thenReturn(Arrays.asList(ownerDTO, owner2));

        mockMvc.perform(get("/owners")
                        .param("lastName",""))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("selections", hasSize(2)));;
    }

    @Test
    void displayOwner() throws Exception {
    	
    	OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setId("1");        
        
        OwnerCommand ownerCommand = new OwnerCommand();
        ownerCommand.setId("1");
        
        when(convertor1.convert(any(OwnerDTO.class))).thenReturn(ownerCommand);
        
        when(ownerService.findById(anyString())).thenReturn(ownerDTO);

        mockMvc.perform(get("/owners/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is("1"))));
    }

    @Test
    void initCreationForm() throws Exception {
    	
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(ownerService);
    }

    @Test
    void processCreationForm() throws Exception {
    	
    	OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setId("1");        
                
        when(convertor1.convert(any(OwnerCommand.class))).thenReturn(ownerDTO);
        
        when(ownerService.save(ArgumentMatchers.any())).thenReturn(ownerDTO);

        mockMvc.perform(post("/owners/new")
    			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
    			.param("id", "")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("address", "address")
                .param("city", "city")
                .param("telephone", "9999999999")
    		)
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(ArgumentMatchers.any());
    }

    @Test
    void initUpdateOwnerForm() throws Exception {
    	
    	OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setId("1");        
        
        OwnerCommand ownerCommand = new OwnerCommand();
        ownerCommand.setId("1");
        
        when(convertor1.convert(any(OwnerDTO.class))).thenReturn(ownerCommand);
        
        when(ownerService.findById(anyString())).thenReturn(ownerDTO);

        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

//        verifyNoInteractions(ownerService);
    }

    @Test
    void processUpdateOwnerForm() throws Exception {
    	
    	OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setId("1");        
        
        OwnerDTO owner2 = new OwnerDTO();
        owner2.setId("2");
        
        when(convertor1.convert(any(OwnerCommand.class))).thenReturn(ownerDTO);
        
        when(ownerService.save(ArgumentMatchers.any())).thenReturn(ownerDTO);

        mockMvc.perform(post("/owners/1/edit")
        			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
        			.param("id", "1")
                    .param("firstName", "firstName")
                    .param("lastName", "lastName")
                    .param("address", "address")
                    .param("city", "city")
                    .param("telephone", "9999999999")
        		)
        		
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(ArgumentMatchers.any());
    }
}
