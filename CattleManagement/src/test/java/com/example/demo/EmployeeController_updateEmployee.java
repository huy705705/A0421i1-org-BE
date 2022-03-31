package com.example.demo;

import com.example.demo.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeController_updateEmployee {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void updateEmployee_1() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/employee/update/{id}","null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_2() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/employee/update/{id}",""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_3() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/employee/update/{id}","1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_4() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/employee/update/{id}","12"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void updateEmployee_Id_19() throws Exception {
        Employee employee = new Employee();
        employee.setEmployeeId(null);
        employee.setEmployeeName("Hoa An");
//        LocalDate outDate = LocalDate.of(2021,10,20);
//        entities.setOutDate(outDate);
//        entities.setStatus("Khoe");
//        entities.setWeight(10);
//        entities.setDelete(false);
//        entities.setCageId("1");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/employee/update/{id}")
                        .content(this.objectMapper.writeValueAsString(employee))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
