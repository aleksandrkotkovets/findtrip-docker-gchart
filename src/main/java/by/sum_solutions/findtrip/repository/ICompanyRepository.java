package by.sum_solutions.findtrip.repository;

import by.sum_solutions.findtrip.repository.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompanyRepository extends JpaRepository<CompanyEntity, Long> {
}
