package com.example.springexempel.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDaoTest {

    @Mock
   JdbcTemplate jdbcTemplateMock;

    @Mock
    CustomerDao customerDaoMock;

  //  CustomerDao customerDao = new CustomerDao();

    @Mock
    Customer testCustomer = new Customer(1000000,"test", "test@mail.com");

    @BeforeEach
    void setUp() {
       MockitoAnnotations.openMocks(this);
    }

    @Test
    void insertCustomer() {
        //setup
        String query = "INSERT INTO customers (customer_name, customer_mail) VALUES(?,?)";
        String name = "test";
        String mail = "test@mail.com";
        Mockito.when(jdbcTemplateMock.update(query,name,mail)).thenReturn(1);

        //action
        int i = jdbcTemplateMock.update(query,name,mail);
        //result

        assertEquals(1,i);
        Mockito.verify(jdbcTemplateMock).update(query,name,mail);
    }

    @Test
    void insertCustomers() {
    }

    @Test
    void getCustomerById() {
        //setup
        Mockito.when(customerDaoMock.getCustomerById(Mockito.anyString())).thenReturn(testCustomer);
        Mockito.when(customerDaoMock.getCustomerById("kaninbajs")).thenReturn(testCustomer);
        //action
        Customer c = customerDaoMock.getCustomerById("banan");

        //result
        assertEquals(testCustomer, c);
        Mockito.verify(customerDaoMock).getCustomerById("banan");

    }

    @Test
    void getCustomerByName() {
    }

    @Test
    void getAllCustomers() {
    }

    @Test
    void getMaxCustomerId() {
    }
}