package com.techacademy.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
@Table(name = "authentication")
public class Authentication {

    public static enum Role {
        管理者, 一般
    }
    /** ユーザ名 */
    @Id
    @Column(length = 20, nullable = false)
    @NotEmpty
    @Length(max=20)
    private String code;

    /** パスワード */
    @Column(length = 255, nullable = false)
    @NotEmpty
    @Length(max=255)
    private String password;

    /** 列挙型（文字列） */
    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    @NotNull
    @Length(max=10)
    private Role role;

    /** 有効日付 */

    /** 従業員ID */
    @OneToOne
    @JoinColumn(name="employee_id", referencedColumnName="id")
    @NotEmpty
    @NotNull
    private Employee employee;

}
