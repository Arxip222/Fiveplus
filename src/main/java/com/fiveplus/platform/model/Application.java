package com.fiveplus.platform.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "applications")
@NoArgsConstructor
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
    private int dlit;
    private LessonType type;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;
    private int teach_receive;
    private int ostatok_min;
    private boolean free;
    private boolean finished;
}