package com.zkgame.meituan.service.impl.codeGenerator;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkgame.meituan.dao.maper.SQLMapper;
import com.zkgame.meituan.service.codeGenerator.ICodeGenerator;
import com.zkgame.meituan.service.codeGenerator.IGeneratorService;

import freemarker.template.Configuration;

@Service("generatorService")
public class GeneratorServiceImpl implements IGeneratorService {
//	private static final Logger logger = Logger.getLogger(GeneratorServiceImpl.class);
	
	protected String templatesDir = workspace + "src/main/java/com/zkgame/meituan/util/templates";
	
	private static final String FIELD_TYPE_STRING = String.class.getSimpleName();
	private static final String FIELD_TYPE_INTEGER = Integer.class.getSimpleName();
	private static final String FIELD_TYPE_LONG = Long.class.getSimpleName();
	private static final String FIELD_TYPE_DOUBLE = Double.class.getSimpleName();
	private static final String FIELD_TYPE_DATE = Date.class.getSimpleName();
	private static final String FIELD_TYPE_BLOB = Blob.class.getSimpleName();
	
	private Map<String, Object> targetClazz = new HashMap<String, Object>();
	
	private ICodeGenerator[] objs = {new ModelGenerator(workspace), new XmlMapperGenerator(workspace), new JavaMapperGenerator(workspace), new ServiceInterfaceGenerator(workspace), new ServiceGenerator(workspace)};
	
	private Configuration configuration; // FreeMarker配置
    private String pageEncoding = "UTF-8";  
	
    @Autowired
	private SQLMapper sqlMapper;
    
	/**
	 * 创建和调整配置,这个在生命周期中只做一次
	 */  
    public GeneratorServiceImpl() {  
        configuration = new Configuration();  
        try {
			configuration.setDirectoryForTemplateLoading(new File(templatesDir));
		} catch (IOException e) {
			e.printStackTrace();
		}  
        configuration.setEncoding(Locale.getDefault(), pageEncoding);  
    }
	
    /**
	 * 获取实体bean名称
	 * 
	 * @param tableName
	 * @return
	 */
	private String tableToBeanName(String tableName, boolean firstUpperCase) {
		if (tableName.startsWith("t_")) {
			tableName = tableName.substring(1);
		}
		StringBuffer sb = new StringBuffer(tableName);
		if(firstUpperCase) {
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		}
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == '_') {
				sb.setCharAt(i + 1, Character.toUpperCase(sb.charAt(i + 1)));
				sb.deleteCharAt(i);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 获取字段名称
	 * 
	 * @param columnName
	 * @return
	 */
	private String columnToPropertyName(String columnName, boolean firstUpperCase) {
		StringBuffer sb = new StringBuffer(columnName);
		if(firstUpperCase) {
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		}
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == '_') {
				sb.setCharAt(i + 1, Character.toUpperCase(sb.charAt(i + 1)));
				sb.deleteCharAt(i);
			}
		}
		return sb.toString();
	}
	
	private List<Map<String, String>> getFields(String tableName) {
		String sql = "SELECT column_name, data_type, column_comment "
				+ "FROM Information_schema.columns WHERE table_Name='"
				+ tableName + "' AND table_schema = (SELECT DATABASE())";
		List<Map<String, Object>> list = sqlMapper.selectBySQL(sql);
		
		List<Map<String, String>> fields = new ArrayList<Map<String,String>>();
		
		for (Map<String, Object> map : list) {
			String fieldName = map.get("column_name").toString();
			String fieldType = map.get("data_type").toString().toLowerCase();
			String fieldRemark = map.get("column_comment").toString();
			
			String propertyName = columnToPropertyName(fieldName.toLowerCase(), false);
			String propertyType = sqlType2JavaType(fieldType);
			
			// 替换get/set方法中的属性名
			String propertyNameGS = columnToPropertyName(fieldName.toLowerCase(), true);
			
			Map<String, String> fieldMap = new HashMap<String, String>();
			fieldMap.put("name", fieldName);
			fieldMap.put("type", sqlType2MapperType(fieldType));
			fieldMap.put("remark", fieldRemark);
			fieldMap.put("propertyName", propertyName);
			fieldMap.put("propertyType", propertyType);
			fieldMap.put("propertyNameGS", propertyNameGS);
			fields.add(fieldMap);
		}
		return fields;
	}
	
	private String sqlType2MapperType(String sqlType) {
		String mapperType = null;
		if ("int".equalsIgnoreCase(sqlType)) {
			mapperType = "INTEGER";
		} else if ("long".equalsIgnoreCase(sqlType)) {
			mapperType = "DOUBLE";
		} else if (("decimal".equals(sqlType)) || ("number".equals(sqlType))) {
			mapperType = "DOUBLE";
		} else if (("datetime".equals(sqlType)) || ("date".equals(sqlType))) {
			mapperType = "DATE";
		} else if (("image".equals(sqlType)) || ("blob".equals(sqlType))) {
			mapperType = "BLOB";
		} else {
			mapperType = "VARCHAR";
		}
		return mapperType;
	}
	
	private String sqlType2JavaType(String sqlType) {
		String propType = null;
		if ("int".equalsIgnoreCase(sqlType)) {
			propType = FIELD_TYPE_INTEGER;
		} else if ("long".equalsIgnoreCase(sqlType)) {
			propType = FIELD_TYPE_LONG;
		} else if (("decimal".equals(sqlType)) || ("number".equals(sqlType)) || ("double".equals(sqlType))) {
			propType = FIELD_TYPE_DOUBLE;
		} else if (("datetime".equals(sqlType)) || ("date".equals(sqlType)) || ("timestamp".equals(sqlType))) {
			propType = FIELD_TYPE_DATE;
		} else if (("image".equals(sqlType)) || ("blob".equals(sqlType))) {
			propType = FIELD_TYPE_BLOB;
		} else {
			propType = FIELD_TYPE_STRING;
		}
		return propType;
	}
    
	@Override
	public void createCode(String tableName) {
		targetClazz.put("tableName", tableName);
		targetClazz.put("modelName", tableToBeanName(tableName, true));
		targetClazz.put("fields", getFields(tableName));
		targetClazz.put("serviceName", tableToBeanName(tableName, false));
		for(ICodeGenerator codeGenerator : objs) {
			try {
				codeGenerator.excute(configuration, targetClazz);
			} catch (Exception e) {
//				logger.error(e.getMessage(), e);
			}
		}
	}

}
