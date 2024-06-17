package kr.co.careerwryposting.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.validator.constraints.URL

@Embeddable
data class UserProfile(
    @Column
    var nickname: String,

    @field:URL
    var picture: String, // FIXME :: URL 타입으로 변경하여야 함

    @Column
    var jobPosition: String?, // TODO :: 추후 Enum 타입으로 변경하여야 함

    // jobPosition 을 비동기 통신을 통해 member 서비스에서 받아와서 저장해야 함.
    // 조회할 때는 저장된 포지션을 보여주도록
    @Column(nullable = false)
    val idProvider: String, // 프로바이더 이름 (예: google, github, 네이버, 카카오)

    @Column(nullable = false)
    val userId: String // 사용자 고유 ID

) {
    companion object {
        fun fixture(
            nickname: String = "사용자 닉네임",
            picture: String = "https://e7.pngegg.com/pngimages/1000/665/png-clipart-computer-icons-profile-s-free-angle-sphere.png",
            jobPosition: String = "백엔드",
            provider: String = "provider",
            userId: String = "userId"
        ): UserProfile {
            return UserProfile(
                nickname = nickname,
                picture = picture,
                jobPosition = jobPosition,
                idProvider = provider,
                userId = userId
            )
        }
    }
}
