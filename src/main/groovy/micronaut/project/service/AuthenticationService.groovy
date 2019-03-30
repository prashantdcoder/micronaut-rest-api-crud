package micronaut.project.service

import io.micronaut.security.authentication.UserDetails
import io.micronaut.security.token.jwt.generator.AccessRefreshTokenGenerator
import io.micronaut.security.token.jwt.render.AccessRefreshToken
import micronaut.project.CO.LoginCO
import micronaut.project.DTO.DTO
import micronaut.project.VO.UserVO
import micronaut.project.domain.User

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationService {

    @Inject
    AccessRefreshTokenGenerator accessRefreshTokenGenerator


    DTO login(LoginCO loginCO) {
        DTO dto
        try {
            User user = User.findByUsername(loginCO.username)
            if (!user) {
                dto = new DTO(false, "No record exists")
            } else {
                dto = new DTO(true, generateJwtAccessToken(user))
            }
        } catch (Exception ex) {
            dto = new DTO(false, ex.getMessage())
        }
        return dto
    }


    UserVO generateJwtAccessToken(User user) {
        List<String> roles = User.fetchUserRoles(user)
        UserDetails userDetails = new UserDetails(user.username, roles)
        Optional<AccessRefreshToken> accessRefreshToken = accessRefreshTokenGenerator.generate(userDetails)
        return new UserVO(user, roles, accessRefreshToken)
    }


}
