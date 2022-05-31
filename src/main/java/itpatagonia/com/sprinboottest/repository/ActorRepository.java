package itpatagonia.com.sprinboottest.repository;

import itpatagonia.com.sprinboottest.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor,Long> {

}
