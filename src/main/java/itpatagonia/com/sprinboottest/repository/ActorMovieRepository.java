package itpatagonia.com.sprinboottest.repository;


import itpatagonia.com.sprinboottest.model.ActorMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorMovieRepository extends JpaRepository<ActorMovie,Long> {
}
