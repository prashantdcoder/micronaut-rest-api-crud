package micronaut.project.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import micronaut.project.DTO.DTO
import micronaut.project.service.LoginService

import javax.inject.Inject

@Controller("/login")
class LoginController {

    @Inject
    LoginService loginService

    @Post(value = "/facebook", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    HttpResponse<DTO> facebook() {
        return HttpResponse.ok(loginService.faceBookLogin())
    }
}
