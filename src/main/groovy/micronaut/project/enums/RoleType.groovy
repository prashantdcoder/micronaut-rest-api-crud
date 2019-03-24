package micronaut.project.enums

enum RoleType {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN")

    String value

    RoleType(String name) {
        this.value = name
    }


    static List<RoleType> fetchRole() {
        [USER.value, ADMIN.value]
    }
}