package eu.balev.batched_inserts.repository;

import eu.balev.batched_inserts.model.CommentEntity;
import eu.balev.batched_inserts.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository  extends JpaRepository<PostEntity, Long> {
}
