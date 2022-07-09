package com.mongo;

import com.mongo.entity.Account;
import com.mongo.entity.Customer;
import com.mongo.entity.Address;
import com.mongo.service.CustomerService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class MongoApplication {
    private CustomerService service;

    @Autowired
    public void setService(CustomerService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void afterStart() throws ParseException {
        Customer customer;
        List<Address> addresses = new ArrayList<>();
        Address addressOne = new Address("line1", "line2", 123);
        Address addressTwo = new Address("line3", "line4", 456);
        addresses.add(addressOne);
        addresses.add(addressTwo);

        List<Account> accounts = new ArrayList<>();
        Date dateOne = new SimpleDateFormat("dd/MM/yyyy").parse("30/12/2021");
        Date dateTwo = new SimpleDateFormat("dd/MM/yyyy").parse("20/12/2021");
        Account accountOne = new Account(1111222233334444L, "name1", dateOne);
        Account accountTwo = new Account(5555666677778888L, "name2", dateTwo);
        accounts.add(accountOne);
        accounts.add(accountTwo);

        customer = new Customer();
        customer.setFirstName("First");
        customer.setLastName("Last");
        customer.setAddresses(addresses);
        customer.setAccounts(accounts);

        service.create(customer);
    }
}
