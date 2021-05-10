package main.hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "engines")
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String model;

    public static Engine of(String model) {
        Engine engine = new Engine();
        engine.model = model;
        return engine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
