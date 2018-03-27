package com.idc.sterba.demo.entity;

import javax.persistence.*;

@Entity
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "colorIdSeq")
    @SequenceGenerator(name = "colorIdSeq", sequenceName = "color_id_seq", allocationSize = 1)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private ColorEnum colorEnum;

    public Color() {}

    public Color(ColorEnum colorEnum) {
        this.colorEnum = colorEnum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ColorEnum getColorEnum() {
        return colorEnum;
    }

    public void setColorEnum(ColorEnum colorEnum) {
        this.colorEnum = colorEnum;
    }
}

enum ColorEnum {
    BLUE, RED
}
