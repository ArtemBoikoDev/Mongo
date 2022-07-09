package com.mongo.repository;

import com.mongo.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Spring Data JPA repository for Customer entity.
 *
 * @author Artem Boiko
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {
    List<Customer> findAllByFirstName(String firstName);

    List<Customer> findAllByLastName(String lastName);

    @Query(value = "{ 'addresses': { $elemMatch: { 'line1' : ?0 }}}")
    List<Customer> findAllByAddressesFirstLine(String line);

    @Query(value = "{ 'addresses': { $elemMatch: { 'line2' : ?0 }}}")
    List<Customer> findAllByAddressesSecondLine(String line);

    @Query(value = "{ 'accounts': { $elemMatch: { 'cardNumber' : ?0 }}}")
    Customer findFirstByAccountsCardNumber(Long cardNumber);

    @Query(value = "{ 'accounts': { $elemMatch: {'expirationDate' : {$lt : ?0} }}}")
    List<Customer> findAllWithAccountsExpirationDate(Date expirationDateDeadLine);
}
