package org.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.example.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
