package guru.springframework.spring_6_rest_mvc.controllers;

import guru.springframework.spring_6_rest_mvc.model.Customer;
import guru.springframework.spring_6_rest_mvc.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerController {

    public static final String BASE_PATH = "/api/v1/customer";
    public static final String BASE_PATH_ID = BASE_PATH + "/{customerId}";
    private final CustomerService customerService;

    @GetMapping(BASE_PATH)
    public List<Customer> getCustomers(){

        log.debug("Get All Customer - in CONTROLLER");

        return customerService.getCustomers();
    }

    @GetMapping(BASE_PATH_ID)
    public Customer getById(@PathVariable UUID customerId){

        log.debug("Get Customer by ID - in CONTROLLER");

        return customerService.getCustomerById(customerId).orElseThrow(NotFoundException::new);
    }

    @PostMapping(BASE_PATH)
    public ResponseEntity create(@RequestBody Customer customer){

        Customer newCustomer = customerService.create(customer);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.LOCATION, "/api/v1/customer/" + newCustomer.getId());

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping(BASE_PATH_ID)
    public ResponseEntity update(@PathVariable UUID customerId, @RequestBody Customer customer){

        customerService.update(customerId, customer);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.LOCATION, "/api/v1/customer/"+customerId);

        return new ResponseEntity(httpHeaders, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BASE_PATH_ID)
    public ResponseEntity deleteById(@PathVariable UUID customerId){

        log.debug("Delete Customer by ID - in CONTROLLER");

        customerService.delete(customerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(BASE_PATH_ID)
    public ResponseEntity patch(@PathVariable UUID customerId, @RequestBody Customer customer){

        customerService.patch(customerId, customer);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.LOCATION, "/api/v1/customer/"+customerId);

        return new ResponseEntity(httpHeaders, HttpStatus.NO_CONTENT);
    }
}