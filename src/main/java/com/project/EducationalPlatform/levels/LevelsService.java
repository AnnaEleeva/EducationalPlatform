package com.project.EducationalPlatform.levels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LevelsService {

	@Autowired
	private LevelsRepository repo;

	public List<Levels> listAll() {
		return repo.findAll();
	}

	public void save(Levels level) {
		repo.save(level);
	}

	public Levels get(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}
}
