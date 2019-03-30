package micronaut.project

import groovy.transform.CompileStatic
import io.micronaut.discovery.event.ServiceStartedEvent
import io.micronaut.runtime.Micronaut
import io.micronaut.runtime.event.annotation.EventListener
import micronaut.project.service.BootstrapService

import javax.inject.Inject

@CompileStatic
class Application {

    @Inject
    BootstrapService bootstrapService

    @EventListener
    void onStartup(ServiceStartedEvent event) {
        bootstrapService.initRoles()
    }

    static void main(String[] args) {
        Micronaut.run(Application)
    }
}