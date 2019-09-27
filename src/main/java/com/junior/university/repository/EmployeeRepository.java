package com.junior.university.repository;

import java.util.function.Function;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.junior.university.model.Employee;

import static org.springframework.data.jpa.domain.Specification.where;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    class Specifications {

        public static Specification<Employee> search(final String search) {
            final Specification<Employee> firstName = createLike(root -> root.get("firstName"), search);
            final Specification<Employee> lastName = createLike(root -> root.get("lastName"), search);
            return firstName.or(lastName);
        }

        static <T> Specification<T> createLike(final Function<Root<T>, Expression<String>> pathToField, final String search) {
            return where((root, query, criteriaBuilder) -> criteriaBuilder.like(
                    criteriaBuilder.lower(pathToField.apply(root)), formatAsLikeExpression(search)));
        }

        private static String formatAsLikeExpression(final String expression) {
            return "%" + expression + "%";
        }
    }
}
