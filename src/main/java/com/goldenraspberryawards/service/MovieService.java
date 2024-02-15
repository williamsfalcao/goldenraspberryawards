package com.goldenraspberryawards.service;

import com.goldenraspberryawards.dto.AwardDTO;
import com.goldenraspberryawards.dto.MovieDTO;
import com.goldenraspberryawards.dto.ResponseDTO;
import com.goldenraspberryawards.model.Movie;
import com.goldenraspberryawards.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {

    @Autowired
    private MoviesRepository moviesRepository;

    public List<Movie> getListMovies() {
        return moviesRepository.findAll();
    }
    public Optional<Movie> getMovie(Long id) {
        return moviesRepository.findById(id);
    }
    public void save(Movie movie) {
        moviesRepository.save(movie);
    }

    public void delete(Long id) {
        moviesRepository.deleteById(id);
    }

    public void update(Long id, MovieDTO movieDTO) {

        Optional<Movie> optMovie = moviesRepository.findById(id);

        if(optMovie.isPresent()){
            Movie movie= optMovie.get();

            if (Objects.nonNull(movieDTO.getYear())) {
                movie.setYear(movieDTO.getYear());
            }
            if (Objects.nonNull(movieDTO.getProducers())) {
                movie.setProducers(movieDTO.getProducers());
            }
            if (Objects.nonNull(movieDTO.getTitle())) {
                movie.setTitle(movieDTO.getTitle());
            }
            if (Objects.nonNull(movieDTO.getStudios())) {
                movie.setStudios(movieDTO.getStudios());
            }
            if (Objects.nonNull(movieDTO.getWinner())) {
                movie.setWinner(movieDTO.getWinner());
            }
            moviesRepository.save(movie);
        }
    }

    public ResponseDTO listProducers(){
        List<Movie> listMovies = moviesRepository.getWinners();
        List<AwardDTO> listAwards = buildListProducers(listMovies);
        return buildResponse(listAwards);
    }
    
    public ResponseDTO listStudios(){
        List<Movie> listMovies = moviesRepository.getWinners();
        List<AwardDTO> listAwards = buildListStudios(listMovies);
        return buildResponse(listAwards);
    }

    private List<AwardDTO> buildListStudios(List<Movie> listMoviesAward){
        Map<String, AwardDTO> map = new HashMap<>();

        listMoviesAward.forEach(item ->{
            AwardDTO awardDTO = null;
            
            String[] studio = item.getStudios().replace(" and ", ",").split(",");
            
            for (String pr : studio) {
            	if(map.containsKey(pr)){
                	awardDTO = map.get(pr);
                	if(null != awardDTO.getFollowingWin()){
                		awardDTO.setPreviousWin(awardDTO.getFollowingWin());
                		awardDTO.setFollowingWin(item.getYear());
                		awardDTO.setInterval(awardDTO.getFollowingWin() - awardDTO.getPreviousWin());
                        map.replace(pr,awardDTO);
                	} else {
                		awardDTO.setFollowingWin(item.getYear());
                		awardDTO.setInterval(awardDTO.getFollowingWin() - awardDTO.getPreviousWin());
                        map.replace(pr,awardDTO);
                	}
                	
                } else {
                    awardDTO = new AwardDTO();
                    awardDTO.setProducer(pr);
                    awardDTO.setPreviousWin(item.getYear());
                    map.put(pr, awardDTO);
                }            

            }

            
        });
        return new ArrayList<>(map.values());
    }
    
    private List<AwardDTO> buildListProducers(List<Movie> listMoviesAward){
        Map<String, AwardDTO> map = new HashMap<>();

        listMoviesAward.forEach(item ->{
            AwardDTO awardDTO = null;
            
            String[] producer = item.getProducers().replace(" and ", ",").split(",");
            
            for (String pr : producer) {
            	if(map.containsKey(pr)){
            		 	awardDTO = map.get(pr);
                	if(null != awardDTO.getFollowingWin()){
                		awardDTO.setPreviousWin(awardDTO.getFollowingWin());
                		awardDTO.setFollowingWin(item.getYear());
                		awardDTO.setInterval(awardDTO.getFollowingWin() - awardDTO.getPreviousWin());
                        map.replace(pr,awardDTO);
                	} else {
                		awardDTO.setFollowingWin(item.getYear());
                		awardDTO.setInterval(awardDTO.getFollowingWin() - awardDTO.getPreviousWin());
                        map.replace(pr,awardDTO);
                	}
                	
                } else {
                    awardDTO = new AwardDTO();
                    awardDTO.setProducer(pr);
                    awardDTO.setPreviousWin(item.getYear());
                    map.put(pr, awardDTO);
                }            

			}

            
        });
        return new ArrayList<>(map.values());
    }

    private ResponseDTO buildResponse(List<AwardDTO> listAward) {

        ResponseDTO response = ResponseDTO.builder()
                .min(new ArrayList<>())
                .max(new ArrayList<>())
                .build();

        for (AwardDTO item : listAward) {
        	
        	if(null != item.getInterval()) {
        		if (response.getMin().isEmpty()) {
                    response.getMin().add(item);
                } else {
                	
                    if (response.getMin().get(0).getInterval() > item.getInterval()) {
                    	response.setMin(new ArrayList<>());
                        response.getMin().add(item);
                    } else if (response.getMin().get(0).getInterval().equals(item.getInterval())) {
                        response.getMin().add(item);
                    }
                }

                if (response.getMax().isEmpty()) {
                    response.getMax().add(item);
                } else {
                    if (response.getMax().get(0).getInterval() < item.getInterval()) {
                        response.setMax(new ArrayList<>());
                        response.getMax().add(item);
                    } else if (response.getMax().get(0).getInterval().equals(item.getInterval())) {
                        response.getMax().add(item);
                    }
                }
        	}
        	
            
        }
        return response;
    }
}
