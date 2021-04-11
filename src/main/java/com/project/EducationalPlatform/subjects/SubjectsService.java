package com.project.EducationalPlatform.subjects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubjectsService {

    @Autowired
    private SubjectsRepository repo;

    public List<Subjects> listAll() {
        return repo.findAll();
    }

    public void save(Subjects subject) {
        repo.save(subject);
    }

    public Subjects get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
