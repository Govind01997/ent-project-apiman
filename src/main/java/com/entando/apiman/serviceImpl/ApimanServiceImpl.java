
/*
package com.entando.apiman.serviceImpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entando.apiman.entity.EntServiceDetail;
import com.entando.apiman.k8sService.InternalServiceFetcher;
import com.entando.apiman.repositories.Repo;
import com.entando.apiman.service.EntApimanService;

import io.kubernetes.client.openapi.ApiException;

@Service
public class ApimanServiceImpl implements EntApimanService {

	@Autowired
	private Repo repo;
	@Autowired
	private InternalServiceFetcher fetch;
	
	@Override
	
	public  void saveUrl(  ) {
		try {
			for(String f1:fetch.fetchService())
			{
				EntServiceDetail obj=new EntServiceDetail();
				obj.setUrl(f1);
				repo.save(obj);
			}
			
			
		} catch (IOException | ApiException e) {
			
			e.printStackTrace();
		}
		
	}

}
*/