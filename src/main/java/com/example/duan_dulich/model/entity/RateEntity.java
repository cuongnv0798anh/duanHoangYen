package com.example.duan_dulich.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post_comment")
public class RateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String comment ;
    @ManyToOne
    private Account account ;

    @ManyToOne
    @JoinColumn(name = "id_tour")
    private TourEntity tourEntity;
}
