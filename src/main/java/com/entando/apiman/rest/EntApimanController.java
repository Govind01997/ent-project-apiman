package com.entando.apiman.rest;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entando.apiman.entity.Entapiman;
import com.entando.apiman.service.ApimanService;

@RestController
@RequestMapping("/api/apiman")

public class EntApimanController {

	private final ApimanService apimanService;

	public EntApimanController(ApimanService apimanService) {
		this.apimanService = apimanService;
	}

	@GetMapping
	// @RolesAllowed("admin")
	// @Scheduled(fixedDelay = 10000)

	public List<Entapiman> GetAllApi() {
		System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
		return apimanService.findAllApiman();

	}

	@Scheduled(fixedDelay = 10000)

	@GetMapping("/pod")
	public void getAllPod() {
		System.out.println("calling pods");
		apimanService.podlist();

	}

}
