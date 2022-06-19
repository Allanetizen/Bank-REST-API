package com.example.banking.Auth;

import com.example.banking.Account.Account;
import com.example.banking.Account.AccountService;
import com.example.banking.Customer.Customer;
import com.example.banking.Customer.CustomerRepository;
import com.example.banking.models.JwtResponse;
import com.example.banking.models.MessageResponse;
import com.example.banking.models.SignInRequest;
import com.example.banking.models.SignUpRequest;
import com.example.banking.security.JwtUtils;
import com.example.banking.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PasswordEncoder encoder;


    @Autowired
    AccountService accountService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
        System.out.println(signInRequest.toString());
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getID(), signInRequest.getPin()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail()
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerCustomer(@RequestBody SignUpRequest signUpRequest) {
        System.out.println(signUpRequest);
        if(customerRepository.findOneByCustomerId(signUpRequest.getID()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: ID is already taken!"));
        }

        // generate random PIN
        int min = 1000;
        int max = 9999;
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);

        Customer customer = new Customer(signUpRequest.getCustomerName(), signUpRequest.getID(), encoder.encode(""+random_int), "","","");
        System.out.println("====>"+customer.toString());
        customerRepository.save(customer);
        accountService.createAccount(customer.getCustomerId());
        return ResponseEntity.ok(new MessageResponse("Customer registered successfully. Your PIN is " +random_int));
    }

}
