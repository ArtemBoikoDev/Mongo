package com.mongo.service;

import com.mongo.entity.Customer;
import com.mongo.repository.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Business logic layer for Customer objects.
 *
 * @author Artem Boiko
 */
@Service
public class CustomerService {
    private static final Logger logger = LogManager.getLogger(CustomerService.class);
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer create(Customer customer) {
        customer = repository.save(customer);
        logger.info("Created Customer: {}", customer);
        logger.info("Created Customer's addresses: {}", customer.getAddresses());
        logger.info("Created Customer's accounts: {}", customer.getAccounts());
        return customer;
    }

    public Customer findById(String id) {
        Customer customer = repository.findById(id)
                                      .orElseThrow(() -> new IllegalStateException("No such Customer with id = " + id));
        logger.info("Found Customer: {}", customer);
        return customer;
    }

    public List<Customer> findAll() {
        List<Customer> customers = repository.findAll();
        customers.forEach(customer -> {
            logger.info("Found Customer: {}", customer);
            logger.info("Found Customer's addresses: {}", customer.getAddresses());
            logger.info("Found Customer's accounts: {}", customer.getAccounts());
        });
        return customers;
    }

    public List<Customer> findByFirstName(String firstName) {
        List<Customer> customers = repository.findAllByFirstName(firstName);
        customers.forEach(customer -> {
            logger.info("Found Customer: {}", customer);
            logger.info("Found Customer's addresses: {}", customer.getAddresses());
            logger.info("Found Customer's accounts: {}", customer.getAccounts());
        });
        return customers;
    }

    public List<Customer> findByLastName(String lastName) {
        List<Customer> customers = repository.findAllByLastName(lastName);
        customers.forEach(customer -> {
            logger.info("Found Customer: {}", customer);
            logger.info("Found Customer's addresses: {}", customer.getAddresses());
            logger.info("Found Customer's accounts: {}", customer.getAccounts());
        });
        return customers;
    }

    public List<Customer> findAllByAddressLine(String address) {
        List<Customer> customers = repository.findAllByAddressesFirstLine(address);
        if (customers.isEmpty()) {
            customers = repository.findAllByAddressesSecondLine(address);
        }
        customers.forEach(customer -> {
            logger.info("Found Customer: {}", customer);
            logger.info("Found Customer's addresses: {}", customer.getAddresses());
            logger.info("Found Customer's accounts: {}", customer.getAccounts());
        });
        return customers;
    }

    public Customer findByCardNumber(Long cardNumber) {
        Customer customer = repository.findFirstByAccountsCardNumber(cardNumber);
        logger.info("Found Customer: {}", customer);
        logger.info("Found Customer's accounts: {}", customer.getAccounts());
        return customer;
    }

    public List<Customer> findAllWithExpiredCards() {
        Date expirationDateDeadLine = new Date();
        List<Customer> customers = repository.findAllWithAccountsExpirationDate(expirationDateDeadLine);
        customers.forEach(customer -> {
            logger.info("Found Customer: {}", customer);
            logger.info("Found Customer's addresses: {}", customer.getAddresses());
            logger.info("Found Customer's accounts: {}", customer.getAccounts());
        });
        return customers;
    }

    public Customer update(Customer customer) {
        customer = repository.save(customer);
        logger.info("Updated Customer: {}", customer);
        logger.info("Updated Customer's addresses: {}", customer.getAddresses());
        logger.info("Updated Customer's accounts: {}", customer.getAccounts());
        return customer;
    }

    public void deleteById(String id) {
        repository.deleteById(id);
        logger.info("Deleted Customer by id: {}", id);
    }

    public void deleteAll() {
        repository.deleteAll();
        logger.info("All Customers are deleted.");
    }
}
