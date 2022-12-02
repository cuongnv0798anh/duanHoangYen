package com.example.duan_dulich.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "tours")
public class TourEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "price" )
    private Integer price;
    @Column
    private String description;
    @Column
    private String title;
    @Column
    private String totalMember;
    @Column(name = "time_start" )
    private Date timeStart;
    @Column(name = "total_day" )
    private Integer totalDay;
    @Column
    private boolean productStatus;
    @Column
    private int idCompany;
    @Column
    private int demTim;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;
    @OneToMany(mappedBy = "tourEntity", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<ImageTour> imageTours = new ArrayList<>();

}
