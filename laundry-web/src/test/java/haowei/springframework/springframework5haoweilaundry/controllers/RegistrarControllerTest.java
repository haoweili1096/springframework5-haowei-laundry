package haowei.springframework.springframework5haoweilaundry.controllers;

import haowei.springframework.springframework5haoweilaundry.model.Registrar;
import haowei.springframework.springframework5haoweilaundry.services.RegistrarService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class RegistrarControllerTest {

    @Mock
    RegistrarService registrarService;

    @InjectMocks
    RegistrarController registrarController;

    MockMvc mockMvc;

    Registrar registrar;

    @BeforeEach
    void setUp(){
        registrar = Registrar.builder().id(1L).build();

        mockMvc = MockMvcBuilders.standaloneSetup(registrarController).build();
    }

    @Test
    void registrarsList() throws Exception{
        Set<Registrar> registrars = new HashSet<>();
        registrars.add(Registrar.builder().id(1L).build());
        registrars.add(Registrar.builder().id(2L).build());

        when(registrarService.findAll()).thenReturn(registrars);

        mockMvc.perform(get("/registrars"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("registrars"))
                .andExpect(view().name("registrars/index"));

    }

    @Test
    void initCreationForm() throws Exception{
        mockMvc.perform(get("/registrars/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("registrar"))
                .andExpect(view().name("registrars/createOrUpdateRegistrarForm"));

        verifyZeroInteractions(registrarService);
    }

    @Test
    void processCreationForm() throws Exception{
        when(registrarService.save(any())).thenReturn(registrar);

        mockMvc.perform(post("/registrars/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("registrar"))
                .andExpect(view().name("redirect:/registrars"));

        verify(registrarService).save(any());
    }

    @Test
    void initUpdateRegistrarForm() throws Exception{
        when(registrarService.findById(anyLong())).thenReturn(registrar);

        mockMvc.perform(get("/registrars/1/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("registrar"))
                .andExpect(view().name("registrars/createOrUpdateRegistrarForm"));

    }

    @Test
    void processUpdateRegistrarForm() throws Exception{
        when(registrarService.save(any())).thenReturn(registrar);

        mockMvc.perform(post("/registrars/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("registrar"))
                .andExpect(view().name("redirect:/registrars"));

        verify(registrarService).save(any());
    }
}
