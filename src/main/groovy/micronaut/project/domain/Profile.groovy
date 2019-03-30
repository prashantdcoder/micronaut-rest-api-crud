package micronaut.project.domain

import grails.gorm.annotation.Entity

@Entity
class Profile {

    Role role
    Date dateCreated
    Date lastUpdated
    boolean isActive = true

    static belongsTo = [user: User]
}
