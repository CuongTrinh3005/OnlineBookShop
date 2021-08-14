package com.example.onlinebookshop.service;

import java.util.List;
import java.util.Optional;

import com.example.onlinebookshop.model.Rating;
import com.example.onlinebookshop.model.Rating.RatingId;

public interface RatingService {
	List<Rating> getAllRatings();
	Optional<Rating> getByRatingId(RatingId id);
	Rating saveRating(Rating rating);
	Rating updateRating(Rating rating);
	List<Rating> getAllRatingByUserName(String username);
	List<Rating> getAllRatingByBookId(Long bookId);
}