package micronaut.project.CO

import javax.validation.constraints.NotEmpty

class UpdateCO {

    @NotEmpty
    String userId

    String firstName

    String lastName

    @NotEmpty
    String dateOfBirth

}
