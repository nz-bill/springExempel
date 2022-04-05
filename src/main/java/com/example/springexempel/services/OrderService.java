package com.example.springexempel.services;

import com.example.springexempel.models.OrdersDao;
import jdk.jfr.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrdersDao ordersDao;

    public void placeOrder(String customerName, String productsAsCSV, String quantitiesAsSCV ){
        ordersDao.placeOrder(customerName,productsAsCSV,quantitiesAsSCV);
    }

    public  void getValue(){
        ordersDao.getValue();
    }

}
