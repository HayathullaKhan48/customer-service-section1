package com.customer.service.section1.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerResponse {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerMobileNumber;
}
