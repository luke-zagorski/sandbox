package pl.com.zagorski.budget.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.repository.annotation.RestResource;
import pl.com.zagorski.budget.model.Expense;

/**
 * Created with IntelliJ IDEA.
 * User: luke
 * Date: 8/11/13
 * Time: 10:41 PM
 * To change this template use File | Settings | File Templates.
 */
@RestResource
public interface ExpenseRepository extends CrudRepository<Expense, Long> {
}
