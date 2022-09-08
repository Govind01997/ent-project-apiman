package com.entando.apiman.k8sserviceimpl;

import java.io.IOException;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.entando.apiman.entity.EmployeeEntity;
import com.entando.apiman.entity.EntServiceDetail;

import io.kubernetes.client.openapi.ApiException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class WebclientServiceImpl {
	@Autowired
	WebClient webClient;
	@Autowired
	EntServiceDetailImpl entServiceDetailImpl;
	public Flux<EmployeeEntity> getSubject() {
		return webClient.get()
				.uri("/apiman/organizations/nv/")
				.retrieve()
				.bodyToFlux(EmployeeEntity.class);
	}
	 
	public Mono<EmployeeEntity> create(EmployeeEntity obj)
	{
		System.out.println("insideServiceIMPL-------------");

//		try {
			for(int i=0;i<5;i++)
			{
				obj.setName("Internlalfccccccv"+i);
 				obj.setDescription("okodne");
 				System.out.println("hellow");
			}
			
//			for(String f1:entServiceDetailImpl.fetchK8sServices("Internal", "entando"))
//			{
//				//EmployeeEntity obj=new EmployeeEntity();
//				obj.setName("Internlalfccccv");
//				obj.setDescription(f1);
//			//	obj.setserviceType1("Internal");
//				//System.out.println("inside of saveURL"+f1);
//				//repo.save(obj);
//			}
			
			
//		} catch (IOException | ApiException e) {
//			System.out.println("inside of catch");
//		}
		return webClient.post()
				.uri("/apiman/organizations/")
				.body(Mono.just(obj), EmployeeEntity.class)
				.retrieve()
				.bodyToMono(EmployeeEntity.class)
		.timeout(Duration.ofMillis(10_000));
		
		
	}
}
