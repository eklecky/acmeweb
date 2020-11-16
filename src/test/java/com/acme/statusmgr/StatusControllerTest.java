package com.acme.statusmgr;


import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StatusControllerTest {

    private MockMvc mockMvc;


    @Test
    public void withParamShouldReturnTailored_Name_Basic_Mem_Op() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=memory,operations&name=yaakov"))
                .andDo(print()).andExpect(status().reason(is("my custom message")))
                .andExpect((ResultMatcher) jsonPath("$.contentHeader").value("Server Status requested by yaakov"))
                .andExpect((ResultMatcher) jsonPath("$.statusDesc").value("Server is up, and its memory is running low," +
                        " and is operating normally"));

    }

    /**
     Testing of expected error case.
     */
    @Test
    public void shouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.contentHeader").value
                        ("\"timestamp\":\"2019-11-06T07:20:47.663+0000\",\"status\":400,\"error"))
                .andExpect((ResultMatcher) jsonPath("$.statusDesc").value("Bad Request\"," +
                        "\"message\":\"Required List parameter 'details' " +
                        "is not present in request\",\"path\":\"/server/status/detailed"));

    }

    @Test
    public void withParamShouldReturnTailored_Name_Op() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details= operations&name=yaakov"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.contentHeader").value("Server Status requested by yaakov"))
                .andExpect((ResultMatcher) jsonPath("$.statusDesc").value("Server is up," +
                        " and is operating normally"));

    }

    @Test
    public void withParamShouldReturnTailored_Name_Mem() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=memory&name=yaakov"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.contentHeader").value("Server Status requested by yaakov"))
                .andExpect((ResultMatcher) jsonPath("$.statusDesc").value("Server is up," +
                        " and its memory is running low,"));
    }

    @Test
    public void withParamShouldReturnTailored_Name_Basic_Mem_Op_Ext() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=memory,operations, extensions&name=yaakov"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.contentHeader").value("Server Status requested by yaakov"))
                .andExpect((ResultMatcher) jsonPath("$.statusDesc").value("Server is up, and its memory is running low," +
                        " and is operating normally" + "and is using these extensions - [Hypervisor, Kubernetes, RAID-6]"));

    }

    @Test
    public void withParamShouldReturnTailored_Name_Basic_Op_Ext() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=operations,extensions&name=yaakov"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.contentHeader").value("Server Status requested by yaakov"))
                .andExpect((ResultMatcher) jsonPath("$.statusDesc").value("Server is up," +
                        " and is operating normally" + "and is using these extensions - [Hypervisor, Kubernetes, RAID-6]"));

    }

    @Test
    public void withParamShouldReturnTailored_Name_Basic_Mem_Ext() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=memory,extensions&name=yaakov"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.contentHeader").value("Server Status requested by yaakov"))
                .andExpect((ResultMatcher) jsonPath("$.statusDesc").value("Server is up, and its memory is running low," +
                         "and is using these extensions - [Hypervisor, Kubernetes, RAID-6]"));

    }

}