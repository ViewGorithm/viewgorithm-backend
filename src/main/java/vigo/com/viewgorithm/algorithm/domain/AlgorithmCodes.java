package vigo.com.viewgorithm.algorithm.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Entity
@Setter
public class AlgorithmCodes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String c_code;

    @Column(columnDefinition = "TEXT")
    private String c_sharp_code;

    @Column(columnDefinition = "TEXT")
    private String python_code;

    @Column(columnDefinition = "TEXT")
    private String java_code;

    @OneToOne
    @JoinColumn(name = "algorithm_name")
    private AlgorithmCategory algorithmCategory;

}
