package com.goldenraspberryawards.repository;

import com.goldenraspberryawards.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoviesRepository extends JpaRepository<Movie, Long> {

    @Query(value = """
            SELECT * 
            FROM TB_MOVIES TB 
            WHERE TB.PRODUCERS IN (
                    SELECT M.PRODUCERS  
                    FROM TB_MOVIES M 
                    WHERE M.WINNER = 'yes' 
                    GROUP BY M.PRODUCERS 
                    HAVING COUNT(M.PRODUCERS) >1)
            """, nativeQuery = true)
    List<Movie>  getListMobvies();

}
