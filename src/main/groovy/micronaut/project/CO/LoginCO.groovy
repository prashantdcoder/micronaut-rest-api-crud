package micronaut.project.CO

import javax.validation.constraints.NotEmpty

class LoginCO {

    @NotEmpty
    String username

    @NotEmpty
    String password
}
