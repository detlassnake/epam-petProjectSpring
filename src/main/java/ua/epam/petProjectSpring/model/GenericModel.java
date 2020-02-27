package ua.epam.petProjectSpring.model;

import javax.persistence.*;

@MappedSuperclass
public class GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public GenericModel() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
