/**
 * @Project Meituan
 * @Name ProcessStatus
 * @User Slbw
 * @Time 2015-1-29 下午5:10:08
 * @Version 1.0
 * @describe 
 */
package com.zkgame.meituan.util;

import java.io.Serializable;

/**
 * @author F
 *
 */
public class ProcessStatus implements Serializable{
	
	private String status;
	private String process;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}

}
