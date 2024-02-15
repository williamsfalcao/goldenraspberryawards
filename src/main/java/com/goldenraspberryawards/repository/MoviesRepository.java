package com.goldenraspberryawards.repository;

import com.goldenraspberryawards.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoviesRepository extends JpaRepository<Movie, Long> {

    @Query(value = """
            SELECT * FROM TB_MOVIES AS TB WHERE TB.WINNER = 'yes' ORDER BY TB.YEAR_AWARD ASC
            """, nativeQuery = true)
    List<Movie>  getWinners();

}
