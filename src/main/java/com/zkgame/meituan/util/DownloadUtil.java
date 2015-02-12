/**
 * @Project Meituan
 * @Name DownloadUtil
 * @User Slbw
 * @Time 2015-1-22 上午9:58:22
 * @Version 1.0
 * @describe 
 */
package com.zkgame.meituan.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpSession;

/**
 * @author F 下载器
 */
public class DownloadUtil {

	/** http下载 */
	public static boolean httpDownload(String httpUrl, String saveFile,String cityId,HttpSession session) {
		// 下载网络文件
		int bytesum = 0;
		int byteread = 0;

		int lastProcess=0;
		
		URL url = null;
		try {
			url = new URL(httpUrl);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}

		try {
			URLConnection conn = url.openConnection();
			InputStream inStream = conn.getInputStream();
//			System.err.println("文件长度："+conn.getContentLength());
			long length=conn.getContentLength();
			FileOutputStream fs = new FileOutputStream(saveFile);

			byte[] buffer = new byte[4096];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				int tempProcess=(int)(bytesum*100l/length);
				if(tempProcess!=lastProcess)
				{
					System.out.println(tempProcess+"%");
					ProcessUtil.newInstance(session).setProcess(cityId,tempProcess+"%","数据下载");
					lastProcess=tempProcess;
				}
				fs.write(buffer, 0, byteread);
			}
			fs.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
