package kr.co.careerwryposting.domain.like

import jakarta.persistence.*
import kr.co.careerwryposting.common.util.TokenGenerator
import kr.co.careerwryposting.domain.AbstractEntity
import kr.co.careerwryposting.domain.post.Post
import org.springframework.security.core.context.SecurityContextHolder


@Entity
@Table(name = "like")
class Like(
    post: Post,
    createdBy: String,
) : AbstractEntity(createdBy = createdBy) {

    @Column(unique = true)
    val token: String

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    var post: Post = post
        private set

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long? = null

    init {
        if (SecurityContextHolder.getContext().authentication == null) {
//          throw IllegalStateException("로그인이 필요합니다")
            println("로그인 하지 않았을 경우의 예외 처리 필요함")
        }
        token = TokenGenerator.randomCharacterWithPrefix("Like_")
    }

    companion object {
        fun fixture(post: Post, createdBy: String): Like {
            return Like(
                post = post,
                createdBy = createdBy,
            )
        }
    }
}
