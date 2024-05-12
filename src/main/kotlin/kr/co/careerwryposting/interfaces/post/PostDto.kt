package kr.co.careerwryposting.interfaces.post

import kr.co.careerwryposting.domain.post.Post

class PostDto {
    data class PostRequest(
        val title: String,
        val content: String,
        val token: String?,
    )

    data class PostUpdateRequest(
        val token: String,
        val title: String,
        val content: String,
    )

    data class PostSearchRequest(
        val title: String?,
        val content: String?,
    )

    data class PostResponse(
        val title: String,
        val content: String,
        val token: String,
    ) {
        companion object {
            fun of(post: Post): PostResponse {
                return PostResponse(
                    title = post.title,
                    content = post.content,
                    token = post.token,
                )
            }
        }
    }
}