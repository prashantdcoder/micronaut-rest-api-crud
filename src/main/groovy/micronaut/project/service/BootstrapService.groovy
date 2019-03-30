package micronaut.project.service

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j
import micronaut.project.domain.Role
import micronaut.project.enums.RoleType

@Slf4j
@Service
class BootstrapService {

    @Transactional
    void initRoles() {

        if (!Role.count()) {
            log.info("Initializing roles....")
            List<Role> roleList = []
            roleList << new Role(name: RoleType.USER, authority: RoleType.USER.value, description: "User role")
            roleList << new Role(name: RoleType.ADMIN, authority: RoleType.ADMIN.value, description: "Admin role")
            roleList*.save()
        }
    }
}
