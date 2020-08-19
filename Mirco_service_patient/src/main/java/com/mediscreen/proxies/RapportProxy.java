package com.mediscreen.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "micro-rapport")
@RibbonClient(name = "micro-rapport")
public interface RapportProxy {

	@GetMapping("rapport/getRapport/{patientId}")
	String getRapport(@PathVariable("patientId") int patientId);

}
