package com.customer.service.section1.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {
     private int customerId;
     private String customerName;
     private String customerAddress;
     private String customerMobileNumber;
}
