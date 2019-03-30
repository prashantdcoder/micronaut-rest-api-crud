package micronaut.project.domain

import grails.gorm.annotation.Entity
import org.hibernate.FetchMode

@Entity
class User {

    String username
    String fullName
    Date dateOfBirth
    String password
    String phoneCode
    String phone
    String email
    Date dateCreated
    Date lastUpdated
    boolean isActive = true


    static hasMany = [profile: Profile]

    static constraints = {
        fullName(nullable: true)
    }

    static mapping = {
        table 'app_user'
    }

    static List<User> fetchAllUsers() {
        List<User> userList = createCriteria().list {} as List<User>
        return userList
    }

    static User findByUsernameOrEmail(String username, String email) {
        User user = createCriteria().get {
            or {
                eq('username', username)
                eq('email', email)
            }
        } as User
        return user ?: null
    }

    static User findByUsername(String username) {
        User user = createCriteria().get {
            eq('username', username)
        } as User
        return user ?: null
    }

    static List<String> fetchUserRoles(User user) {
        List<String> roles = Profile.createCriteria().list {
            fetchMode('user', FetchMode.JOIN)
            fetchMode('role', FetchMode.JOIN)
            eq('user', user)
            projections {
                'role' {
                    property("authority")
                }
            }
        }.collect { it } as List<String>
        return roles
    }


}
