package com.itechart.commuterange.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CitiesDirection {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    private City from;

    @OneToOne(cascade = CascadeType.ALL)
    private City to;

    private int distance;

    public CitiesDirection(City from, City to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public CitiesDirection() {
    }
}
