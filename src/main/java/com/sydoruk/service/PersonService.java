package com.sydoruk.service;

import com.sydoruk.model.Customer;
import java.util.Random;

public class PersonService {

    private final Random random = new Random();
    private final String[] emails = {"korvin@gmail.com", "aragorn@gmail.com", "rand@gmail.com", "kaladin@gmail.com",
            "kvothe@gmail.com", "roland@gmail.com", "logen@gmail.com", "raistlin@gmail.com", "geralt@gmail.com"};

    public Customer createPerson(){
        Customer customer = new Customer();
        customer.setEmail(emails[random.nextInt(0, emails.length)]);
        customer.setAge(random.nextInt(15, 80));
        return customer;
    }
}