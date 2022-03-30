package com.example.demo.controller;

import com.example.demo.jwt.JwtTokenUtil;
import com.example.demo.model.Account;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignUpForm;
import com.example.demo.payload.response.JwtResponse;
import com.example.demo.payload.response.ResponseMessage;
import com.example.demo.service.AccountService;
import com.example.demo.service.RoleService;
import com.example.demo.service.impl.AccountDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/public")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @PostMapping("/signup")
    public ResponseEntity<?> resgister (@Valid @RequestBody SignUpForm signUpForm){
        if (accountService.existsByAccountName(signUpForm.getUserName())){
            return new ResponseEntity<>(new ResponseMessage("existed!!!"), HttpStatus.OK);
        }

        Account account = new Account(signUpForm.getUserName(), passwordEncoder.encode(signUpForm.getPassword()));

//        roleService.setRole(13, 1);

        accountService.save(account);

        System.out.println(account.getAccountName() + "username + " + account.getPassword() + " is pass");

        Long idAccountAfterCreated = accountService.findAccountIdByAccountName(account.getAccountName());
        //Set default role is "ROLE_USER"
        roleService.setRole(idAccountAfterCreated, 1);

        return new ResponseEntity<>(new ResponseMessage("Create new success"), HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticateAccount(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getAccountName(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenUtil.createToken(authentication);

//        AccountDetailsImpl accountDetails = (AccountDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        AccountDetailsImpl accountDetails = (AccountDetailsImpl) authentication.getPrincipal();

        List<String> roles = accountDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtResponse(
                        token,
                        accountDetails.getUsername(),
                        authentication.getAuthorities()
                ));
    }
}
