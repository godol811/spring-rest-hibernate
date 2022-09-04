package com.godol.springdemo.rest;


import com.godol.springdemo.entity.Customer;
import com.godol.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    // customer 서비스 autowire 시키기
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers(){

        return customerService.getCustomers();
    }
    // GET /customers에 매핑 시키기
    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId){
        Customer theCustomer = customerService.getCustomer(customerId);

        if (theCustomer == null){
            throw new CustomerNotFoundException("고객 아이디가 없습니다 -" + customerId);
        }

        return theCustomer;
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer theCustomer){
        //0번 세팅은 DAO에서 저장으로 알고 있는다.
        theCustomer.setId(0);
        customerService.saveCustomer(theCustomer);

        return theCustomer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer theCustomer){
        customerService.saveCustomer(theCustomer);

        return theCustomer;
    }

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId){

        Customer tempCustomer = customerService.getCustomer(customerId);

        // 만약 없는 아이디면 던지기

        if (tempCustomer == null){
            throw new CustomerNotFoundException("고객 아이디가 없습니다 -" + customerId);
        }

        customerService.deleteCustomer(customerId);

        return "고객 정보 삭제 됨 : " + customerId;
    }
}
