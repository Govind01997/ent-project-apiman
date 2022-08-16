package com.entando.apiman.serviceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.entando.apiman.entity.Entapiman;
import com.entando.apiman.entity.repository.ApimanRepository;
import com.entando.apiman.k8sService.PodList;
import com.entando.apiman.service.ApimanService;

import io.kubernetes.client.openapi.ApiException;

@Service
public class ApimanServiceImpl implements ApimanService {

	private final ApimanRepository apimanRepository;

	public ApimanServiceImpl(ApimanRepository apimanRepository) {
		this.apimanRepository = apimanRepository;
	}

	@Override
	public List<Entapiman> findAllApiman() {
		return apimanRepository.findAll();
		// return null;
	}

	@Override
	public void podlist() {
		PodList p = new PodList();

		try {
			p.podList();
		} catch (IOException | ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
