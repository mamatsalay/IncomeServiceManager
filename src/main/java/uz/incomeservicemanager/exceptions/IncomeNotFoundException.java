package uz.incomeservicemanager.exceptions;

public class IncomeNotFoundException extends RuntimeException {
    public IncomeNotFoundException(Long id) {
        super("Income not found with id");
    }
}
