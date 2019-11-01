package by.sum_solutions.findtrip.repository.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;


@MappedSuperclass
public abstract class BaseEntity extends AbstractPersistable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BaseEntity() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseEntity{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}