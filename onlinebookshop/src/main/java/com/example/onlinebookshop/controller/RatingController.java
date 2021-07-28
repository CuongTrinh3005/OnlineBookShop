package com.example.onlinebookshop.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlinebookshop.model.Rating;
import com.example.onlinebookshop.model.Rating.RatingId;
import com.example.onlinebookshop.service.impl.RatingService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class RatingController {
	@Autowired
	RatingService ratingService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("ratings")
	public List<Rating> getAllRatings(){
		return ratingService.getAllRatings();
	}

	@GetMapping("ratings/id")
	public Optional<Rating> getRatingById(@RequestParam String username, @RequestParam Long bookId){
		RatingId ratingId = new RatingId(username, bookId);
		return ratingService.getByRatingId(ratingId);
	}
	
	@GetMapping("ratings/user")
	public List<Rating> getUserRatings(@RequestParam String username){
		return ratingService.getAllRatingByUserName(username);
	}
	
//	@GetMapping("ratings/books/{id}")
//	public List<Rating> getBookRatings(@PathVariable Long id){
//		return ratingService.getAllRatingByBookId(id);
//	}
	
	@PostMapping("ratings")
	public Rating saveRating(@Valid @RequestBody Rating rating){
		return ratingService.saveRating(rating);
	}
	
	@PutMapping("ratings")
	public ResponseEntity<Rating> updateRating(@Valid @RequestBody Rating rating) {
		return new ResponseEntity<Rating>(ratingService.updateRating(rating), HttpStatus.OK);
	}
}