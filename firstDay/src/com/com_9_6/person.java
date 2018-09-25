package com_9_6;

import java.util.Objects;

public class person {
    private int age;
    private int height;
    private int blood;
    private int weight;
    public person(){

    }
    public person(int age, int height, int blood, int weight) {
        this.age = age;
        this.height = height;
        this.blood = blood;
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        person p = (person) o;
        return  (age << 24 | weight&0xff<<16 | age&0xff<<8 | blood&0xff) == (p.age << 24 | p.weight&0xff<<16 | p.age&0xff<<8 | p.blood&0xff);
    }

    @Override
    public int hashCode() {
        int i = age << 24 | weight&0xff<<16 | age&0xff<<8 | blood&0xff;
        return i;
    }
}
