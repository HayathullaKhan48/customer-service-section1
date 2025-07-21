package com.customer.service.section1.controller;

import com.customer.service.section1.request.CustomerRequest;
import com.customer.service.section1.response.CustomerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    Collection<CustomerResponse> customers = new ArrayList<>();

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerRequest request) {
        customers.add(CustomerResponse.builder()
                        .customerId(request.getCustomerId())
                        .customerName(request.getCustomerName())
                        .customerAddress(request.getCustomerAddress())
                        .customerMobileNumber(request.getCustomerMobileNumber())
                .build());
        return ResponseEntity.ok("Customer is successfully registered...");
    }


    @GetMapping("/getData")
    public Collection<CustomerResponse> getCustomers() {
        return customers;
    }

    @GetMapping("/getById/{customerId}")
    public CustomerResponse getCustomerById(@PathVariable int customerId) {
        return customers.stream()
                .filter(customer -> customer.getCustomerId() == customerId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer with ID " + customerId + " not found."));
    }

    @DeleteMapping("/deleteById/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {
        boolean removed = customers.removeIf(customer -> customer.getCustomerId() == customerId);
        return removed
                ? "Customer with ID " + customerId + " deleted successfully."
                : "Customer with ID " + customerId + " not found.";
    }

    @PutMapping("/update")
    public String updateCustomer(@RequestBody CustomerRequest request) {
        for (CustomerResponse customer : customers) {
            if (customer.getCustomerId() == request.getCustomerId()) {
                customer.setCustomerName(request.getCustomerName());
                customer.setCustomerAddress(request.getCustomerAddress());
                customer.setCustomerMobileNumber(request.getCustomerMobileNumber());
                return "Customer with ID " + request.getCustomerId() + " was updated successfully.";
            }
        }
        return "Customer with ID " + request.getCustomerId() + " not found.";
    }

    @PatchMapping("/update/mobileNumber/{customerId}/{mobileNumber}")
    public String patchCustomer(@PathVariable int customerId, @PathVariable String mobileNumber) {
        Optional<CustomerResponse> customerData = customers.stream()
                .filter(customerResponse -> customerResponse.getCustomerId() == customerId)
                .findFirst();
        if (customerData.isPresent()) {
            CustomerResponse customerResponse = customerData.get();
            customerResponse.setCustomerMobileNumber(mobileNumber);
        }
        return "Customer with ID " + customerId + " not found.";
    }
}
