package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Collections;
import java.util.Random;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by hillaryskye on 3/1/17.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerMockTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PersonRepository repository;

    @Test
    public void testCreate() throws Exception {
        MockHttpServletRequestBuilder request = post("/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\": \"Chloeaa\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Chloeaa") ));

        verify(this.repository).save(any(Person.class));
    }

        @Test
        public void testList() throws Exception {
            Long id = new Random().nextLong();
            Person person = new Person();
            person.setFirstName("Lou");
            person.setId(id);

            when(this.repository.findAll()).thenReturn(Collections.singletonList(person));

            MockHttpServletRequestBuilder request = get("/people")
                    .contentType(MediaType.APPLICATION_JSON);

            this.mvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id", equalTo(id) ))
                    .andExpect(jsonPath("$[0].firstName", equalTo("Lou") ));
        }
}
