package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private UserService userService;

	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		Review entity = new Review();
		User userEntity = new User();
		 
		entity.setText(dto.getText());
		entity.setMovie(new Movie(dto.getMovieId(), null, null, null, null, null, null));
		userEntity.setId(userService.getLoggedUser().getId());
		userEntity.setName(userService.getLoggedUser().getName());
		userEntity.setEmail(userService.getLoggedUser().getEmail());
		entity.setUser(userEntity);
		entity = repository.save(entity);
		
		return new ReviewDTO(entity, entity.getUser());
	}
	
}
