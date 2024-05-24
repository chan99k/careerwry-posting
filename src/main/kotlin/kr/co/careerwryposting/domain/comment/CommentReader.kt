package kr.co.careerwryposting.domain.comment

interface CommentReader {
    fun getComment(token: String): Comment?
}