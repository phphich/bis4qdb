package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by COM-1 on 30/8/2561.
 */
@Entity
@Table(name="tbStudent")
public class Student {
    @Id
    private String id;
    private String name, surname,major;
    private int level;

    public Student() {
    }

    public Student(String id, String name, String surname, String major, int level) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.major = major;
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
