package com.zlaja.avatest;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@ActiveProfiles("notApprovedRules")
public class AvaTestApplicationTests_NotApprovedRules {

	@Autowired
	WebApplicationContext context;
    @Autowired
    private MockMvc mockMvc;

	@Test
    @WithMockUser("sloba")
	public void test_sloba() throws Exception {
        MockHttpServletResponse response = accessProductsResource();

        assertThat(getNameOfElement(0, response), is("Ves Masina"));
        assertThat(getRightsOfElement(0, response), is("RUD"));

        assertThat(getNameOfElement(1, response), is("Bojler"));
        assertThat(getRightsOfElement(1, response), is("RUD"));

        assertThat(getNameOfElement(2, response), is("Sporet"));
        assertThat(getRightsOfElement(2, response), is("RUD"));

        assertThat(getNameOfElement(3, response), is("Sudo masina"));
        assertThat(getRightsOfElement(3, response), is("RUD"));
	}

    @Test
    @WithMockUser("jovana")
    public void test_jovana() throws Exception {
        MockHttpServletResponse response = accessProductsResource();

        assertThat(getNameOfElement(0, response), is("Ves Masina"));
        assertThat(getRightsOfElement(0, response), is("RU"));

        assertThat(getNameOfElement(1, response), is("Bojler"));
        assertThat(getRightsOfElement(1, response), is("RU"));

        assertThat(getNameOfElement(2, response), is("Sporet"));
        assertThat(getRightsOfElement(2, response), is("RU"));

        assertThat(getNameOfElement(3, response), is("Sudo masina"));
        assertThat(getRightsOfElement(3, response), is("RU"));
    }

    @Test
    @WithMockUser("nemanja")
    public void test_nemanja() throws Exception {
        MockHttpServletResponse response = accessProductsResource();

        assertThat(getNameOfElement(0, response), is("Ves Masina"));
        assertThat(getRightsOfElement(0, response), is("R"));

        assertThat(getNameOfElement(1, response), is("Bojler"));
        assertThat(getRightsOfElement(1, response), is("R"));

        assertThat(getNameOfElement(2, response), is("Sporet"));
        assertThat(getRightsOfElement(2, response), is("R"));

        assertThat(getNameOfElement(3, response), is("Sudo masina"));
        assertThat(getRightsOfElement(3, response), is("R"));
    }

    private MockHttpServletResponse accessProductsResource() throws Exception {
        return mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }

    private String getNameOfElement(int index, MockHttpServletResponse response) throws UnsupportedEncodingException {
        DocumentContext doc = JsonPath.parse(response.getContentAsString());
        return doc.read(JsonPath.compile("$[" + index + "].name"));
    }

    private String getRightsOfElement(int index, MockHttpServletResponse response) throws UnsupportedEncodingException {
        DocumentContext doc = JsonPath.parse(response.getContentAsString());
        JSONArray rights = doc.read(JsonPath.compile("$[" + index + "].rights"));

        String result = "";
        for (Object right : rights) {
            result += right.toString().charAt(0);
        }

        return result;
    }
}
