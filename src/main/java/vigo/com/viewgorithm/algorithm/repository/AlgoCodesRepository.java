package vigo.com.viewgorithm.algorithm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vigo.com.viewgorithm.algorithm.domain.AlgorithmCodes;

@Repository
public interface AlgoCodesRepository extends JpaRepository<AlgorithmCodes, Long> {
    AlgorithmCodes findByAlgorithmNameAlgorithmName(String algorithmName);
}

