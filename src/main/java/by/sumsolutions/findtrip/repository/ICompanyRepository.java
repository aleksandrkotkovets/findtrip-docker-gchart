package by.sumsolutions.findtrip.repository;

import by.sumsolutions.findtrip.repository.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompanyRepository extends JpaRepository<CompanyEntity, Long> {
}
