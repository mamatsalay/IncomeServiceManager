package uz.incomeservicemanager.service;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.incomeservicemanager.exceptions.IncomeNotFoundException;
import uz.incomeservicemanager.model.Income;
import uz.incomeservicemanager.repository.IncomeRepository;

import java.util.Optional;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<Income> getAllIncome() {
        return incomeRepository.findAll();
    }

    @Transactional
    public Income saveIncome(Income income) {
        if (income.getId() != null) {
            Income existingIncome = incomeRepository.
                    findById(income.getId()).
                    orElseThrow(() -> new IncomeNotFoundException(income.getId()));
            existingIncome.setAmount(income.getAmount());
            existingIncome.setDate(income.getDate());
            existingIncome.setDescription(income.getDescription());
            return existingIncome;
        } else {
            entityManager.persist(income);
            return income;
        }
    }

    @Transactional(readOnly = true)
    public Income getIncome(Long id) {
        return incomeRepository.findById(id).orElseThrow(() -> new IncomeNotFoundException(id));
    }

    public void deleteIncome(Long id) {
        Income income = entityManager.find(Income.class, id);
        if (income != null) {
            entityManager.remove(income);
        }
    }
}
