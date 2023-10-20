package com.example.demo.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserSkillLevelId implements Serializable {
    private String userId;
    private int skillId;
}
