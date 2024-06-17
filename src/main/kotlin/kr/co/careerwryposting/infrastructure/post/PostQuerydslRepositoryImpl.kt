package kr.co.careerwryposting.infrastructure.post

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.PathBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.careerwryposting.common.util.withPageable
import kr.co.careerwryposting.domain.comment.QComment.comment
import kr.co.careerwryposting.domain.post.Post
import kr.co.careerwryposting.domain.post.QPost.post
import kr.co.careerwryposting.interfaces.post.PostDto
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl

class PostQuerydslRepositoryImpl(
    private val querydsl: JPAQueryFactory
) : PostQuerydslRepository {
    override fun findPosts(request: PostDto.PostSearchRequest): List<Post> {
        val title = request.title
        val content = request.content

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
            .fetch()
    }

    override fun findAllByQuerydsl(pageable: Pageable): Slice<Post> {
        // 정렬 조건 추가
        val orderSpecifiers = getOrderSpecifiers(pageable.sort)

        val content = querydsl.select(post)
            .from(post)
            .withPageable(pageable)
            .orderBy(*orderSpecifiers.toTypedArray())
            .limit((pageable.pageSize + 1).toLong())
            .fetch()

        val hasNext = if (content.size > pageable.pageSize) {
            content.removeAt(pageable.pageSize) // hasNext 판별용으로 하나 더 요청했던거 지우기
            true
        } else {
            false
        }

        return SliceImpl(content, pageable, hasNext)
    }

    override fun getPostDetails(token: String, pageable: Pageable): Post? {
        // 1. Post 조회
        val post = querydsl.selectFrom(post)
            .leftJoin(post.comments, comment).fetchJoin()
            .where(post.token.eq(token))
            .fetchOne() ?: return null

        // 2. Comment 페이징 조회
        val comments = querydsl.selectFrom(comment)
            .where(comment.post.eq(post))
            .limit(pageable.pageSize.toLong())
            .offset(pageable.offset)
            .fetch()
        TODO()
    }

    private fun getOrderSpecifiers(sort: org.springframework.data.domain.Sort): List<OrderSpecifier<*>> {
        return sort.map { sortOrder ->
            val order: Order = if (sortOrder.isAscending) Order.ASC else Order.DESC
            val pathBuilder = PathBuilder(post.type, post.metadata.name)
            OrderSpecifier(order, pathBuilder.getComparable(sortOrder.property, Comparable::class.java))
        }.toList()
    }
}

/**
 * Page 반환시,
 *        val countQuery = querydsl.select(post.count())
 *             .from(post)
 *             .withPageable(pageable)
 *         return PageableExecutionUtils.getPage(content, pageable) { countQuery.fetchOne()!! }
 */
