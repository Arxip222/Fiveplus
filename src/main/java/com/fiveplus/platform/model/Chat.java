package com.fiveplus.platform.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "chat")
    private Application application;
    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;
    private boolean finished;
    @ManyToMany
    private List<User> users;
    @OneToMany(mappedBy="chat")
    private List<Message> messages;
}
