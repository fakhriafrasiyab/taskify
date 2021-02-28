package com.taskify.taskifyApp;

import com.taskify.taskifyApp.payload.request.LoginRequest;
import com.taskify.taskifyApp.payload.request.SignupRequest;
import com.taskify.taskifyApp.payload.response.JwtResponse;
import com.taskify.taskifyApp.payload.response.MessageResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTesting {
    @Autowired
    TestRestTemplate testRestTemplate;


    @Test
    public void test_sign_up() throws Exception {
        SignupRequest signUpRequest = new SignupRequest();
        signUpRequest.setEmail("afrasiyab@gmail.com");
        signUpRequest.setName("Fakhri Afrasiyab");
        signUpRequest.setPassword("test1234");
        signUpRequest.setOrganizationName("ATL TECH");
        signUpRequest.setPhoneNumber("0505140673");
        signUpRequest.setAddress("St.Ahmad Jamil");

        ResponseEntity<MessageResponse> rspn =
                testRestTemplate.postForEntity("/api/auth/signup", signUpRequest, MessageResponse.class);

        assertThat(rspn.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(rspn.getBody().getMessage()).isEqualTo("User registered successfully!");
    }


    @Test
    public void test_sign_in() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("test1234");
        loginRequest.setEmail("fakhriafrasiyab@gmail.com");


        ResponseEntity<JwtResponse> rspn =
                testRestTemplate.postForEntity("/api/auth/signin", loginRequest, JwtResponse.class);

        assertThat(rspn.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(rspn.getBody().getTokenType()).isEqualTo("Bearer");


    }


}

