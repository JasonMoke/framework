/**
* @Title: RoleGroup.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.oneworld.core.helper.user   
* @Description:    
* @author guangchao    
* @date 2014-3-26 下午3:16:20   
* @version V1.0 
*/
package com.core.helper.user;


/**
 * @ClassName: RoleGroup
 * @Description: 角色组
 * @author guangchao
 * @date 2014-3-26 下午3:16:20
 *
 */
public enum RoleGroup {
	ADMIN("admin"), DEV("dev"), AUTHOR("author"), PUBLISHER("publisher"), PRODUCTION(
	        "production"), PUBLISHERMANAGER("publishermanager"),CHANNEL("channel"),OTHER("other");
	 
	private final String role;
	 
	private RoleGroup(String role) {
	     this.role = role;
	}
	
	public static String getRoleName(String roleName){
		String RoleName = "";
		if("ADMIN".equals(roleName)){
			RoleName = RoleGroup.ADMIN.getRole().toString();
		}
		if("DEV".equals(roleName)){
			RoleName = RoleGroup.DEV.getRole().toString();
		}
		if("AUTHOR".equals(roleName)){
			RoleName =  RoleGroup.AUTHOR.getRole().toString();
		}
		if("PUBLISHER".equals(roleName)){
			RoleName =  RoleGroup.PUBLISHER.getRole().toString();
		}
		if("PRODUCTION".equals(roleName)){
			RoleName =  RoleGroup.PRODUCTION.getRole().toString();
		}
		if("PUBLISHERMANAGER".equals(roleName)){
			RoleName =  RoleGroup.PUBLISHERMANAGER.getRole().toString();
		}
		if("CHANNEL".equals(roleName)){
			RoleName =  RoleGroup.CHANNEL.getRole().toString();
		}
		if("OTHER".equals(roleName)){
			RoleName =  RoleGroup.OTHER.getRole().toString();
		}
		return RoleName;
	}
	 
	public String getRole() {
	     return role;
	}
}
