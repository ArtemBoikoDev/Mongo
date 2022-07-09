package com.mongo.controller;

import com.mongo.entity.Customer;
import com.mongo.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API Controller for Customer objects.
 *
 * @author Artem Boiko
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return service.create(customer);
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Customer> findAll() {
        return service.findAll();
    }

    @PostMapping(value = "/first-name",
                 consumes = MediaType.TEXT_PLAIN_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> findByFirstName(@RequestBody String firstName) {
        return service.findByFirstName(firstName);
    }

    @PostMapping(value = "/last-name",
                 consumes = MediaType.TEXT_PLAIN_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> findByLastName(@RequestBody String lastName) {
        return service.findByLastName(lastName);
    }

    @PostMapping(value = "/address",
                 consumes = MediaType.TEXT_PLAIN_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> findAllByAddress(@RequestBody String address) {
        return service.findAllByAddressLine(address);
    }

    @GetMapping("/card-number/{cardNumber}")
    public Customer findByCardNumber(@PathVariable Long cardNumber) {
        return service.findByCardNumber(cardNumber);
    }

    @GetMapping("/expired")
    public List<Customer> findAllWithExpiredCards() {
        return service.findAllWithExpiredCards();
    }

    @PutMapping
    public Customer update(@RequestBody Customer customer) {
        return service.update(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        service.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll() {
        service.deleteAll();
    }
}
