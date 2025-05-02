package guru.springframework.spring_6_rest_mvc.services;

import guru.springframework.spring_6_rest_mvc.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    List<Customer> getCustomers();
    Optional<Customer> getCustomerById(UUID id);
    Customer create(Customer customer);
    void update(UUID id, Customer customer);
    void delete(UUID id);
    void patch(UUID id, Customer customer);
}
