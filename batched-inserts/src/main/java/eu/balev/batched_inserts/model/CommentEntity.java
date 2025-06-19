package eu.balev.batched_inserts.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comment")
public class CommentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_id_seq_generator")
  @SequenceGenerator(
      name = "comments_id_seq_generator",
      sequenceName = "comment_id_seq",
      initialValue = 1,
      allocationSize = 100
  )
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String comment;

  @ManyToOne
  private PostEntity post;

  public Long getId() {
    return id;
  }

  public CommentEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public String getComment() {
    return comment;
  }

  public CommentEntity setComment(String comment) {
    this.comment = comment;
    return this;
  }

  public PostEntity getPost() {
    return post;
  }

  public CommentEntity setPost(PostEntity post) {
    this.post = post;
    return this;
  }
}
