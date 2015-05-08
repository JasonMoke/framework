/**
* @Title: DataLoadUtil.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.oneworld.core.dataload   
* @Description: 
* @author gaoguangchao    
* @date 2014年6月27日 下午2:58:18   
* @version V1.0 
*/

package com.core.dataload;

import java.lang.reflect.Method;

import com.framework.entity.organ.Organ;


/**
 * @ClassName: DataLoadUtil
 * @Description: 
 * @author gaoguangchao
 * @date 2014年6月27日 下午2:58:18
 *
 */

public class DataLoadUtil {

	/**   
	 * @Title: main   
	 * @Description:根据实体生成dataload类的字符串
	 * @param args
	 * @return void        
	 * @author gaoguangchao
	 * @date 2014年6月27日 下午2:58:18 
	 *   
	 */

	public static void main(String[] args) {
		
		generaEntity(new Organ());
	}
	
	public static String generaEntity(Object entity){
		StringBuffer sb = new StringBuffer();
		sb.append(entity.getClass().getSimpleName()).append(" entity = new ").append(entity.getClass().getSimpleName()).append("();\r\n");
		sb.append("entity.getClass().getConstructors();\r\n");
		Method[] Methods = entity.getClass().getDeclaredMethods();
		
		int num = 0;
		int num1 = 0;
		for(Method method:Methods){
			String Name = method.getName();
			Class<?>[] param = method.getParameterTypes();
			String returnType = "";
			if(param.length>0){
				returnType = method.getParameterTypes()[0].getSimpleName();
			}
			if(Name.startsWith("set")){
				num1++;
				if(returnType.contains("String")){
					sb.append("entity.");
					sb.append(Name).append("(rs.getNString(\"").append(Name.substring(3)).append("\"));\r\n");
					num++;
					continue;
				}
				if(returnType.contains("byte[]")){
					sb.append("entity.");
					sb.append(Name).append("(rs.getBytes(\"").append(Name.substring(3)).append("\"));\r\n");
					num++;
					continue;
				}
				if(returnType.contains("byte")){
					sb.append("entity.");
					sb.append(Name).append("(rs.getByte(\"").append(Name.substring(3)).append("\"));\r\n");
					num++;
					continue;
				}
				if(returnType.contains("int")){
					sb.append("entity.");
					sb.append(Name).append("(rs.getInt(\"").append(Name.substring(3)).append("\"));\r\n");
					num++;
					continue;
				}
				if(returnType.contains("long")){
					sb.append("entity.");
					sb.append(Name).append("(rs.getLong(\"").append(Name.substring(3)).append("\"));/n");
					num++;
					continue;
				}
				if(returnType.contains("double")){
					sb.append("entity.");
					sb.append(Name).append("(rs.getDouble(\"").append(Name.substring(3)).append("\"));\r\n");
					num++;
					continue;
				}
				if(returnType.contains("Integer")){
					sb.append("entity.");
					sb.append(Name).append("(rs.getInt(\"").append(Name.substring(3)).append("\"));\r\n");
					num++;
					continue;
				}
				if(returnType.contains("Date")){
					sb.append("entity.");
					sb.append(Name).append("(rs.getDate(\"").append(Name.substring(3)).append("\"));\r\n");
					num++;
					continue;
				}
			}
			
		}
		System.out.println("实体中包含方法个数为："+num1);
		System.out.println("最终生成的属性个数："+num);
		System.out.println(sb.toString());
		return sb.toString();
	}

}
