package kr.co.careerwryposting.domain.post

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kr.co.careerwryposting.interfaces.post.PostDto
import org.junit.jupiter.api.Disabled
import org.springframework.boot.test.context.SpringBootTest
@Disabled
@SpringBootTest
class PostServiceTest(
    private val postService: PostService,
    private val postReader: PostReader,
    private val postWriter: PostWriter,
) : BehaviorSpec({
    given("게시글 생성 시") {
        When("게시글 입력이 정상적으로 들어오면") {
            val postInfo = postService.savePost(
                PostCommand.of(
                    PostDto.PostRequest(
                        title = "제목",
                        content = "내용",
                        token = null,
                    )
                )
            )
            then("게시글이 정상적으로 생성됨을 확인한다.") {
                postInfo.id shouldBeGreaterThan 0L
                val post = postReader.getPost(postInfo.token)
                post shouldNotBe null
                post?.title shouldBe "제목"
                post?.content shouldBe "내용"
            }
        }
    }
})