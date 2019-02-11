package com.aeon.model;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "aenaflight_2017_01")
public class Source {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "dep_apt_code_iata")
    private String depAptCodeIata;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepAptCodeIata() {
        return depAptCodeIata;
    }

    public void setDepAptCodeIata(String depAptCodeIata) {
        this.depAptCodeIata = depAptCodeIata;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", id, depAptCodeIata);
    }
}
