package micronaut.project.domain

import grails.gorm.annotation.Entity

@Entity
class User {

    String firstName
    String lastName
    String username
    String password
    Date dateOfBirth

    static constraints = {
        firstName(nullable: true)
        lastName(nullable: true)
        username(nullable: false)
        password(nullable: false)
        dateOfBirth(nullable: false)
    }

    static mapping = {
        table 'app_user'
    }

    static List<User> fetchAllUsers() {
        List<User> userList = createCriteria().list {} as List<User>
        return userList
    }
}
