package com.fiveplus.platform.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "applications")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private User parent;
    private Date start_less;
    private Date finish_less;
    private int dlitT;
    private int dlitP;
    @Enumerated(EnumType.STRING)
    private LessonType type;
    @OneToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;
    private int teach_receive;
    private int ostatok_min;
    private boolean free;//Table
    private boolean finished;
}