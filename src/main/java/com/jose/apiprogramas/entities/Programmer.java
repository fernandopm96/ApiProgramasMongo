package com.jose.apiprogramas.entities;

import java.util.Date;

public class Programmer {

    public String name;
    public Date birthDate;
    public Specialization specialization;
    public Level level;
    public Programmer(){

    }

    public Programmer(String name, Date birthDate, Specialization specialization, Level level) {
        this.name = name;
        this.birthDate = birthDate;
        this.specialization = specialization;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}

enum Specialization{Frontend, Backend, FullStack, DevOps }
enum Level{Junior,MidLevel,Senior,Ceo}
