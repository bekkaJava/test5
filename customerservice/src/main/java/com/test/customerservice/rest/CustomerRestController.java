package com.test.customerservice.rest;

import com.test.customerservice.entity.Customer;
import com.test.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
    private CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/customers/{customerID}")
    public Customer getCustomer(@PathVariable int customerID) {

        Customer customer = customerService.findById(customerID);

        if (customer == null) {
            throw new RuntimeException("Customer id not found - " + customerID);
        }

        return customer;
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {

        Customer customer = customerService.findById(customerId);


        if (customer == null) {
            throw new RuntimeException("Customer id not found - " + customerId);
        }

        customerService.deleteById(customerId);

        return "Deleted Customer id - " + customerId;
    }

}
