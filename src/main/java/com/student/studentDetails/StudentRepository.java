package com.student.studentDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, BigInteger> {
    List<Student> findBycourseid(BigInteger id);
}
