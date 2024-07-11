package uz.incomeservicemanager.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.incomeservicemanager.model.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
}
