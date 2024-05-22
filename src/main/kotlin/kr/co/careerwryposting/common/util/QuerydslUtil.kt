package kr.co.careerwryposting.common.util

import com.querydsl.jpa.impl.JPAQuery
import org.springframework.data.domain.Pageable

fun <T> JPAQuery<T>.withPageable(pageable: Pageable): JPAQuery<T> {
    return this.limit(pageable.pageSize.toLong()) // 여기 이렇게 적용해놓고 슬라이스에서 limit 조건 다시 주면 덮어 씌워지네?
        .offset(pageable.offset)
}
