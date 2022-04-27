package br.com.fiap.abctechservice.controller;

import br.com.fiap.abctechservice.application.Impl.AssistanceApplicationImpl;
import br.com.fiap.abctechservice.application.dto.AssistDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AssistanceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AssistanceApplicationImpl assistanceApplication;

    @InjectMocks
    private AssistanceController assistanceController;

    AssistDTO ASSIST1 = new AssistDTO(1L, "Assistencia1", "Desc1");
    AssistDTO ASSIST2 = new AssistDTO(1L, "Assistencia2", "Desc2");
    AssistDTO ASSIST3 = new AssistDTO(1L, "Assistencia3", "Desc3");

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(assistanceController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    public void getAllAssists_success() throws Exception {
        List<AssistDTO> assists = new ArrayList<>(Arrays.asList(ASSIST1, ASSIST2, ASSIST3));

        Mockito.when(assistanceApplication.getAssists()).thenReturn(assists);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/assistance")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)));
    }

}
