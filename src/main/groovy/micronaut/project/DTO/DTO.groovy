package micronaut.project.DTO

class DTO<T> {

    boolean status
    T data

    DTO() {
        this.status = true
    }

    DTO(boolean status, T data) {
        this.status = status
        this.data = data
    }

}
