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

    /**
     * Creates a new customer record
     * @param request CustomerResponse object containing customer details
     * @return String confirmation message
     */
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


    /**
     * Retrieves all registered customers
     * @return Collection of all CustomerResponse objects
     */
    @GetMapping("/getData")
    public Collection<CustomerResponse> getCustomers() {
        return customers;
    }

    /**
     * Retrieves a specific customer by their ID
     * @param customerId The ID of the customer to retrieve
     * @return CustomerResponse object for the requested customer
     * @throws RuntimeException if customer is not found
     */
    @GetMapping("/getById/{customerId}")
    public CustomerResponse getCustomerById(@PathVariable int customerId) {
        return customers.stream()
                .filter(customer -> customer.getCustomerId() == customerId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer with ID " + customerId + " not found."));
    }

    /**
     * Deletes a customer by their ID
     * @param customerId The ID of the customer to delete
     * @return String confirmation message indicating success or failure
     */
    @DeleteMapping("/deleteById/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {
        boolean removed = customers.removeIf(customer -> customer.getCustomerId() == customerId);
        return removed
                ? "Customer with ID " + customerId + " deleted successfully."
                : "Customer with ID " + customerId + " not found.";
    }

    /**
     * Updates all fields of a customer record
     * @param request CustomerRequest object containing updated customer details
     * @return String confirmation message indicating success or failure
     */
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

    /**
     * Partially updates specific fields of a customer record
     * @param customerId The ID of the customer to update
     * @param mobileNumber mobile number to update the customer data
     * @return String confirmation message indicating success or failure
     */
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
