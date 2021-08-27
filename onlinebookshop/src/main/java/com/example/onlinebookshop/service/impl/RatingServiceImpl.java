package com.example.onlinebookshop.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.onlinebookshop.model.Book;
import com.example.onlinebookshop.model.dto.RatingDTO;
import com.example.onlinebookshop.repository.BookRepository;
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

	@Autowired
	BookRepository bookRepository;

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
		existedRating.setComment(rating.getComment());

		return ratingRepository.save(existedRating);
	}

	@Override
	public List<Rating> getAllRatingByUserName(String username) {
		return ratingRepository.findByRatingIdUsername(username);
	}

	@Override
	public Boolean checkRatingExist(RatingId ratingId) {
		return ratingRepository.existsByRatingId(ratingId);
	}

	@Override
	public List<Rating> getAllRatingByBookId(Long bookId) {
		return ratingRepository.findByRatingIdBookId(bookId);
	}

	@Override
	public RatingDTO convertToDTO(Rating rating) {
		Long bookId = rating.getRatingId().getBookId();
		Book book = bookRepository.getById(bookId);
		RatingDTO dto = new RatingDTO(rating.getRatingId().getUsername(), bookId, book.getBookName(),book.getPhoto()
				,rating.getDateRating(),rating.getLevelRating(), rating.getComment());
		return dto;
	}
}