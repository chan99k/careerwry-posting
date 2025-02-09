package kr.co.careerwryposting.domain.post

import jakarta.persistence.*
import kr.co.careerwryposting.common.exeption.InvalidInputException
import kr.co.careerwryposting.common.util.TokenGenerator
import kr.co.careerwryposting.domain.AbstractEntity
import kr.co.careerwryposting.domain.UserProfile
import kr.co.careerwryposting.domain.like.Like

@Entity
class Post(
    @Column(nullable = false, length = 40)
    var title: String,

    @Column(nullable = false, columnDefinition = "JSON")
    var content: String, // TODO 라인 다섯개까지만 불러오기, JSON 타입으로 저장하도록 변경

    @Column(nullable = false)
    var viewCount: Long = 0,

    @Embedded
    var profile: UserProfile,

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    val likes: MutableList<Like> = mutableListOf(),

    @Column(name = "comments_count", nullable = false)
    var commentsCount: Int = 0,

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
        if (content.isEmpty()) {
            throw InvalidInputException("본문은 비어있을 수 없습니다")
        }
        token = TokenGenerator.randomCharacterWithPrefix("Post_")
    }

    fun updatePost(request: PostCommand) {
        this.title = request.title
        this.content = request.content
    }

    companion object {
        fun fixture(
            title: String = "제목",
            content: String,
            viewCount: Long = 0L,
            nickname: String = "사용자 닉네임",
            positionJob: String = "프론트엔드",
            profileImage: String = "https://e7.pngegg.com/pngimages/1000/665/png-clipart-computer-icons-profile-s-free-angle-sphere.png"
        ): Post {
            return Post(
                title = title,
                content = content,
                viewCount = viewCount,
                profile = UserProfile(
                    nickname = nickname,
                    picture = profileImage,
                    jobPosition = positionJob,
                    idProvider = "provider",
                    userId = "userId"
                )
            )
        }
    }
}
