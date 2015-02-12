package com.zkgame.meituan.util.tag;

import java.io.IOException;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.zkgame.meituan.model.CodeDetailBean;

public class SelectTag extends BaseTag {
	
	public void processContents() throws IOException {
		htmlContents.append("<select id=\"").append(
				id).append("\"").append(" name=\"").append(id).append("\"");
		
		for (Entry<String, Object> attr : this.tagAttrs.entrySet()) {
			htmlContents.append(String.format(ATTR_TEMPLATE, new Object[] {
					attr.getKey(), attr.getValue() }));
		}
		htmlContents.append(">");
		
		if (!emptyOption) {
			htmlContents.append("<option value=''>--请选择--</option>\n");
		}
		
		if (codeResults == null || codeResults.size() == 0) {
			return;
		}
		
		for (CodeDetailBean codeDetail : codeResults) {
			if ((StringUtils.equals(codeDetail.getItemKey(), this.defaultValue))
					|| (StringUtils.equals(codeDetail.getItemValue(),
							this.defaultValue))) {
				htmlContents.append("<option selected=\"true\" value='").append(
						codeDetail.getItemKey()).append("'>").append(
						codeDetail.getItemValue()).append("</option>");
			} else {
				htmlContents.append("<option value='").append(
						codeDetail.getItemKey()).append("'>").append(
						codeDetail.getItemValue()).append("</option>");
			}
		}
		htmlContents.append("</select>");
	}
}