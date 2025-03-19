package com.in28minutes.springboot.tutorial.basics.application.configuration.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "MediaType")
public class MediaType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MediaTypeId")
    private Integer mediaTypeId;
    @Column(name = "Name", length = 120, nullable = true)
    private String name;

    public MediaType() {
    }

    public MediaType(String name) {
        this.name = name;
    }

    public Integer getMediaTypeId() {
        return mediaTypeId;
    }

    public void setMediaTypeId(Integer mediaTypeId) {
        this.mediaTypeId = mediaTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MediaType{" +
                "mediaTypeId=" + mediaTypeId +
                ", name='" + name + '\'' +
                '}';
    }
}
