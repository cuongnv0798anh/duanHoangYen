package com.example.duan_dulich.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "imagesTour")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ImageTour {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id ;
    private String fileUrl ;
    private String filename ;
    private String fileType ;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    @JsonIgnore
    private TourEntity tourEntity ;
    public ImageTour(String filename, String fileUrl, String fileType) {
        this.filename = filename;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
    }
}
