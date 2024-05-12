package kr.co.careerwryposting.domain.post

import jakarta.persistence.*
import kr.co.careerwryposting.common.util.TokenGenerator
import kr.co.careerwryposting.domain.AbstractEntity
import kr.co.careerwryposting.interfaces.post.PostDto

@Entity
class Post(
    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var content: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long? = null

) : AbstractEntity() {

    @Column(unique = true)
    val token: String

    init {
        if (title.isBlank()) {
            throw IllegalArgumentException("제목은 비어있을 수 없습니다")
        }
        if (content.isBlank()) {
            throw IllegalArgumentException("본문은 비어있을 수 없습니다")
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
        ): Post {
            return Post(
                title = title,
                content = content,
            )
        }
    }
}

/**
 * Note
 * 제목, 본문, 첨부 이미지(5개까지), 토큰(식별자)
 */