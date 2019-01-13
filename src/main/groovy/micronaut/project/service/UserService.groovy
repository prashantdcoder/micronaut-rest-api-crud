package micronaut.project.service

import grails.gorm.transactions.Transactional
import io.micronaut.context.annotation.Prototype
import micronaut.project.AppUtilities.AppUtils
import micronaut.project.CO.AddCO
import micronaut.project.CO.UpdateCO
import micronaut.project.DTO.DTO
import micronaut.project.VO.EditVO
import micronaut.project.domain.User

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

    @Transactional
    public DTO add(AddCO addCO) {
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
    public DTO<EditVO> edit(String userId) {
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
    public DTO update(UpdateCO updateCO) {
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
}
