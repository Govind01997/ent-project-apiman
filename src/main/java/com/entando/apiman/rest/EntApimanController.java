package com.entando.apiman.rest;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entando.apiman.k8sserviceimpl.EntServiceDetailImpl;
import com.entando.apiman.k8sserviceimpl.WebclientServiceImpl;


import io.kubernetes.client.openapi.ApiException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.entando.apiman.entity.EmployeeEntity;
@RestController
@RequestMapping("/fetcher")
public class EntApimanController {

//	@Autowired
//	InternalServiceFetcher iFetcher;
//	// ExternalServiceFetcher eFetcher;
//
//	@GetMapping("/")
//	public List<String> InternalData() throws FileNotFoundException, IOException, ApiException {
//
//		return iFetcher.fetchService();
//
//	}
	

	@Autowired
	EntServiceDetailImpl entServiceDetailImpl;
	

	@GetMapping("/save")
	public void addurl() throws FileNotFoundException, IOException, ApiException {
		System.out.println("inside controller");

	entServiceDetailImpl.saveUrl();
	//	entServiceDetailImpl.fetchAndSaveK8sServices("Entando");
		
	//	entServiceDetailImpl.fetchK8sServices("entando", "entando");
	}	
	
	@Autowired
	WebclientServiceImpl subjectService;
	 @Autowired
	 EmployeeEntity emp;
	    // @GetMapping("/subject/{id}")
	    // public Mono<EmployeeEntity> getSubject(@PathVariable String id) {
	    //     return subjectService.getSubjectById(id);
	    // }

	    @GetMapping("/org")
	    public Flux<EmployeeEntity> getSubjects() {
	        return subjectService.getSubject();
	    }
	    
	    
		@PostMapping("/orgcreate")
	    public Mono<EmployeeEntity> addEmployee(@RequestBody EmployeeEntity empl) {
			System.out.println("insideController-------------");
		
	        return subjectService.create(empl);
	    }
	
//	
//	
//	@Autowired
//	ExternalServiceFetcher efetcher;
//
//	@GetMapping("/external")
//	public List<String> externalData() throws FileNotFoundException, IOException, ApiException {
//
//		return efetcher.xfetchService();
//	}
}
