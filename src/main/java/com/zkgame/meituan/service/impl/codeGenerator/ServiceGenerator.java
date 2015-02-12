package com.zkgame.meituan.service.impl.codeGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import com.zkgame.meituan.service.codeGenerator.ICodeGenerator;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class ServiceGenerator implements ICodeGenerator {
    private String pageEncoding = "UTF-8";  
    
	private String targetFileDir;
	
	public ServiceGenerator(String workspace) {
		this.targetFileDir = workspace + "src/main/java/com/zkgame/meituan/service/impl";
	}

	@Override
	public void excute(Configuration conf, Map<String, Object> clazz) throws Exception {
		/* 1，获取模板 */
        Template temp = conf.getTemplate("Java_Service_Impl.ftl", pageEncoding);  
        temp.setEncoding(pageEncoding);  
        
        String fileName = targetFileDir + "/"+ clazz.get("modelName") + "ServiceImpl.java";  
        File target = new File(fileName);  
        Writer out = new OutputStreamWriter(new FileOutputStream(target), pageEncoding);  
        temp.process(clazz, out);  
        out.flush();  
        out.close();  
	}
}
