package wyse.common.model.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="usr")
data class User (
        @Id
        val userId: String,
        val password: String,
        val firstName: String,
        val lastName: String,
        val roleName: RoleName
)
enum class RoleName {
    ADMIN,
    USER
}
