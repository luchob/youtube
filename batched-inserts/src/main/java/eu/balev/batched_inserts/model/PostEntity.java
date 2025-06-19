package eu.balev.batched_inserts.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
public class PostEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String post;

  @OneToMany(
      mappedBy = "post",
      cascade = CascadeType.ALL
  )
  private List<CommentEntity> comments = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public PostEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public String getPost() {
    return post;
  }

  public PostEntity setPost(String post) {
    this.post = post;
    return this;
  }

  public List<CommentEntity> getComments() {
    return comments;
  }

  public PostEntity setComments(List<CommentEntity> comments) {
    this.comments = comments;
    return this;
  }

  public PostEntity addComment(CommentEntity comment) {
    this.comments.add(comment);
    return this;
  }
}
