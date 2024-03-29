package vigo.com.viewgorithm.algorithm.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vigo.com.viewgorithm.algorithm.dto.AlgorithmCodesDto;
import vigo.com.viewgorithm.algorithm.service.AlgorithmProvider;


@RestController
@RequiredArgsConstructor
public class AlgorithmController {

    private final AlgorithmProvider provider;

    @GetMapping("/algorithm/{language}")
    public ResponseEntity<AlgorithmCodesDto> sendCodes(@PathVariable String language){
        return new ResponseEntity<>(provider.getAlgorithm_Codes(language), HttpStatus.OK);
    }

}
