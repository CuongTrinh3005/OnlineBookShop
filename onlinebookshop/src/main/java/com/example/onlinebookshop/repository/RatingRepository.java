package com.example.onlinebookshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.onlinebookshop.model.Rating;
import com.example.onlinebookshop.model.Rating.RatingId;

@Repository
public interface RatingRepository extends JpaRepository<Rating, RatingId> {
	List<Rating> findByRatingIdUsername(String username);
	List<Rating> findByRatingIdBookId(Long bookId);
	Boolean existsByRatingId(RatingId ratingId);
}