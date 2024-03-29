package vigo.com.viewgorithm.algorithm.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlgorithmCategoryDto {

    private String algorithmName;
    private String categoryName;
}
