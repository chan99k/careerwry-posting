package kr.co.careerwryposting.domain.like

import kr.co.careerwryposting.interfaces.like.LikeDto

class LikeCommand {

    data class CreateCommand(
        val postToken: String,
        val createdBy: String,
    ) {
        companion object {
            fun of(request: LikeDto.LikeEvent): CreateCommand {
                return CreateCommand(
                    postToken = request.postToken,
                    createdBy = request.createdBy,
                )
            }
        }
    }

    data class DeleteCommand(
        val postToken: String,
        val createdBy: String,
    ) {
        companion object {
            fun of(request: LikeDto.LikeEvent): DeleteCommand {
                return DeleteCommand(
                    postToken = request.postToken,
                    createdBy = request.createdBy,
                )
            }
        }
    }
}

