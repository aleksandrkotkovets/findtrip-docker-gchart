package by.sum_solutions.findtrip.repository.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "wallet")
public class WalletEntity extends BaseEntity {

    @NotNull
    @Column(name = "sum")
    private Long sum;

    @NotNull
    @OneToOne(mappedBy = "wallet")
    private UserEntity owner;

    public WalletEntity() {
    }

    public WalletEntity(@NotNull Long sum, @NotNull UserEntity owner) {
        this.sum = sum;
        this.owner = owner;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletEntity that = (WalletEntity) o;
        return Objects.equals(sum, that.sum) &&
                Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sum, owner);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WalletEntity{");
        sb.append("sum=").append(sum);
        sb.append(", owner=").append(owner);
        sb.append('}');
        return sb.toString();
    }
}
