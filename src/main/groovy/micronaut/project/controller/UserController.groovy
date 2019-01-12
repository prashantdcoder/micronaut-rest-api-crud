package micronaut.project.controller

import io.micronaut.context.annotation.Bean
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import micronaut.project.CO.AddCO
import micronaut.project.CO.UpdateCO
import micronaut.project.DTO.DTO
import micronaut.project.service.UserService
import javax.inject.Inject
import javax.validation.Valid

@Controller("/user")
class UserController {

    @Inject
    UserService userService

    @Get(value = "/index", consumes = MediaType.TEXT_PLAIN)
    HttpResponse<DTO> index() {
        return HttpResponse.ok(userService.index())
    }

    @Post(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    HttpResponse<DTO> add(@Valid AddCO addCO){
        return HttpResponse.ok(userService.add(addCO))
    }

    @Post(value = "/edit",consumes = MediaType.APPLICATION_FORM_URLENCODED)
    HttpResponse<DTO> edit(String userId){
        return HttpResponse.ok(userService.edit(userId))
    }

    @Post(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    HttpResponse<DTO> update(@Body @Valid UpdateCO updateCO){
        return HttpResponse.ok(userService.update(updateCO))
    }

}
