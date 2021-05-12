package com.efsoftware.crudspringboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efsoftware.crudspringboot.model.Person;
import com.efsoftware.crudspringboot.repository.PersonRepository;

@RestController
@RequestMapping("/person")  
public class PersonController {

	private PersonRepository _personRepository;
	
	public PersonController(PersonRepository personRepository) {
		this._personRepository = personRepository;
	}
	
	@GetMapping
	public List<Person> allPerson(){
		return _personRepository.findAll();    
	}
	
	@GetMapping("/{id}")     
	public ResponseEntity<Person> getById(@PathVariable Integer id){
		 Optional<Person> person = _personRepository.findById(id);
	        if(person.isPresent())
	            return new ResponseEntity<Person>(person.get(), HttpStatus.OK);  
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  
	}
	
	@PostMapping
	public Person addPerson(@RequestBody Person person) {
		return _personRepository.save(person);    
	}
	
	@PutMapping("/{id}")  
	public ResponseEntity<Person> putPerson(@PathVariable Integer id, @RequestBody Person newPerson){
		Optional<Person> oldPerson = _personRepository.findById(id);
        if(oldPerson.isPresent()){
            Person person = oldPerson.get();
            person.setName(newPerson.getName());    
            _personRepository.save(person);
            return new ResponseEntity<Person>(person, HttpStatus.OK);  
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  
	}
	
	@DeleteMapping("/{id}")  
	 public ResponseEntity<Object> deletePerson(@PathVariable Integer id)
	    {
	        Optional<Person> person = _personRepository.findById(id);
	        if(person.isPresent()){
	            _personRepository.delete(person.get());
	            return new ResponseEntity<>(HttpStatus.OK);
	        }
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);    
	    }   
}
