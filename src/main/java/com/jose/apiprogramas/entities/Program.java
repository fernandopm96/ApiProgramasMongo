package com.jose.apiprogramas.entities;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Program {

    @BsonId
    public ObjectId id;

    public String name;

    public Double price;

    public Date publicationDate;

    public List<Programmer> programmers = new ArrayList<>();

    public Program() {
    }

    public Program(String name) {
        this.name = name;
    }

    public Program(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Program(String name, Double price, Date publicationDate, List<Programmer> programmers) {
        this.name = name;
        this.price = price;
        this.publicationDate = publicationDate;
        this.programmers = programmers;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<Programmer> getProgrammers() {
        return programmers;
    }

    public void setProgrammers(List<Programmer> programmers) {
        this.programmers = programmers;
    }
}
