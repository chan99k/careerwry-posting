package kr.co.careerwryposting.domain.comment

import jakarta.persistence.*
import kr.co.careerwryposting.common.exeption.InvalidInputException
import kr.co.careerwryposting.common.util.TokenGenerator
import kr.co.careerwryposting.domain.AbstractEntity
import kr.co.careerwryposting.domain.UserProfile
import kr.co.careerwryposting.domain.post.Post

@Entity
class Comment(
    var content: String,

    @Embedded
    var profile: UserProfile,

//    @OneToMany(mappedBy = "comment", cascade = [CascadeType.ALL], orphanRemoval = true)
//    val likes: MutableList<Like> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    var post: Post? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long? = null
) : AbstractEntity() {

    @Column(unique = true)
    val token: String

    init {
        if (content.isBlank()) {
            throw InvalidInputException("본문은 비어있을 수 없습니다")
        }
        token = TokenGenerator.randomCharacterWithPrefix("Comment_")
    }

    fun updateComment(newContent: String) {
        this.content = newContent
    }

}

fun Post.addComment(comment: Comment) {
    this.comments.add(comment)
}

fun Post.deleteComment(comment: Comment) {
    this.comments.remove(comment)
}
