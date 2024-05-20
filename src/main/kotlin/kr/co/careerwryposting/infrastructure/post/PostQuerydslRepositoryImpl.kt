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
        // NOTE: 검색에 사용할 정보가 제대로 주어지지 않은 경우
        if (title == null && content == null) {
            throw IllegalArgumentException("검색에 사용할 정보가 제대로 주어지지 않았습니다 - 요청 본문을 확인하세요")
        }

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