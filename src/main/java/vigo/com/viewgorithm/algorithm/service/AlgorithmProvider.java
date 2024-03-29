package vigo.com.viewgorithm.algorithm.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vigo.com.viewgorithm.algorithm.domain.AlgorithmCodes;
import vigo.com.viewgorithm.algorithm.domain.AlgorithmCategory;
import vigo.com.viewgorithm.algorithm.dto.AlgorithmCodesDto;
import vigo.com.viewgorithm.algorithm.repository.AlgoCategoryRepository;
import vigo.com.viewgorithm.algorithm.repository.AlgoCodesRepository;

@Service
@RequiredArgsConstructor
public class AlgorithmProvider {

    private final AlgoCategoryRepository categoryRepository;
    private final AlgoCodesRepository codesRepository;

    public AlgorithmCodesDto getAlgorithm_Codes(String algorithmName) {

        AlgorithmCodes algorithmCodes = codesRepository.findByAlgorithmNameAlgorithmName(algorithmName);

        return AlgorithmCodesDto.builder()
                .c_code(algorithmCodes.getC_code())
                .c_sharp_code(algorithmCodes.getC_sharp_code())
                .java_code(algorithmCodes.getJava_code())
                .python_code(algorithmCodes.getPython_code())
                .build();
    }

}
