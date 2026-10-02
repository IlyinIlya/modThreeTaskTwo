package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student get(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student update(Long id, Student student) {
        student.setId(id);
        return studentRepository.save(student);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getByAge(Integer minAge, Integer maxAge) {
        if (minAge != null && maxAge != null) {
            return studentRepository.findByAgeBetween(minAge, maxAge);
        }
        if (minAge != null) {
            return studentRepository.findByAgeGreaterThanEqual(minAge);
        }
        if (maxAge != null) {
            return studentRepository.findByAgeLessThanEqual(maxAge);
        }
        return studentRepository.findAll();
    }
}
