package haowei.springframework.springframework5haoweilaundry.controllers;

import haowei.springframework.springframework5haoweilaundry.model.Cloth;
import haowei.springframework.springframework5haoweilaundry.services.ClothService;
import haowei.springframework.springframework5haoweilaundry.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class VisitControllerTest {

    @Mock
    ClothService clothService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController visitController;

    Cloth cloth;

    MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        cloth = Cloth.builder().id(1L).build();

        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void initCreationForm() throws Exception{
        when(clothService.findById(anyLong())).thenReturn(cloth);

        mockMvc.perform(get("/owners/1/clothes/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("cloth"))
                .andExpect(view().name("visits/createOrUpdateVisitForm"));
    }

    @Test
    void processCreationForm() throws Exception{
        when(clothService.findById(anyLong())).thenReturn(cloth);

        mockMvc.perform(post("/owners/1/clothes/1/visits/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("cloth"))
                .andExpect(view().name("redirect:/owners/{ownerId}"));

        verify(visitService).save(any());
    }
}
