package com.example.ProjectGoods.dto;

import com.example.ProjectGoods.model.DeliveryAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto2 {
    Long id;
    String firstName;
    String lastName;
    String email;
    Set<DeliveryAddress> addressAvailable;
}
