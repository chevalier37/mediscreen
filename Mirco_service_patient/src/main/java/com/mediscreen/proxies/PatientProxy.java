package com.mediscreen.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "micro-patient")
@RibbonClient(name = "micro-patient")
public interface PatientProxy {

}
