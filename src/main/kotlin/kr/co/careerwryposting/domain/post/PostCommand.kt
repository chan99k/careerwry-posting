package kr.co.careerwryposting.domain.post

import kr.co.careerwryposting.interfaces.post.PostDto

data class PostCommand(
    val title: String,
    val content: String,
) {
    companion object {

        fun of(request: PostDto.PostRequest): PostCommand {
            return PostCommand(
                title = request.title,
                content = request.content,
            )
        }

        fun ofUpdate(request: PostDto.PostUpdateRequest): PostCommand {
            return PostCommand(
                title = request.title,
                content = request.content
            )
        }
    }
}
