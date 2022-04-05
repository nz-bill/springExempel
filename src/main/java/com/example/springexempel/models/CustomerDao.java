package com.example.springexempel.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class CustomerDao extends  JdbcDaoSupport {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @PostConstruct
    private void initialize() {
        setJdbcTemplate(jdbcTemplate);
    }

    public void insertCustomer(String name, String email){

        String query = "INSERT INTO customers (customer_name, customer_mail) VALUES(?,?)";

        int result = jdbcTemplate.update(query,name,email);

        if(result > 0){
            System.out.println(result + " customer added to database");
        }
    }

    public void insertCustomers(List<Customer> customerList){
            String query = "INSERT INTO customers (customer_name, customer_mail) VALUES (?,?)";


            int[] batchSize =jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Customer customer = customerList.get(i);

                    ps.setString(1, customer.getName());
                    ps.setString(2,customer.getEmail());
                }

                @Override
                public int getBatchSize() {
                    return customerList.size();
                }
            });

        System.out.println(batchSize + " customers added to database");
    }


    public Customer getCustomerById(String id){
        String query = "SELECT * FROM customers WHERE customer_id =?";

        Customer customer = jdbcTemplate.queryForObject(query, new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Customer cus = new Customer(rs.getInt("customer_id"),
                        rs.getString("customer_name"),
                        rs.getString("customer_mail"));
                return cus;
            }
        }, id);

        return customer;
    }

    public Customer getCustomerByName(String id){
        String query = "SELECT * FROM customers WHERE customer_name =?";

        Customer customer = jdbcTemplate.queryForObject(query, new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Customer cus = new Customer(rs.getInt("customer_id"),
                        rs.getString("customer_name"),
                        rs.getString("customer_mail"));
                return cus;
            }
        }, id);

        return customer;
    }

    public List<Customer> getAllCustomers(){
        String query = "SELECT * FROM customers";

        List<Map<String,Object>> rows = jdbcTemplate.queryForList(query);
        List<Customer> customerList = new ArrayList<>();

        for (Map<String,Object> row: rows) {
            Customer customer = new Customer((int)row.get("customer_id"),(String)row.get("customer_name"),(String)row.get("customer_mail"));
            customerList.add(customer);
        }

        return customerList;

    }

    public int getMaxCustomerId(){
        String query = "SELECT MAX(customer_id) FROM customers";

       return  jdbcTemplate.queryForObject(query, new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Customer(rs.getInt("MAX(customer_id)"));
            }
        }).getId();

    }






}
