package com.fiveplus.platform.model;

import com.fiveplus.platform.model.Application;
import com.fiveplus.platform.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "children")
@NoArgsConstructor
@Getter
@Setter
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private User parent;
    private String photo;
    @OneToMany(mappedBy = "child")
    private Set<Application> applications;
}

