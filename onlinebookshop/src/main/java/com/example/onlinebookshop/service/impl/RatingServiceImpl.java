package com.example.onlinebookshop.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.onlinebookshop.exception.ResourceNotFoundException;
import com.example.onlinebookshop.model.Rating;
import com.example.onlinebookshop.model.Rating.RatingId;
import com.example.onlinebookshop.repository.RatingRepository;
import com.example.onlinebookshop.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
	@Autowired
	RatingRepository ratingRepository;

	@Override
	public List<Rating> getAllRatings() {
		return ratingRepository.findAll();
	}

	@Override
	public Optional<Rating> getByRatingId(RatingId id) {
		Rating rating = ratingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("rating id " + id + " not found"));
		return Optional.of(rating);
	}

	@Override
	public Rating saveRating(Rating rating) {
		return ratingRepository.save(rating);
	}

	@Override
	public Rating updateRating(Rating rating) {
		Optional<Rating> ratingOpt = getByRatingId(rating.getRatingId());
		Rating existedRating = ratingOpt.get();

		existedRating.setDateRating(new Date());
		existedRating.setLevelRating(rating.getLevelRating());
		return ratingRepository.save(existedRating);
	}

	@Override
	public List<Rating> getAllRatingByUserName(String username) {
		return ratingRepository.findByRatingIdUsername(username);
	}

	@Override
	public List<Rating> getAllRatingByBookId(Long bookId) {
		return ratingRepository.findByRatingIdBookId(bookId);
	}
}