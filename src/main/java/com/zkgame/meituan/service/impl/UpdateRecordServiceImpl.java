package com.zkgame.meituan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("cityId", updateRecord.getCityId());
		List<UpdateRecord> updateRecords = UpdateRecordMapper.selectByParams(params);
		if (updateRecords.size() == 0) {
			return UpdateRecordMapper.insertSelective(updateRecord);
		} else {
			return 1;
		}
	}

	@Override
	public List<String> getunUpdateCity() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("version", 0);
		List<UpdateRecord> updateRecords = UpdateRecordMapper.selectByParams(params);
		List<String> cityList = new ArrayList<String>();
		int c = updateRecords.size();
		for (int i = 0; i < c; ++i) {
			cityList.add(updateRecords.get(i).getCityId());
		}
		return cityList;
	}

	@Override
	public int updateUpdateRecord(String cityId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityId", cityId);
		List<UpdateRecord> updateRecords = UpdateRecordMapper.selectByParams(params);
		if (updateRecords.size() > 0) {
			UpdateRecord updateRecord = updateRecords.get(0);
			updateRecord.setVersion(1);
			return UpdateRecordMapper.updateByPrimaryKeySelective(updateRecord);
		} else {
			return 0;
		}

	}
}