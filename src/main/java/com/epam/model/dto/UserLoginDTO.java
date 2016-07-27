package com.epam.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

/**
 * Created by fg on 7/26/2016.
 *
 * The class handles data transmission of authorizing user from view to controller.
 */

@Setter
@Getter
@EqualsAndHashCode(of = {"email", "password"})
@ToString(of = {"email", "password"})
public class UserLoginDTO {
    @NotNull
    @Size(min = 1)
    private String email;

    @NotNull
    @Size(min = 1)
    private String password;
}
