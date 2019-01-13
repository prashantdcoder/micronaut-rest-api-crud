package micronaut.project.service

import io.micronaut.context.annotation.Prototype
import micronaut.project.DTO.DTO

@Prototype
class UserService {

    public DTO index() {
        DTO dto
        try {
            String welcome = "Welcome to Micronaut Rest API C.R.U.D Application"
            dto = new DTO(true, welcome)
        } catch (Exception e) {
            dto = new DTO(false, e.getMessage())
        }
        return dto
    }
}
