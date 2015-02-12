package com.zkgame.meituan.service.impl.codeGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import com.zkgame.meituan.service.codeGenerator.ICodeGenerator;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class ModelGenerator implements ICodeGenerator {
    private String pageEncoding = "UTF-8";  
    
	private String targetFileDir;
	
	public ModelGenerator(String workspace) {
		this.targetFileDir = workspace + "src/main/java/com/zkgame/meituan/model";
	}

	// @Autowired
	// private SqlSessionFactoryBean sqlSessionFactory;

	// private Connection conn = null;

//	@Override
//	public void createCode(String tableName) throws Exception {
		// getConnection();
		// DatabaseMetaData dbmd = conn.getMetaData();
		// ResultSet rs = dbmd.getColumns(null, null, tableName, null);
		// while (rs.next()) {
		// System.out.println(rs.getString("COLUMN_NAME") + "----"
		// + rs.getString("REMARKS"));
		// }
		
	/* 1，获取模板 */
//        Template temp = configuration.getTemplate("Java_Entity.ftl", pageEncoding);  
//        temp.setEncoding(pageEncoding);  
//        
	// // 填充Map
//        Map<String, Object> targetClazz = new HashMap<String, Object>(); 
//        targetClazz.put("modelName", tableToBeanName(tableName));
//        getFields(tableName);
//        targetClazz.put("fields", fields);
//        targetClazz.put("getSetFields", getSetFields);
//        
//        String targetFileDir = srcDir + "/cn";
//        String fileName = targetFileDir + "/"+ tableToBeanName(tableName) + ".java";  
//        File target = new File(fileName);  
//        Writer out = new OutputStreamWriter(new FileOutputStream(target), pageEncoding);  
//        temp.process(targetClazz, out);  
//        out.flush();  
//        out.close();  
//	}

	// private void getConnection() throws Exception {
	// SqlSession sqlSession = sqlSessionFactory.getObject().openSession();
	// if (conn == null) {
	// conn = sqlSession.getConnection();
	// }
	// }

	@Override
	public void excute(Configuration conf, Map<String, Object> clazz) throws Exception {
		/* 1，获取模板 */
        Template temp = conf.getTemplate("Java_Entity.ftl", pageEncoding);  
        temp.setEncoding(pageEncoding);  
        
        String fileName = targetFileDir + "/"+ clazz.get("modelName") + ".java";  
        File target = new File(fileName);  
        Writer out = new OutputStreamWriter(new FileOutputStream(target), pageEncoding);  
        temp.process(clazz, out);  
        out.flush();  
        out.close();  
	}
}
