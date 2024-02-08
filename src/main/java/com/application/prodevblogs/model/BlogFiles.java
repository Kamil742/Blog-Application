package com.application.prodevblogs.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BlogFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long blogFileId;
    @NotNull
    private String path;
    @NotNull
    private Long size;

}
