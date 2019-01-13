package micronaut.project.controller

import io.micronaut.context.annotation.Bean
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import micronaut.project.DTO.DTO
import micronaut.project.service.UserService
import org.springframework.beans.factory.annotation.Autowired

import javax.inject.Inject

@Controller("/user")
class UserController {

    @Inject
    UserService userService

    @Get(value = "/index", consumes = MediaType.TEXT_PLAIN)
    HttpResponse<DTO> index() {
        return HttpResponse.ok(userService.index())
    }

}
