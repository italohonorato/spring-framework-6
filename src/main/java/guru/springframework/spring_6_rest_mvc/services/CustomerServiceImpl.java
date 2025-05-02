package guru.springframework.spring_6_rest_mvc.services;

import guru.springframework.spring_6_rest_mvc.controllers.NotFoundException;
import guru.springframework.spring_6_rest_mvc.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, Customer> customerMap;

    public CustomerServiceImpl() {

        this.customerMap = new HashMap<>();

        Customer customer1 = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Stefano Honorato")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer2 = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Paulina Vilches")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer3 = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Aldo Honorato")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        this.customerMap.put(customer1.getId(), customer1);
        this.customerMap.put(customer2.getId(), customer2);
        this.customerMap.put(customer3.getId(), customer3);
    }

    @Override
    public List<Customer> getCustomers() {

        log.debug("Get All Customers - in service. Id");

        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Optional<Customer> getCustomerById(UUID id) {

        log.debug("Get Customer By ID - in service. Id: " + id.toString());

        return Optional.of(customerMap.get(id));
    }

    @Override
    public Customer create(Customer customer) {

        final Customer newCustomer = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName(customer.getCustomerName())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(newCustomer.getId(), newCustomer);

        return newCustomer;
    }

    @Override
    public void update(UUID id, Customer customer) {

        Customer targetCustomer = getCustomerById(id).orElseThrow(NotFoundException::new);
        targetCustomer.setCustomerName(customer.getCustomerName());
        targetCustomer.setVersion(customer.getVersion());
        targetCustomer.setLastModifiedDate(LocalDateTime.now());

        customerMap.put(id, targetCustomer);
    }

    @Override
    public void delete(UUID id) {
        customerMap.remove(id);
    }

    @Override
    public void patch(UUID id, Customer customer) {

        Customer targetCustomer = getCustomerById(id).orElseThrow(NotFoundException::new);

        if (StringUtils.hasText(customer.getCustomerName())) {
            targetCustomer.setCustomerName(customer.getCustomerName());
        }

        if (customer.getVersion() != null) {
            targetCustomer.setVersion(customer.getVersion());
        }

        customerMap.put(id, targetCustomer);
    }
}
