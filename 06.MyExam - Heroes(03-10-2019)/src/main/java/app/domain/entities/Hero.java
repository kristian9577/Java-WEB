package app.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "heroes")
public class Hero extends BaseEntity {
    private String name;
    private Clazz clazz;
    private Integer level;

    public Hero() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "clazz", nullable = false)
    @Enumerated(EnumType.STRING)
    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    @Column(name = "level", nullable = false)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
