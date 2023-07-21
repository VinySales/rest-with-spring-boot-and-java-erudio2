package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	PersonRepository repository;

	public PersonVO findById(Long id) {
		logger.info("Finding person");
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));
	}

	public List<PersonVO> findAll() {
		logger.info("Finding all people");
		return repository.findAll();
	}

	public PersonVO create(PersonVO person) {
		logger.info("Creating person");
		return repository.save(person);
	}

	public PersonVO update(PersonVO person) {
		logger.info("Updating person");

		var entitty = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));

		entitty.setFirstName(person.getFirstName());
		entitty.setLastName(person.getLastName());
		entitty.setGender(person.getGender());
		entitty.setAdress(person.getAdress());

		return repository.save(entitty);
	}

	public void delete(Long id) {
		logger.info("Deleting person");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));

		repository.delete(entity);
	}

}
