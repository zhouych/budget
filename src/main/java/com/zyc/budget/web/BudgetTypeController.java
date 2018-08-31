package com.zyc.budget.web;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.zyc.baselibs.commons.CollectionUtils;
import com.zyc.baselibs.web.BaseController;
import com.zyc.baselibs.web.ResponseResult;
import com.zyc.budget.ApplicationProperties;
import com.zyc.budget.data.BudgetType;

@RestController
public class BudgetTypeController extends BaseController {

	private static final Logger logger = LogManager.getLogger(BudgetTypeController.class);
	
	@Autowired
	private DiscoveryClient client;
	
	@Autowired
	private ApplicationProperties applicationProperties;

    @RequestMapping(value = "/budgetTypes" ,method = RequestMethod.GET)
    public String types() {
		ResponseResult result = new ResponseResult();
		
		result.setData(BudgetType.toMap());
		
		logger.debug("server.port=" + applicationProperties.getServerPort());
		logger.debug("server.servlet.context-path=" + applicationProperties.getServerServletContextPath());
		logger.debug("services=" + Arrays.toString(this.client.getServices().toArray()));
		List<ServiceInstance> sis = this.client.getInstances(applicationProperties.getSpringApplicationName());
		if(CollectionUtils.hasElement(sis)) {
			for (ServiceInstance ins : sis) {
				logger.debug(String.format("scheme=%s; host=%s; port=%s.", ins.getScheme(), ins.getHost(), ins.getPort()));
			}	
		}
		
		return JSON.toJSONString(result);
    }
}
