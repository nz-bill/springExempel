package com.example.springexempel.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository("namn")
public class OrdersDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void placeOrder(String customerName, String productsAsCSV, String quantitiesAsSCV ){

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("place_order");

        Map<String,String> inParameters = new HashMap<>();

        inParameters.put("customerName", customerName);
        inParameters.put("products", productsAsCSV);
        inParameters.put("quantitiesAsString", quantitiesAsSCV);

        SqlParameterSource in = new MapSqlParameterSource(inParameters);

        simpleJdbcCall.execute(in);

    }

    public void getValue(){
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withFunctionName("new_function");

        Map<String,Object> resultMap = simpleJdbcCall.execute();
        int value = (int)resultMap.get("return");

        System.out.println(value);
    }
}
