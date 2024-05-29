package vigo.com.viewgorithm.algorithm.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class AlgorithmCodes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long algorithm_codes_pk;

    @Column(columnDefinition = "TEXT")
    private String c_code;

    @Column(columnDefinition = "TEXT")
    private String c_sharp_code;

    @Column(columnDefinition = "TEXT")
    private String python_code;

    @Column(columnDefinition = "TEXT")
    private String java_code;

    @Column(columnDefinition = "TEXT")
    private String time_complexity;

    @OneToOne
    @JoinColumn(name = "algorithm_name", referencedColumnName = "algorithm_name", columnDefinition = "varchar(100)")
    private AlgorithmCategory algorithmName;




}
