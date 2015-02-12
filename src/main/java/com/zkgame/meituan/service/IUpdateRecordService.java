package com.zkgame.meituan.service;

import com.zkgame.meituan.model.UpdateRecord;

public interface IUpdateRecordService extends IService<UpdateRecord, Integer> {

	/**
	 * 初始化数据记录
	 * @param updateRecord
	 * @return
	 */
	public int initRecord(UpdateRecord updateRecord);
	
}