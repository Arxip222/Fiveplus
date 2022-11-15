package com.fiveplus.platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "children")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private User parent;
    private String photo;
    @OneToMany(mappedBy = "child")
    private Set<Application> applications;
    private int klass;
}

