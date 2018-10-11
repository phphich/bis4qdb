package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by COM-1 on 27/9/2561.
 */
@Entity
@Table(name = "tbDepartment")
public class Department extends Model {
    @Id
       private String id;
    private String thName, engName, level;
    private int year;

    @ManyToOne()
    private Faculty faculty;

    public Department() {
    }

    public Department(String id, String thName, String engName, String level, int year, Faculty faculty) {
        this.id = id;
        this.thName = thName;
        this.engName = engName;
        this.level = level;
        this.year = year;
        this.faculty = faculty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThName() {
        return thName;
    }

    public void setThName(String thName) {
        this.thName = thName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public static Finder<String, Department> find =
            new Finder<String, Department>(String.class, Department.class);

    public static List<Department> list(){
        return find.all();
    }

    public static void create(Department department){
        department.save();
    }
    public static void update(Department department){
        department.update();
    }
    public static void delete(Department department){
        department.delete();
    }
}
