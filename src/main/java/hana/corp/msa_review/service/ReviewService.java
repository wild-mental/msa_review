package hana.corp.msa_review.service;

import hana.corp.msa_review.domain.Review;
import hana.corp.msa_review.domain.ReviewRequest;
import hana.corp.msa_review.repository.ReviewRepository;
import hana.corp.msa_review.repository.UserRepository;
import hana.corp.msa_review.repository.ProductRepository;
import hana.corp.msa_review.domain.User;
import hana.corp.msa_review.domain.Product;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         UserRepository userRepository,
                         ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    // 3.1 Create Review
    public Optional<Review> createReview(ReviewRequest reviewRequest) {
        Optional<User> user = userRepository.findById(reviewRequest.getUserId());
        Optional<Product> product = productRepository.findById(reviewRequest.getProductId());

        if (user.isEmpty() || product.isEmpty()) {
            return Optional.empty();
        }

        Review review = new Review();
        review.setUser(user.get());
        review.setProduct(product.get());
        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());
        review.setCreatedAt(LocalDateTime.now());

        return Optional.of(reviewRepository.save(review));
    }

    // 3.2 Get Review by ID
    public Optional<Review> getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId);
    }

    // 3.3 Get Reviews by Product ID
    public List<Review> getReviewsByProductId(Long productId) {
        return reviewRepository.findByProductProductId(productId);
    }
}
