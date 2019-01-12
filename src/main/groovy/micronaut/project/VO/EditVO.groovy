package micronaut.project.VO

import micronaut.project.AppUtilities.AppUtils
import micronaut.project.domain.User

class EditVO {

    String firstName
    String lastName
    String dateOfBirth

    EditVO(User user) {
        this.firstName = user.firstName
        this.lastName = user.lastName
        this.dateOfBirth = AppUtils.parseSqlDateToString(user.dateOfBirth)
    }
}
