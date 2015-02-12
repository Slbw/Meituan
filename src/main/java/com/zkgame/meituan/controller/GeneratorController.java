package com.zkgame.meituan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zkgame.meituan.service.codeGenerator.IGeneratorService;

@Controller
@RequestMapping("/generator")
public class GeneratorController extends BaseController {
//	private static Logger logger = Logger.getLogger(GeneratorController.class); 
	
	@Autowired
	private IGeneratorService modelGeneratorService;
	
	/**
	 * 生成代码
	 */
	@RequestMapping("createCode")
	public Object createCode(@RequestParam("tableName") String tableName) {
//		logger.debug("进入[生成代码]模块");
		
		try {
			modelGeneratorService.createCode(tableName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:"+getBasePath()+"/pages/hidden.jsp";
	}
}
