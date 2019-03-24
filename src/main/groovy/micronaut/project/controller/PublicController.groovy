package micronaut.project.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.validation.Validated
import micronaut.project.CO.LoginCO
import micronaut.project.DTO.DTO
import micronaut.project.service.AuthenticationService

import javax.inject.Inject
import javax.validation.Valid

@Validated
@Secured("isAnonymous()")
@Controller("/public")
class PublicController {

    @Inject
    AuthenticationService authenticationService

    @Post(value = "/auth", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    HttpResponse<DTO> authentication(@Body @Valid LoginCO loginCO) {
        return HttpResponse.ok(authenticationService.generateJWT(loginCO))
    }
}
