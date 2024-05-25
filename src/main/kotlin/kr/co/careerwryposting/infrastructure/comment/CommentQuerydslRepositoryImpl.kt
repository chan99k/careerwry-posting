package kr.co.careerwryposting.infrastructure.comment

import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.careerwryposting.domain.comment.Comment
import kr.co.careerwryposting.domain.comment.QComment.comment
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl

class CommentQuerydslRepositoryImpl(
    private val querydsl: JPAQueryFactory,
) : CommentQuerydslRepository {
    override fun getCommentsByPostId(postId: Long, pageable: Pageable): Slice<Comment> {
        val comments = querydsl.selectFrom(comment)
            .where(comment.post.id.eq(postId))
            .orderBy(comment.createdDate.desc())
            .offset(pageable.offset)
            .limit((pageable.pageSize + 1).toLong())
            .fetch()

        val hasNext = if (comments.size > pageable.pageSize) {
            comments.removeAt(pageable.pageSize) // hasNext 판별용으로 하나 더 요청했던거 지우기
            true
        } else {
            false
        }

        return SliceImpl(comments, pageable, hasNext)
    }
}