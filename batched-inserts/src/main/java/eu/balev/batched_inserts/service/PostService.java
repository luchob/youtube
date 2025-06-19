package eu.balev.batched_inserts.service;

import eu.balev.batched_inserts.model.CommentEntity;
import eu.balev.batched_inserts.model.PostEntity;
import eu.balev.batched_inserts.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.util.Arrays.spliterator;

@Service
public class PostService {

  private final PostRepository postRepository;

  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public void doInsert() {

    PostEntity post = new PostEntity().setPost("Posting on " + LocalDateTime.now());

    CommentEntity comment1 = new CommentEntity().setComment("Comment 1").setPost(post);
    CommentEntity comment2 = new CommentEntity().setComment("Comment 2").setPost(post);
    CommentEntity comment3 = new CommentEntity().setComment("Comment 3").setPost(post);
    CommentEntity comment4 = new CommentEntity().setComment("Comment 4").setPost(post);
    CommentEntity comment5 = new CommentEntity().setComment("Comment 5").setPost(post);
    CommentEntity comment6 = new CommentEntity().setComment("Comment 6").setPost(post);
    CommentEntity comment7 = new CommentEntity().setComment("Comment 7").setPost(post);
    CommentEntity comment8 = new CommentEntity().setComment("Comment 8").setPost(post);
    CommentEntity comment9 = new CommentEntity().setComment("Comment 9").setPost(post);
    CommentEntity comment10 = new CommentEntity().setComment("Comment 10").setPost(post);
    CommentEntity comment11 = new CommentEntity().setComment("Comment 11").setPost(post);

    post.addComment(comment1);
    post.addComment(comment2);
    post.addComment(comment3);
    post.addComment(comment4);
    post.addComment(comment5);
    post.addComment(comment6);
    post.addComment(comment7);
    post.addComment(comment8);
    post.addComment(comment9);
    post.addComment(comment10);
    post.addComment(comment11);

    postRepository.save(post);



    // 1011

    // 1000 -> 8
    // 10   -> 2
    // 1    -> 1
  }
}

