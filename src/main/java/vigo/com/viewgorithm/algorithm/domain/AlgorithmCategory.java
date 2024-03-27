package vigo.com.viewgorithm.algorithm.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
public class AlgorithmCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "algorithm_name")
    private String algorithmName;

    @Column(name = "category_name")
    private String categoryName;

}
