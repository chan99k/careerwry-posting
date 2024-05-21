package kr.co.careerwryposting.domain.post

import jakarta.persistence.*
import kr.co.careerwryposting.common.exeption.InvalidInputException
import kr.co.careerwryposting.common.util.TokenGenerator
import kr.co.careerwryposting.domain.AbstractEntity
import kr.co.careerwryposting.interfaces.post.PostDto

@Embeddable
data class UserProfile(
    var nickName: String,
    var positionJob: String,
    var profileImage: String
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
            nickName: String = "사용자 닉네임",
            positionJob: String = "프론트 엔드",
            profileImage: String = "https://e7.pngegg.com/pngimages/1000/665/png-clipart-computer-icons-profile-s-free-angle-sphere.png",
        ): Post {
            return Post(
                title = title,
                content = content,
                UserProfile(
                    nickName = nickName,
                    positionJob = positionJob,
                    profileImage = profileImage,
                )
            )
        }
    }
}

/**
 * Note
 *     {
 *       nickName: '개개발발자자',
 *       positionJob: '어딘가의 프론트엔드 개발자', -> 이거는 멤버 서비스에 api 요청해서 받아와야 함 : 리액티브
 *       profileImg: 'https://e7.pngegg.com/pngimages/1000/665/png-clipart-computer-icons-profile-s-free-angle-sphere.png',
 *       -> 사용자 정보 -> fixture 를 만들 때 security context 에서 token 정보를 추출하여 만들기
 *       date: '5월 3일', // created or modified date (형태는 YYYY.MM.DD가 제일 나을듯?)
 *       title: '우와아앙',
 *       contents: '이것이 첫글이지롱',
 *     },
 */