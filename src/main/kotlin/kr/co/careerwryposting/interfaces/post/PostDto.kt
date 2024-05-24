package kr.co.careerwryposting.interfaces.post

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Size
import kr.co.careerwryposting.domain.comment.CommentInfo
import kr.co.careerwryposting.domain.post.Post
import kr.co.careerwryposting.domain.post.PostInfo
import kr.co.careerwryposting.interfaces.comment.CommentDto
import java.time.LocalDateTime

class PostDto {
    data class PostRequest(
        @JsonProperty(value = "title") @field:Size(min = 1, max = 40) val title: String,
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
        @JsonProperty(value = "nickname") val nickName: String,
        @JsonProperty(value = "positionJob") val positionJob: String?,
        @JsonProperty(value = "profileImg") val profileImage: String?,
        @JsonProperty(value = "comments") val comments: List<CommentDto.CommentResponse>?,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonProperty(value = "date") val createdAt: LocalDateTime,
        @JsonProperty(value = "token") val token: String,
    ) {
        companion object {
            fun of(post: Post): PostResponse {
                return PostResponse(
                    title = post.title,
                    content = post.content,
                    nickName = post.profile.nickname!!,
                    positionJob = post.profile.jobPosition,
                    profileImage = post.profile.picture,
                    comments = null,
                    createdAt = post.createdDate!!,
                    token = post.token,
                )
            }

            fun of(info: PostInfo): PostResponse {
                return PostResponse(
                    title = info.title,
                    content = info.content,
                    nickName = info.nickName,
                    positionJob = info.positionJob,
                    profileImage = info.profileImage,
                    comments = null,
                    createdAt = info.createdAt,
                    token = info.token,
                )
            }

            fun detail(info: PostInfo): PostResponse {
                return PostResponse(
                    title = info.title,
                    content = info.content,
                    nickName = info.nickName,
                    positionJob = info.positionJob,
                    profileImage = info.profileImage,
                    comments = info.comments
                        .map { CommentInfo.of(it) }
                        .map { CommentDto.CommentResponse.of(it) },
                    createdAt = info.createdAt,
                    token = info.token,
                )
            }

        }
    }
}