package kr.co.careerwryposting.domain.like

import jakarta.persistence.*
import kr.co.careerwryposting.common.util.TokenGenerator
import kr.co.careerwryposting.domain.AbstractEntity
import kr.co.careerwryposting.domain.UserProfile
import kr.co.careerwryposting.domain.comment.Comment
import kr.co.careerwryposting.domain.post.Post


@Entity
@Table(name = "`like`")
class Like(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    val post: Post? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    val comment: Comment? = null,

    @Embedded
    var profile: UserProfile,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    ) : AbstractEntity() {

    @Column(unique = true)
    val token: String = TokenGenerator.randomCharacterWithPrefix("Like_")

    init {
        require(!(post != null && comment != null)) { "Either post or comment should be assigned, not both" }
        require(!(post == null && comment == null)) { "Either post or comment should be assigned" }
        require(!(post != null && profile.hasLikedPost(post))) { "User has already liked this post" }
        require(!(comment != null && profile.hasLikedComment(comment))) { "User has already liked this comment" }
    }

    constructor(post: Post?, comment: Comment?, profile: UserProfile)
            : this(post = post, comment = comment, profile = profile, id = null)

}


fun UserProfile.hasLikedPost(post: Post): Boolean {
    return post.likes.any { it.post == post }
}

fun UserProfile.hasLikedComment(comment: Comment): Boolean {
    return comment.likes.any { it.comment == comment }
}


