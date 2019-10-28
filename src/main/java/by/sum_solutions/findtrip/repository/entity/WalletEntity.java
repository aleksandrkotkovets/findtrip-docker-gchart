package by.sum_solutions.findtrip.repository.entity;

import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Table(name = "wallet")
public class WalletEntity extends BaseEntity {

    @Column(name = "sum")
    @NotNull
    private Long sum;

    @OneToOne(optional = false, mappedBy = "wallet")
    private UserEntity owner;

    public WalletEntity(@NotNull Long sum, UserEntity owner) {
        this.sum = sum;
        this.owner = owner;
    }

    public WalletEntity() {
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


}
