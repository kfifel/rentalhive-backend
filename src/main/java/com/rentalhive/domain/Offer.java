package com.rentalhive.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rentalhive.enums.OfferStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double overallCost;
    @Enumerated(EnumType.STRING)
    private OfferStatus status;
    private Boolean negotiable;

    @ManyToOne
    @JsonBackReference
    private Order order;
}
