package kr.co.careerwryposting.domain.file

import jakarta.persistence.*
import kr.co.careerwryposting.common.util.TokenGenerator
import kr.co.careerwryposting.domain.AbstractEntity
import kr.co.careerwryposting.domain.post.Post
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.multipart.MultipartFile

@Entity
@Table
class File(
    @Column(nullable = false)
    val s3Url: String,

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
            println("로그인 하지 않았을 경우의 예외 처리 필요함")
        }
        token = TokenGenerator.randomCharacterWithPrefix("PostImg")
    }

    companion object {
        fun fixture(post: Post, url: String, file: MultipartFile): File {
            return File(
                post = post,
                s3Url = url,
                contentType = file.contentType ?: "unknown",
                fileSize = file.size,
            )
        }
    }
}