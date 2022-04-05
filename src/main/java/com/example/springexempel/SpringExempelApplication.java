package com.example.springexempel;

import com.example.springexempel.models.Customer;
import com.example.springexempel.services.CustomerService;
import com.example.springexempel.services.OrderService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class SpringExempelApplication {

    @Autowired
    CustomerService customerService;

    @Autowired
    OrderService orderService;

    //new
    @Autowired
    HttpServletRequest request;

    private static Authenticate auth;

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SpringExempelApplication.class, args);
        CustomerService customerService = context.getBean(CustomerService.class);
        OrderService orderService = context.getBean(OrderService.class);

        auth = new Authenticate();

       // orderService.placeOrder("Bill","apple,orange","3,2");
        orderService.getValue();
       // System.out.println(customerService.getCustomerById("1").toString());

//        Customer customer = new Customer(customerService.getMAxCustomerId()+1,"Bane","baqrne@mail.com");
//        Customer customer1 = new Customer(customerService.getMAxCustomerId()+1,"Badfhdne","bage@mail.com");
//        Customer customer2 = new Customer(customerService.getMAxCustomerId()+1,"Bdhgdne","bansgne@mail.com");

//        List<Customer> customerList = new ArrayList<>();
//        customerList.add(customer);
//        customerList.add(customer1);
//        customerList.add(customer2);


    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name){

        return "Hello" + name + "!";
    }

    @GetMapping("/calculate")
    public String calculateStuff(@RequestParam(value ="tal1", defaultValue ="0") int tal1, @RequestParam(value = "tal2", defaultValue = "0") int tal2){
        int sum = tal1 + tal2;
        return "summan av " + tal1 + " och " + tal2 + " är " + sum;
    }

    @GetMapping("/authenticate")
    public String getCustomerByName(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email){
        Customer c = customerService.getCustomerByName(name);

        String token = auth.generateToken(c);


       return  token;
    }

    @GetMapping("/checkToken")
    public String getCustomerByName(@RequestParam(value = "token") String token){

      // String claims = auth.parseJwt(token);
        System.out.println(getToken());
        return null;
    }

    public String getToken(){
        String token = request.getHeader("Authorization").substring(7);

        return token;
    }


}
