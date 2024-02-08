package com.application.prodevblogs.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String imageUrl;
    private String firstName;
    private String lastName;
    private String emailId;
    private Long sizeAvailable;
    @OneToMany
    private List<Blog> blog;
}
