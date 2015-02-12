package com.zkgame.meituan.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkgame.meituan.dao.maper.UpdateRecordMapper;
import com.zkgame.meituan.model.UpdateRecord;
import com.zkgame.meituan.service.IUpdateRecordService;

@Service("UpdateRecordService")
public class UpdateRecordServiceImpl extends ServiceImpl<UpdateRecord, Integer> implements IUpdateRecordService {

	@Autowired
	protected UpdateRecordMapper UpdateRecordMapper;

	@Override
	public int initRecord(UpdateRecord updateRecord) {
		
		HashMap<String, Object> params=new HashMap<String, Object>();
		params.put("cityId", updateRecord.getCityId());
		List<UpdateRecord> updateRecords=UpdateRecordMapper.selectByParams(params);
		if(updateRecords.size()==0)
		{
			return UpdateRecordMapper.insertSelective(updateRecord);
		}
		else {
			return 1;
		}
	}
}