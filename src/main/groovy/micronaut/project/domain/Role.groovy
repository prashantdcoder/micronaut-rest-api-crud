package micronaut.project.domain

import grails.gorm.annotation.Entity

@Entity
class Role {

    String name
    String authority
    String description
    Date dateCreated
    Date lastUpdated
    boolean isActive = true
}
