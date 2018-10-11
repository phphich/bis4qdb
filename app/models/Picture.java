package models;

import java.util.Random;

/**
 * Created by COM-1 on 6/9/2561.
 */
public class Picture {
    private String id;
    private String name;
    private String description;
    private String filename;

    public Picture() {
    }

    public Picture(String id, String name, String description, String filename) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.filename=filename;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}

