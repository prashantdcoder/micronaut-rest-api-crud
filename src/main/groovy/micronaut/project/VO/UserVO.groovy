package micronaut.project.VO

import io.micronaut.security.token.jwt.render.AccessRefreshToken
import micronaut.project.domain.User

class UserVO {

    String username
    String fullName
    List<String> roles
    String accessToken
    String refreshToken

    UserVO(User user, List<String> roles, Optional<AccessRefreshToken> accessRefreshToken) {
        this.username = user.username
        this.fullName = user.fullName ?: ''
        this.roles = roles
        this.accessToken = accessRefreshToken.get().accessToken
        this.refreshToken = accessRefreshToken.get().refreshToken
    }
}
