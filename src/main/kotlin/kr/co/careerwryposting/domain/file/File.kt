package kr.co.careerwryposting.domain.file

import jakarta.persistence.*
import kr.co.careerwryposting.common.util.TokenGenerator
import kr.co.careerwryposting.domain.AbstractEntity
import kr.co.careerwryposting.domain.post.Post
import org.springframework.security.core.context.SecurityContextHolder

@Entity
@Table
class File(
    @Column(nullable = true)
    val name: String,

    @Lob
    @Column(nullable = false)
    val data: ByteArray,

    @Column(nullable = false)
    val contentType: String,

    @Column(nullable = false)
    val fileSize: Long,

    post: Post,

    ) : AbstractEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    var post: Post = post
        private set

    @Column(unique = true)
    val token: String

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long? = null

    init {
        if (SecurityContextHolder.getContext().authentication == null) {
//          throw IllegalStateException("로그인이 필요합니다")
            println("로그인 하지 않았을 경우의 예외 처리 필요함")
        }
        token = TokenGenerator.randomCharacterWithPrefix("PostImage_")
    }

    companion object {
        fun fixture(post: Post, name: String, contentType: String, fileSize: Long, data: ByteArray): File {
            return File(
                post = post,
                name = name,
                data = data,
                contentType = contentType,
                fileSize = fileSize,
            )
        }
    }
}