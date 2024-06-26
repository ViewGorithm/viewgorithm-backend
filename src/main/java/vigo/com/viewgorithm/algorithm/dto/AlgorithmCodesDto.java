package vigo.com.viewgorithm.algorithm.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlgorithmCodesDto {

    private String c_code;
    private String c_sharp_code;
    private String python_code;
    private String java_code;
    private String time_complexity;

}