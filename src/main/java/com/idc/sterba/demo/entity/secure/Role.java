package com.idc.sterba.demo.entity.secure;

import javax.persistence.*;

@Entity
@Table(name = "role", schema = "secure")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleIdSeq")
    @SequenceGenerator(name = "roleIdSeq", sequenceName = "role_id_seq", allocationSize = 1, schema = "secure")
    private Long id;

    @Column
    private String name;

    @Column
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
