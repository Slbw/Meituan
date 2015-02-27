package com.zkgame.meituan.service;

import java.util.List;

import com.zkgame.meituan.model.UpdateRecord;

public interface IUpdateRecordService extends IService<UpdateRecord, Integer> {

	/**
	 * 初始化数据记录
	 * @param updateRecord
	 * @return
	 */
	public int initRecord(UpdateRecord updateRecord);
	
	/**
	 * 获取没有更新的城市
	 * @return
	 */
	public List<String> getunUpdateCity();
	
	/**
	 * 更新城市数据状态
	 * @param cityId
	 * @return
	 */
	public int updateUpdateRecord(String cityId);
	
}