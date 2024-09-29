package kr.co.careerwryposting.domain.tag

import jakarta.persistence.*
import kr.co.careerwryposting.common.util.TokenGenerator
import kr.co.careerwryposting.domain.AbstractEntity
import kr.co.careerwryposting.domain.post.Post

@Entity
@Table(name = "tag")
class Tag(
    @Column(nullable = false, length = 15)
    var hashTag: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    val post: Post,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long? = null
) : AbstractEntity() {
    @Column(unique = true)
    val token: String

    init {
        if(hashTag.isBlank()) {
            throw IllegalArgumentException("태그 이름은 비어있을 수 없습니다")
        }
        if(hashTag.length > 15) {
            throw IllegalArgumentException("태그 이름은 10자 이하여야 합니다")
        }
        token = TokenGenerator.randomCharacterWithPrefix("Tag_")
    }
}