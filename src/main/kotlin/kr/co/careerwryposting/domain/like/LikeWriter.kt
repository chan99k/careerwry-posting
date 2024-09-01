package kr.co.careerwryposting.domain.like


interface LikeWriter {
    fun save(command: LikeCommand.CreateCommand)
    fun delete(command: LikeCommand.DeleteCommand)
}
