package micronaut.project.service

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import micronaut.project.AppUtilities.AppUtils
import micronaut.project.CO.AddCO
import micronaut.project.CO.RegistrationCO
import micronaut.project.CO.UpdateCO
import micronaut.project.DTO.DTO
import micronaut.project.VO.EditVO
import micronaut.project.VO.UserVO
import micronaut.project.VO.ViewVO
import micronaut.project.constants.RoleConstant
import micronaut.project.domain.Profile
import micronaut.project.domain.Role
import micronaut.project.domain.User

@Service(User)
class UserService {

    DTO index() {
        DTO dto
        try {
            String welcome = "Welcome to Micronaut Rest API C.R.U.D Application"
            dto = new DTO(true, welcome)
        } catch (Exception e) {
            dto = new DTO(false, e.getMessage())
        }
        return dto
    }

    @Transactional
    DTO add(AddCO addCO) {
        DTO dto
        try {

            User user = User.findByUsername(addCO.username)
            if (user) {
                dto = new DTO(false, "This username has already exist, try another one!")
            } else {
                User newUser = new User(
                        firstName: addCO.firstName,
                        lastName: addCO.lastName,
                        username: addCO.username,
                        password: addCO.password.md5(),
                        dateOfBirth: AppUtils.parseStringDateToSql(addCO.dateOfBirth))
                newUser.save(flush: true, failOnError: true)
                dto = new DTO(true, "User has been created successfully")
            }

        } catch (Exception e) {
            dto = new DTO(false, e.getMessage())
        }
        return dto
    }

    @Transactional
    DTO<EditVO> edit(String userId) {
        DTO<EditVO> dto

        try {
            User user = User.findById(userId.toLong())
            if (!user) {
                dto = new DTO(false, "No record exist")
            } else {
                dto = new DTO(true, new EditVO(user))
            }
        } catch (Exception e) {
            dto = new DTO(false, e.getMessage())
        }
        return dto
    }

    @Transactional
    DTO update(UpdateCO updateCO) {
        DTO dto
        try {

            User user = User.findById(updateCO.userId.toLong())
            if (!user) {
                dto = new DTO(false, "No record found")
            } else {
                user.firstName = updateCO.firstName
                user.lastName = updateCO.lastName
                user.dateOfBirth = AppUtils.parseStringDateToSql(updateCO.dateOfBirth)
                user.save(flush: true, failOnError: true)
                dto = new DTO(true, "Your profile has been updated successfully.")
            }

        } catch (Exception e) {
            dto = new DTO(false, e.getMessage())
        }
        return dto
    }

    @Transactional
    DTO delete(String userId) {
        DTO dto
        try {

            User user = User.findById(userId.toLong())
            if (!user) {
                dto = new DTO(false, "No record found")
            } else {
                user.delete(flush: true, failOnError: true)
                dto = new DTO(true, "Your record has been deleted successfully")
            }

        } catch (Exception e) {
            dto = new DTO(false, e.getMessage())
        }

        return dto
    }

    DTO view() {
        DTO dto
        try {
            List<User> userList = User.fetchAllUsers()
            dto = new DTO(true, userList.collect { new ViewVO(it) })
        } catch (Exception e) {
            dto = new DTO(false, e.getMessage())
        }
        return dto
    }

    @Transactional
    DTO registration(RegistrationCO registrationCO) {
        DTO dto = null
        try {
            User user = User.findByUsernameOrEmail(registrationCO.username, registrationCO.email)
            if (user) {
                dto = new DTO(false, "Username/email already exists")
            } else {

                user = new User(
                        username: registrationCO.username,
                        fullName: registrationCO.fullName,
                        dateOfBirth: AppUtils.parseStringDateToSql(registrationCO.dateOfBirth),
                        password: registrationCO.password.md5(),
                        phoneCode: registrationCO.phoneCode,
                        phone: registrationCO.phone,
                        email: registrationCO.email
                ).save()
                Profile profile = new Profile(role: Role.findByAuthority(RoleConstant.USER), user: user)
                profile.save()
                dto = new DTO(true, "Registration is successful.")
            }


        } catch (Exception ex) {
            dto = new DTO(false, ex.message)
        }
        return dto
    }

}
