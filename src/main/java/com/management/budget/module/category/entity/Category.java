package com.management.budget.module.category.entity;

import com.management.budget.common.entity.BaseEntity;
import com.management.budget.module.member.entity.Member;
import com.management.budget.module.spending.entity.Spending;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "category")
    private List<Spending> spendings;
}
