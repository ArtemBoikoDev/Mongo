package com.mongo.service;

import com.mongo.entity.Account;
import com.mongo.entity.Customer;
import com.mongo.entity.Address;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for integration tests on Customer Service.
 *
 * @author Artem Boiko
 */
@SpringBootTest
public class CustomerServiceIT {
    private Customer customer;
    @Autowired
    private CustomerService service;

    @BeforeEach
    public void setUp() throws ParseException {
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
    }

    @AfterEach
    public void tearDown() {
        service.deleteAll();
    }

    @Test
    public void shouldCreateNewCustomer() {
        // when
        service.create(customer);
        List<Customer> customers = service.findAll();
        Customer customerFromDB = customers.get(0);

        // then
        assertEquals(1, customers.size());
        assertEquals(customerFromDB, customer);
    }

    @Test
    public void shouldFindCustomerById() {
        //given
        customer = service.create(customer);

        // when
        Customer customerFromDB = service.findById(customer.getId());

        // then
        assertNotNull(customerFromDB);
    }

    @Test
    public void shouldFindTwoCustomersAfterTheirCreation() {
        //given
        customer.setId(new ObjectId().toString());
        service.create(customer);
        customer.setId(new ObjectId().toString());
        service.create(customer);

        // when
        List<Customer> customers = service.findAll();

        // then
        assertEquals(2, customers.size());
    }

    @Test
    public void shouldFindCustomerByFirstName() {
        //given
        service.create(customer);

        // when
        List<Customer> customersFromDB = service.findByFirstName(customer.getFirstName());

        // then
        assertEquals(customersFromDB.get(0), customer);
    }

    @Test
    public void shouldFindCustomerByLastName() {
        //given
        service.create(customer);

        // when
        List<Customer> customersFromDB = service.findByLastName(customer.getLastName());

        // then
        assertEquals(customersFromDB.get(0), customer);
    }

    @Test
    public void shouldFindCustomerByAddressLine() {
        //given
        service.create(customer);

        // when
        String line = customer.getAddresses().get(0).getLine1();
        List<Customer> customersFromDB = service.findAllByAddressLine(line);

        // then
        assertEquals(customersFromDB.get(0), customer);
    }

    @Test
    public void shouldFindCustomerByAccountCardNumber() {
        //given
        service.create(customer);

        // when
        Long cardNumber = customer.getAccounts().get(0).getCardNumber();
        Customer customerFromDB = service.findByCardNumber(cardNumber);

        // then
        assertEquals(customerFromDB, customer);
    }

    @Test
    public void shouldFindTwoCustomersWithExpiredCards() {
        //given
        customer.setId(new ObjectId().toString());
        service.create(customer);
        customer.setId(new ObjectId().toString());
        service.create(customer);

        // when
        List<Customer> customersFromDB = service.findAllWithExpiredCards();

        // then
        assertEquals(2, customersFromDB.size());
    }

    @Test
    public void shouldUpdateCustomer() {
        //given
        customer = service.create(customer);

        // when
        customer.setFirstName("AAAAAAAAA");
        Customer updatedCustomer = service.update(customer);

        // then
        assertEquals("AAAAAAAAA", updatedCustomer.getFirstName());
    }

    @Test
    public void deleteById() {
        //given;
        customer = service.create(customer);
        List<Customer> customers = service.findAll();
        assertEquals(1, customers.size());

        // when
        service.deleteById(customer.getId());
        customers = service.findAll();

        // then
        assertEquals(0, customers.size());
    }

    @Test
    public void deleteAll() {
        //given;
        customer.setId(new ObjectId().toString());
        service.create(customer);
        customer.setId(new ObjectId().toString());
        service.create(customer);
        List<Customer> customers = service.findAll();
        assertEquals(2, customers.size());

        // when
        service.deleteAll();
        customers = service.findAll();

        // then
        assertEquals(0, customers.size());
    }
}
