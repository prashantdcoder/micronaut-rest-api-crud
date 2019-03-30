package micronaut.project.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.validation.Validated
import micronaut.project.CO.LoginCO
import micronaut.project.CO.RegistrationCO
import micronaut.project.DTO.DTO
import micronaut.project.service.AuthenticationService
import micronaut.project.service.UserService

import javax.inject.Inject
import javax.validation.Valid

@Validated
@Secured("isAnonymous()")
@Controller("/public")
class PublicController {

    @Inject
    AuthenticationService authenticationService

    @Inject
    UserService userService

    @Post(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    HttpResponse<DTO> login(@Body @Valid LoginCO loginCO) {
        return HttpResponse.ok(authenticationService.login(loginCO))
    }

    @Post(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED, produces = MediaType.APPLICATION_JSON)
    HttpResponse<DTO> register(RegistrationCO registrationCO) {
        return HttpResponse.ok(userService.registration(registrationCO))
    }
}
