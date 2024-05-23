package com.chamdarae.domain;

import javax.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Comment {
    @Id @GeneratedValue
    @Column(name="comment_id")
    private Long id;

    private String commentContent;
    private Long commentLike;
    private LocalDate commentCreateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipe_id")
    private Recipe recipe;
}
