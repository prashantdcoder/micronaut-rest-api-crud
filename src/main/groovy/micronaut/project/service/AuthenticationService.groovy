package micronaut.project.service

import io.micronaut.security.authentication.UserDetails
import io.micronaut.security.token.jwt.generator.AccessRefreshTokenGenerator
import io.micronaut.security.token.jwt.render.AccessRefreshToken
import micronaut.project.CO.LoginCO
import micronaut.project.DTO.DTO

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationService {

    @Inject
    AccessRefreshTokenGenerator accessRefreshTokenGenerator


    DTO generateJWT(LoginCO loginCO) {
        DTO dto
        try {
            dto = new DTO(true, generateJwtAccessToken(loginCO))
        } catch (Exception ex) {
            dto = new DTO(false, ex.getMessage())
        }
        return dto
    }


    Map generateJwtAccessToken(LoginCO loginCO) {
        UserDetails userDetails = new UserDetails(loginCO.username, ["ROLE_USER", "ROLE_ADMIN"])
        Optional<AccessRefreshToken> accessRefreshToken = accessRefreshTokenGenerator.generate(userDetails)
        Map map = [
                'username'    : loginCO.username,
                'accessToken' : accessRefreshToken.get().accessToken,
                'refreshToken': accessRefreshToken.get().refreshToken

        ]
        return map


    }


}
