package kr.co.careerwryposting.domain.post

import jakarta.persistence.*
import kr.co.careerwryposting.common.exeption.InvalidInputException
import kr.co.careerwryposting.common.util.TokenGenerator
import kr.co.careerwryposting.domain.AbstractEntity
import kr.co.careerwryposting.interfaces.post.PostDto
import org.hibernate.validator.constraints.URL

@Embeddable
data class UserProfile(
    var nickname: String,
    var positionJob: String, // TODO :: 추후 Enum 타입으로 변경하여야 함
    @field:URL var profileImage: String
)

@Entity
class Post(
    @Column(nullable = false, length = 40)
    var title: String,

    @Column(nullable = false)
    var content: String,

    @Embedded
    var profile: UserProfile,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long? = null

) : AbstractEntity() {

    @Column(unique = true)
    val token: String

    init {
        if (title.isBlank()) {
            throw InvalidInputException("제목은 비어있을 수 없습니다")
        }
        if (content.isBlank()) {
            throw InvalidInputException("본문은 비어있을 수 없습니다")
        }
        token = TokenGenerator.randomCharacterWithPrefix("Post_")
    }

    fun updatePost(request: PostDto.PostUpdateRequest) {
        this.title = request.title
        this.content = request.content
    }

    companion object {
        fun fixture(
            title: String = "제목",
            content: String = "본문",
            nickname: String = "사용자 닉네임",
            positionJob: String = "프론트엔드",
            profileImage: String = "https://e7.pngegg.com/pngimages/1000/665/png-clipart-computer-icons-profile-s-free-angle-sphere.png",
        ): Post {
            return Post(
                title = title,
                content = content,
                UserProfile(
                    nickname = nickname,
                    positionJob = positionJob,
                    profileImage = profileImage,
                )
            )
        }
    }
}
