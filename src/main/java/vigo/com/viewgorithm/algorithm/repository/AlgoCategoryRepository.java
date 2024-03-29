package vigo.com.viewgorithm.algorithm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vigo.com.viewgorithm.algorithm.domain.AlgorithmCategory;
import vigo.com.viewgorithm.algorithm.domain.AlgorithmCodes;

@Repository
public interface AlgoCategoryRepository extends JpaRepository<AlgorithmCategory, Long> {

//    AlgorithmCategory findByAlgorithmName(String algorithmName);

}
