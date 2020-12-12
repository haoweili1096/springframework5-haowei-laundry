package haowei.springframework.springframework5haoweilaundry.controllers;


import haowei.springframework.springframework5haoweilaundry.model.Cloth;
import haowei.springframework.springframework5haoweilaundry.model.ClothType;
import haowei.springframework.springframework5haoweilaundry.model.Owner;
import haowei.springframework.springframework5haoweilaundry.services.ClothService;
import haowei.springframework.springframework5haoweilaundry.services.ClothTypeService;
import haowei.springframework.springframework5haoweilaundry.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ClothControllerTest {

    @Mock
    ClothService clothService;

    @Mock
    ClothTypeService clothTypeService;

    @Mock
    OwnerService ownerService;

    @InjectMocks
    ClothController clothController;

    MockMvc mockMvc;

    Owner owner;

    Set<ClothType> clothTypes;

    @BeforeEach
    void setUp(){
        owner = Owner.builder().id(1L).build();

        clothTypes = new HashSet<>();
        clothTypes.add(ClothType.builder().id(1L).name("Shirt").build());
        clothTypes.add(ClothType.builder().id(2L).name("Jacket").build());

        mockMvc = MockMvcBuilders.standaloneSetup(clothController).build();
    }

    @Test
    void initCreationForm() throws Exception{
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(clothTypeService.findAll()).thenReturn(clothTypes);

        mockMvc.perform(get("/owners/1/clothes/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("cloth"))
                .andExpect(view().name("clothes/createOrUpdateClothForm"));

    }

    @Test
    void processCreationForm() throws Exception{
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(clothTypeService.findAll()).thenReturn(clothTypes);

        mockMvc.perform(post("/owners/1/clothes/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("cloth"))
                .andExpect(view().name("redirect:/owners/1"));

        verify(clothService).save(any());
    }

    @Test
    void initUpdateClothForm() throws Exception{
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(clothTypeService.findAll()).thenReturn(clothTypes);
        when(clothService.findById(anyLong())).thenReturn(Cloth.builder().id(2L).build());

        mockMvc.perform(get("/owners/1/clothes/2/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("cloth"))
                .andExpect(view().name("clothes/createOrUpdateClothForm"));
    }

    @Test
    void processUpdateClothForm() throws Exception{
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(clothTypeService.findAll()).thenReturn(clothTypes);

        mockMvc.perform(post("/owners/1/clothes/2/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(clothService).save(any());
    }
}
