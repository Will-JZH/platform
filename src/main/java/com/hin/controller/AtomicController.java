package com.hin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hin.entity.Atomic;
import com.hin.entity.SumAtomic;
import com.hin.service.AtomicService;

@Controller
@RequestMapping("/atomic")
public class AtomicController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AtomicController.class);
	
	@Resource
    private AtomicService atomicService;

    @RequestMapping(value="/searchByServiceName/{serviceName}", method = RequestMethod.GET)
    @ResponseBody
    public List<Atomic> getByServiceName(@PathVariable String serviceName) {
    	LOGGER.info(serviceName);
        return atomicService.getByServiceName(serviceName);
    }
    
    @RequestMapping(value="/searchByEnterpriseName/{enterpriseName}", method = RequestMethod.GET)
    @ResponseBody
    public List<Atomic> getByEnterpriseName(@PathVariable String enterpriseName) {
        return atomicService.getByEnterpriseName(enterpriseName);
    }
    
    @RequestMapping(value="/sumAtomic", method = RequestMethod.POST)
    @ResponseBody
    public SumAtomic sumPriceAndTime(@RequestBody Map<String, ArrayList<String>> services) {
    	return atomicService.sumPriceAndTime(services);
    }
}
