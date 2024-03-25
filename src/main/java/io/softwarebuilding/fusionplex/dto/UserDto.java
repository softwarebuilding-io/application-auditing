package io.softwarebuilding.fusionplex.dto;

import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;

public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -3221310165685977573L;

    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "Password is required")
    private String password;

    public String getUsername() {
        return this.username;
    }

    public void setUsername( final String username ) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword( final String password ) {
        this.password = password;
    }

}
