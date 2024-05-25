package kr.co.careerwryposting.interfaces.comment

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Size
import kr.co.careerwryposting.domain.comment.CommentInfo
import java.time.LocalDateTime

class CommentDto {
    data class CommentCreateRequest(
        @JsonProperty(value = "contents") @field:Size(min = 1, max = 300) val content: String,
    )

    data class CommentUpdateRequest(
        @JsonProperty(value = "contents") @field:Size(min = 1, max = 300) val content: String,
    )

    data class CommentResponse(
        @JsonProperty(value = "contents") val content: String,
        @JsonProperty(value = "nickname") val nickname: String,
        @JsonProperty(value = "profileImg") val picture: String?,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonProperty(value = "date") val createdDate: LocalDateTime,
        @JsonProperty(value = "token") val token: String,
    ) {
        companion object {
            fun of(info: CommentInfo): CommentResponse {
                return CommentResponse(
                    content = info.content,
                    nickname = info.nickName,
                    picture = info.picture,
                    createdDate = info.createdDate,
                    token = info.token,
                )
            }
        }
    }

    data class CommentTinyResponse(
        @JsonProperty(value = "date") val createdDate: LocalDateTime,
        @JsonProperty(value = "token") val token: String,
    ) {
        companion object {

            fun of(info: CommentInfo): CommentTinyResponse {
                return CommentTinyResponse(
                    createdDate = info.createdDate,
                    token = info.token,
                )
            }
        }
    }
}


