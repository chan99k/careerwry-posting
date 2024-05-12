package kr.co.careerwryposting.infrastructure.post

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.careerwryposting.domain.post.Post
import kr.co.careerwryposting.domain.post.QPost.post
import kr.co.careerwryposting.interfaces.post.PostDto

class PostQuerydslRepositoryImpl(
    private val querydsl: JPAQueryFactory,
) : PostQuerydslRepository {
    override fun findPosts(request: PostDto.PostSearchRequest): List<Post> {
        val title = request.title
        val content = request.content
        val booleanBuilder = BooleanBuilder()

        title?.let { booleanBuilder.or(post.title.likeIgnoreCase("%$title%")) }
        content?.let { booleanBuilder.or(post.content.likeIgnoreCase("%$content%")) }

        return querydsl
            .select(post)
            .from(post)
            .where(booleanBuilder)
            .fetch();
    }
}