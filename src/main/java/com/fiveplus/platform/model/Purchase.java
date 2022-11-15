package com.fiveplus.platform.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "purchases")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private LessonType type;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Purchase(int quantity, LessonType type, User owner) {
        this.quantity=quantity;
        this.type=type;
        this.owner=owner;
    }
}