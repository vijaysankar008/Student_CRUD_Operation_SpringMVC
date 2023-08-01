/**
 * 
 */
package com.aiht.students_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aiht.students_data.domain.Student;

/**
 * @author VJ
 *
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
