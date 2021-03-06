package com.tacocloud.tacocloud.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Order {

    private Long id;
    private Date placedAt;
    private List<Taco> tacos = new ArrayList<>();

//    @NotBlank(message="Name is required")
    private String name;

//    @NotBlank(message="Name is required")
    private String street;

//    @NotBlank(message="Name is required")
    private String city;

//    @NotBlank(message="Name is required")
    private String state;

//    @NotBlank(message="Name is required")
    private String zip;

//    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

//    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\\\/])([1-9][0-9])$")
    private String ccExpiration;

//    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    public void addDesign(Taco design) {
        this.tacos.add(design);
    }
}
