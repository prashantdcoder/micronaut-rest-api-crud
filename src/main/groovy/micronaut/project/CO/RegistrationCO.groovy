package micronaut.project.CO

import javax.validation.constraints.NotEmpty

class RegistrationCO {
    @NotEmpty
    String username

    @NotEmpty
    String email

    String fullName

    String dateOfBirth

    @NotEmpty
    String password

    String phoneCode

    String phone

}
