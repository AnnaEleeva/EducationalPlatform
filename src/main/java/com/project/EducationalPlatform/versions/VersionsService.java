package com.project.EducationalPlatform.versions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VersionsService {

	@Autowired
	private VersionsRepository repo;

	public List<Versions> listAll() {
		return repo.findAll();
	}

	public void save(Versions version) {
		repo.save(version);
	}

	public Versions get(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}
}
