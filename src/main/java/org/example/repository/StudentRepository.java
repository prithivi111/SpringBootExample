package org.example.repository;


//import org.springframework.data.jpa.repository.Query;
import org.example.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    //We have method called findById, but not findByStudId.
    //Even though the method below is not in the library of CrudRepository, the implementation of the method is automatically
    // handled by the CrudRepository as we have provided method name and parameters in very appropriate manner as same as mentioned in the Entity
    Student findByStudId(Integer studId);

    //Even though the method below is not in the library of CrudRepository, the implementation of the method is automatically
    // handled by the CrudRepository as we have provided method name in very appropriate manner as mentioned in the Entity
  //  Optional<Student> findByStudIdAndStudName(Integer studId, String studName);  //The parameter and instance variable should be as same as mentioned in the Entity class

    //We are creating a custom method with the same parameters as above.
    // But we need to write the implementation here or annotate with @Query because we are providing the method name
    // inappropriate manner, which confuses the automatic process of implementation.
   @Query("SELECT s FROM Student as s where s.studId=:studId AND s.studName=:studName")
   Optional<Student> findByIdAndName(@Param("studId") Integer studId, @Param("studName") String studName);
    //Optional<Student> findByIdAndName(Integer studId, String studName);


}