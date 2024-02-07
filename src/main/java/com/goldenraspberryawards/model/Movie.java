package com.goldenraspberryawards.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Table(name = "tb_movies")
@Getter
@Setter
@Data
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "YEAR_AWARD")
    private Integer year;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "STUDIOS")
    private String studios;

    @Column(name = "PRODUCERS")
    private String producers;

    @Column(name = "WINNER", nullable = true)
    private String winner;

}
