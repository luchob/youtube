package eu.balev.batched_inserts.web;

import eu.balev.batched_inserts.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping("/test")
  public void doInsert() {
    postService.doInsert();
  }
}
