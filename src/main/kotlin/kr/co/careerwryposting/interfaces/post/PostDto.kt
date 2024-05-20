package kr.co.careerwryposting.interfaces.post

import com.fasterxml.jackson.annotation.JsonProperty
import kr.co.careerwryposting.domain.post.Post

class PostDto {
    data class PostRequest(
        @JsonProperty(value = "title") val title: String,
        @JsonProperty(value = "contents") val content: String,
        @JsonProperty(value = "token") val token: String?,
    )

    data class PostUpdateRequest(
        @JsonProperty(value = "title") val title: String,
        @JsonProperty(value = "contents") val content: String,
        @JsonProperty(value = "token") val token: String,
    )

    data class PostSearchRequest(
        @JsonProperty(value = "title") val title: String?,
        @JsonProperty(value = "contents") val content: String?,
    )

    data class PostResponse(
        @JsonProperty(value = "title") val title: String,
        @JsonProperty(value = "contents") val content: String,
        @JsonProperty(value = "token") val token: String,
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