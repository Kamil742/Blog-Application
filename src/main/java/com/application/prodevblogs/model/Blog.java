package com.application.prodevblogs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long blogId;
    private String imageUrl;
    @NotNull
    private String title;
    @NotNull
    private LocalDate date;

    @NotNull
    @ManyToOne
    private Category category;

    @Column(length = 10000)
    private String content;

    @ManyToOne
    private UserProfile userProfile;

    @OneToOne
    private  BlogFiles blogFiles;
}
