package models.entity;

import javax.persistence.*;

@Entity
public class Car extends BaseEntity {

    private String brand;
    private String model;
    private String year;
    private Engines engine;
    private User user;

    @Column(nullable = false)
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(nullable = false)
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public Engines getEngine() {
        return engine;
    }

    public void setEngine(Engines engine) {
        this.engine = engine;
    }

    @ManyToOne
    @JoinColumn(referencedColumnName = "id",name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
