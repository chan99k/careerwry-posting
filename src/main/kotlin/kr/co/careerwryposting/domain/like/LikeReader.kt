package kr.co.careerwryposting.domain.like


interface LikeReader {
    fun countByPostToken(postToken: String): Long
}