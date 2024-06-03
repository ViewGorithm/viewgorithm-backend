package vigo.com.viewgorithm.algorithm.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vigo.com.viewgorithm.algorithm.dto.AlgorithmCodesDto;
import vigo.com.viewgorithm.algorithm.service.AlgorithmProvider;


@RestController
@RequiredArgsConstructor
public class AlgorithmController {

    private final AlgorithmProvider provider;

    @GetMapping("/algorithm/{language}")
    public ResponseEntity<AlgorithmCodesDto> sendCodes(@PathVariable String language) {
        try {
            AlgorithmCodesDto codesDto = provider.getAlgorithm_Codes(language);

            return new ResponseEntity<>(codesDto, HttpStatus.OK);
            // 코드가 있을 경우 
        }
        catch (NullPointerException e) { // 없을 경우 예외처리 완료
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Algorithm codes not found", e);
        }
    }
}
