package memphis.StepDefination;



import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import cucumber.api.java.en.*;
import memphis.AbstractIntegrationTest;
import memphis.api.rest.v1.dto.auth.AuthenticationRequest;
import memphis.api.rest.v1.dto.auth.AuthenticationResponse;
import memphis.security.pojo.JwtConfig;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



public class equipmentStepDefination extends AbstractIntegrationTest {
    private final ObjectMapper mapper = new ObjectMapper()
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    private MockHttpServletResponse response;
    private String jwtToken;
    private final JwtConfig jwtConfig;

    private Logger logger = LoggerFactory.getLogger(equipmentStepDefination.class);

    protected equipmentStepDefination(MockMvc mockMvc, JwtConfig jwtConfig) {
        super(mockMvc);
        this.jwtConfig = jwtConfig;

    }


    private MockHttpServletRequestBuilder requestBuilder(String requestType, String endpointUrl) {
        switch (requestType) {
            case "GET":
                return get(endpointUrl);
            case "POST":
                return post(endpointUrl);
            case "PUT":
                return put(endpointUrl);
            case "DELETE":
                return delete(endpointUrl);

            default:
                throw new IllegalArgumentException("Wrong request type: " + requestType);
        }
    }
    private RequestBuilder requestBuilder(String requestType, String endpointUrl,
                                          String contentType, Map<String, String> headers) {
        return requestBuilder(requestType, endpointUrl, contentType, headers, null);
    }

    private RequestBuilder requestBuilder(String requestType, String endpointUrl,
                                          String contentType, Map<String, String> headers,
                                          String body) {
        MockHttpServletRequestBuilder builder = requestBuilder(requestType, endpointUrl);
        builder.contentType(contentType);
        if (body != null) {
            builder.content(body);
        }if (jwtToken != null) {
            builder.header(AUTHORIZATION, jwtConfig.getPrefix() + jwtToken);
        }
        headers.forEach(builder::header);
        return builder;
    }

    private JsonNode find(String keyPath, JsonNode rootNode) {
        String[] keys = keyPath.split("\\.");
        JsonNode jsonNode = rootNode;
        for (String key : keys) {
            jsonNode = jsonNode.get(key);
            if (jsonNode == null) {
                return null;
            }
        }
        return jsonNode;
    }

    @When("^user authenticate with password \"(.+)\"$")
    public void userAuthenticate(String password) throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(password);
        response = mockMvc.perform(requestBuilder("POST", "/v1/auth/", MediaType.APPLICATION_JSON_UTF8_VALUE,
                Collections.emptyMap(), mapper.writeValueAsString(authenticationRequest)))
                .andReturn()
                .getResponse();
        AuthenticationResponse authenticationResponse = mapper.readValue(response.getContentAsString(), AuthenticationResponse.class);
        jwtToken = authenticationResponse.getToken();
    }
    @When("^user sends (.*) request to endpoint (.*) with content-type (.*) and empty headers$")
    public void sendHTTPRequesttoURLWithoutBody(String requestType , String URl , String contentType ) throws Exception{

        response = mockMvc.perform(requestBuilder(requestType, URl, contentType, Collections.emptyMap()))
                .andReturn()
                .getResponse();

    }

    @When("^user sends (.*) request to endpoint (.*) with content-type (.*) and empty headers and body (.*)$")
    public void sendHTTPRequesttoURLWithBody(String requestType , String URl , String contentType ,String requestBody)throws Exception{
        response = mockMvc.perform(requestBuilder(requestType, URl, contentType, Collections.emptyMap(),requestBody))
                .andReturn()
                .getResponse();
    }


    @Then("^the response status code should be (\\d+)$")
    public void checkResponseStatusCode (int statusCode){
        Assert.assertEquals(response.getStatus(), statusCode);

    }

    @And("^the \"([^\"]*)\" list size should be (\\d+)$")
    public void checkResponseListSize(String obj , int size)throws Exception{
        String json = response.getContentAsString();
        JsonNode jsonNode = mapper.readTree(json);

        JsonNode node = find(obj, jsonNode);
        Assert.assertNotNull(node);
        Assert.assertEquals(size, node.size());
    }


    @And("^the json response has \"([^\"]*)\" object$")
    public void checkThereIsResponse(String obj )throws Exception{
        String json = response.getContentAsString();
        JsonNode jsonNode = mapper.readTree(json);
        Assert.assertNotNull("Node is null!", find(obj, jsonNode));

    }

    @And("^the json response of the \"([^\"]*)\" should be like that$")
    public void iShouldSeeJsonResponseWithTheFollowingKeysAndValues
            (String baseKeyPath, Map<String,String> responseFields)throws Exception {
        String json = response.getContentAsString();
        logger.debug("the json response ==>" +json);
        JsonNode jsonNode = mapper.readTree(json);

        JsonNode expectedNode = find(baseKeyPath, jsonNode);
        logger.debug("the json node ==>"+expectedNode.toString());
        responseFields.forEach((key, value) -> {
            JsonNode valueNode = find(key, expectedNode);
            Assert.assertNotNull("Node is null!", valueNode);
            Assert.assertEquals(value, valueNode.asText());
        });
    }

}
