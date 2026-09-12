package work.onlinebookshop.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserRequestDto {
    @NotBlank(message = "Email must not be blank")
    private String email;
    @NotBlank(message = "Password must not be blank")
    private String password;
    @NotBlank(message = "FirstName must not be blank")
    private String firstName;
    @NotBlank(message = "LastName must not be blank")
    private String lastName;
    @NotBlank(message = "ShippingAddress must not be blank")
    private String shippingAddress;
}
