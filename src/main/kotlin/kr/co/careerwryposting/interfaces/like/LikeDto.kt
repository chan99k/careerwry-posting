package kr.co.careerwryposting.interfaces.like

class LikeDto {
    data class LikeEvent(
        val postToken: String,
        val createdBy: String,
    )
}