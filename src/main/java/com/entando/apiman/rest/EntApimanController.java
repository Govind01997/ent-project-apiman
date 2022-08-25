package com.entando.apiman.rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entando.apiman.k8sService.ExternalServiceFetcher;
import com.entando.apiman.k8sService.InternalServiceFetcher;

import io.kubernetes.client.openapi.ApiException;

@RestController
@RequestMapping("/fetcher")
public class EntApimanController {

	@Autowired
	InternalServiceFetcher iFetcher;
	//ExternalServiceFetcher eFetcher;
  
	@GetMapping("/")
	public String InternalData() throws FileNotFoundException, IOException, ApiException {

		return iFetcher.fetchService();

	}

	@Autowired
	  ExternalServiceFetcher efetcher;
	
	@GetMapping("/external")
	public String externalData() throws FileNotFoundException, IOException, ApiException {
        System.out.println("before xfetch");
		return efetcher.xfetchService();
	}
}
