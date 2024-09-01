package kr.co.careerwryposting.domain.like

interface LikeService {
    fun createLike(likeCommand: LikeCommand.CreateCommand)
    fun deleteLike(likeCommand: LikeCommand.DeleteCommand)
    fun countLike(postToken: String): Long
}