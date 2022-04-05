package com.example.springexempel.services;

import com.example.springexempel.models.Customer;
import com.example.springexempel.models.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerDao customerDao;

    public void insertCustomer(Customer customer){
        customerDao.insertCustomer(customer.getName(),customer.getEmail());
    }

    public Customer getCustomerById(String id){
        Customer customer = customerDao.getCustomerById(id);
        return customer;
    }

    public Customer getCustomerByName(String name){
        Customer customer = customerDao.getCustomerByName(name);
        return  customer;
    }

    public void insertCustomers(List<Customer> list){
        customerDao.insertCustomers(list);
    }

    public  int getMAxCustomerId(){
        return customerDao.getMaxCustomerId();
    }

    public List<Customer> getAllCustomers(){
        return customerDao.getAllCustomers();
    }

}
