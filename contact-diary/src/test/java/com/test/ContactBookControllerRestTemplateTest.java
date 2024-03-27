  package com.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.contact.AddressBook;
import com.contact.AddressBookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) 
@ActiveProfiles("test")
public class ContactBookControllerRestTemplateTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private AddressBookRepository mockRepository;

    @Before
    public void init() {
        AddressBook addressBook = new AddressBook(101L, "Scott", "9876644");
        when(mockRepository.findById(101L)).thenReturn(Optional.of(addressBook));
    }
    
    @Test
    public void findContactOk() throws Exception {

        List<AddressBook> addressBook = Arrays.asList(
        		new AddressBook(101L, "Scott", "9876644"),
        		new AddressBook(102L, "John", "9876633"));

        when(mockRepository.findAll()).thenReturn(addressBook);

        String expected = om.writeValueAsString(addressBook);

        ResponseEntity<String> response = restTemplate.getForEntity("/addressBooks", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(), false);

        verify(mockRepository, times(1)).findAll();
    }

  
}
