package micronaut.project.CO

import javax.validation.constraints.NotNull

class AddCO {

    String firstName

    String lastName

    @NotNull
    String username

    @NotNull
    String password

    String dateOfBirth
}
