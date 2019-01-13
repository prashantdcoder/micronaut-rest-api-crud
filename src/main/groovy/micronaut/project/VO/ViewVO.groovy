package micronaut.project.VO

import micronaut.project.AppUtilities.AppUtils
import micronaut.project.domain.User

class ViewVO {

    String firstName
    String lastName
    String username
    String dateOfBirth

    ViewVO(User user) {
        this.firstName = user.firstName
        this.lastName = user.lastName
        this.username = user.username
        this.dateOfBirth = AppUtils.parseSqlDateToString(user.dateOfBirth)
    }
}
