package com.springboot.mealkart.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Data
@Embeddable
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderingDetailPK implements Serializable {

    private String orderingUuid;
    private String orderingDetailUuid;
}
