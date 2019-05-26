package com.walmart.rebates.dao;

import javax.persistence.*;

@Entity
@Table(name="catogery")
public class Category {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    private String name;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[id=%d, name='%s']",
                id, name);
    }

}