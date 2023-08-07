package com.example.ProjectGoods.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Date;

@Getter
@Setter
public class GoodsDTO {
    private Long id;
    private String product;
    private double buying;
    private double resell;
    private double weight;
    public String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
    }
    public Date getCreatedDate(){
        return new Date(System.currentTimeMillis());
    }
}