/**
 * @Project Meituan
 * @Name UpdateRecordController
 * @User Slbw
 * @Time 2015-1-27 下午5:24:47
 * @Version 1.0
 * @describe 
 */
package com.zkgame.meituan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkgame.meituan.model.UpdateRecord;
import com.zkgame.meituan.service.IUpdateRecordService;
import com.zkgame.meituan.util.ProcessStatus;

/**
 * @author F
 * 
 */
@Controller
@RequestMapping("/updateRecord")
public class UpdateRecordController extends BaseController {

	@Autowired
	private IUpdateRecordService updateRecordService;

	@RequestMapping(value = "/list", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object list(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (pageNumber != null && pageSize != null) {
			pageNumber = (pageNumber - 1) * pageSize;

			params.put("startRow", pageNumber);
			params.put("pageSize", pageSize);
		}
		List<UpdateRecord> updateRecords = updateRecordService.searchByParams(params);
		return updateRecords;
	}
	
	@RequestMapping(value = "/process", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object process(HttpServletRequest request) {
		HttpSession session=request.getSession();
		HashMap<String,ProcessStatus> map=(HashMap<String, ProcessStatus>) session.getAttribute("updateStatus");
		return map;
	} 

}
