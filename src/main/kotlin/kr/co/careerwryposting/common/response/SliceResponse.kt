package kr.co.careerwryposting.common.response

import org.springframework.data.domain.Slice

data class SliceResponse<T>(
    val content: List<T>,
    val sort: SortResponse,
    val currentPage: Int,
    val size: Int,
    val first: Boolean,
    val last: Boolean
) {
    companion object {
        fun <T> fromSlice(slice: Slice<T>): SliceResponse<T> {
            return SliceResponse(
                content = slice.content,
                sort = SortResponse(slice.sort),
                currentPage = slice.number + 1,
                size = slice.size,
                first = slice.isFirst,
                last = slice.isLast
            )
        }
    }

    fun toCommonResponse(): CommonResponse<SliceResponse<T>> {
        return CommonResponse.success(this)
    }
}
