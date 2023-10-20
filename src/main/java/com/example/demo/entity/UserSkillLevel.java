package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "skill_level_t")
@IdClass(UserSkillLevelId.class) // 複合主キークラスを指定
public class UserSkillLevel {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Id
    @Column(name = "skill_id")
    private int skillId;

    @Column(name = "level")
    private int level;

    @ManyToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "skill_id", insertable = false, updatable = false)
    private Skill skill;
}
