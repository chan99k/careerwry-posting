package kr.co.careerwryposting.interfaces.post

import kr.co.careerwryposting.application.post.PostFacade
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/postings")
class PostApiController(
    private val postFacade: PostFacade,
) {

    @GetMapping
    fun getAllPostings(): List<PostDto.PostResponse> {
        return postFacade.getAllPostings()
    }

    @PostMapping
    fun savePost(@RequestBody request: PostDto.PostRequest) {
        postFacade.savePost(request)
    }

    @GetMapping("/{token}")
    fun getPost(@PathVariable token: String): PostDto.PostResponse {
        return postFacade.getPost(token)
    }

    @GetMapping("/search")
    fun findPosts(@RequestBody request: PostDto.PostSearchRequest): List<PostDto.PostResponse> {
        return postFacade.findPosts(request)
    }

    @PutMapping
    fun updatePost(@RequestBody request: PostDto.PostUpdateRequest) {
        return postFacade.updatePost(request)
    }

    @DeleteMapping("/{token}")
    fun deletePost(@PathVariable token: String) {
        return postFacade.deletePost(token)
    }


}
