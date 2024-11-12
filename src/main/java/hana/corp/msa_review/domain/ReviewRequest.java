package hana.corp.msa_review.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewRequest {
    private Long userId;
    private Long productId;
    private int rating;
    private String comment;
}
