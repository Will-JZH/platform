package com.hin.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hin.service.ResourceCalService;

@Controller
@RequestMapping("/cal")
public class ResourceCalController {
	@Resource
	private ResourceCalService calService;
	
	@RequestMapping(value = "/addResourceCal", method = RequestMethod.POST)
    public @ResponseBody void addResourceCal(
    		 @RequestBody Map<String,Object>  params) throws Exception{
		calService.addResourceCal(params);
	}
}
