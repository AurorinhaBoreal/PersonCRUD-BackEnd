package com.db.crud.person.integration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.db.crud.person.PersonApplication;
import com.db.crud.person.dto.request.AddressRequest;
import com.db.crud.person.fixtures.AddressFixture;
import com.db.crud.person.fixtures.SqlProvider;
import com.db.crud.person.repository.AddressRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@AutoConfigureMockMvc
@Transactional
@SpringBootTest(classes = { PersonApplication.class })
@ActiveProfiles("test")
class AddressControllerIntegrationTests {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        ObjectMapper mapper;

        @Autowired
        AddressRepository addressRepository;

        private AddressRequest addressDTORequest;
        String json;

        @DirtiesContext
        @Test
        @DisplayName("Happy Test: Should Create Address")
        @SqlGroup({
                        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.CLEAR_DB),
                        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.INSERT_PERSON),
                        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.CLEAR_DB)
        })
        void testCreateAddress() throws Exception {

                addressDTORequest = AddressFixture.AddressDTOValidFixture();

                json = mapper.writeValueAsString(addressDTORequest);

                String cpf = "74553108038";

                mockMvc.perform(MockMvcRequestBuilders.post("/address/create/" + cpf)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.street").value(addressDTORequest.street()));
        }

        @Test
        @DisplayName("Happy Test: Should update Address")
        @DirtiesContext
        @SqlGroup({
                        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.CLEAR_DB),
                        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.INSERT_PERSON),
                        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.INSERT_ADDRESS),
                        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.CLEAR_DB)
        })
        void testUpdateAddress() throws Exception {

                addressDTORequest = AddressFixture.AddressDTOValidFixture();

                json = mapper.writeValueAsString(addressDTORequest);

                Long id = 14L;

                mockMvc.perform(MockMvcRequestBuilders.patch("/address/update/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.street").value(addressDTORequest.street()));
        }

        @Transactional
        @DirtiesContext
        @Test
        @DisplayName("Happy Test: Should Delete Address")
        @SqlGroup({
                        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.CLEAR_DB),
                        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.INSERT_PERSON),
                        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.INSERT_ADDRESS),
                        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.CLEAR_DB)
        })
        void testDeleteAddress() throws Exception {

                Long id = 14L;

                mockMvc.perform(MockMvcRequestBuilders.delete("/address/delete/" + id))
                                .andExpect(status().isNoContent());
        }
}