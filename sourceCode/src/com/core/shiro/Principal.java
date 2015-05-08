package com.core.shiro;

import java.io.Serializable;

/**
 * 身份信息
* @ClassName: Principal
* @Description: 身份信息
* @author gaoguangchao
* @date 2014年8月27日 下午3:18:20
*
 */
public class Principal implements Serializable {

	private static final long serialVersionUID = 5798882004228239559L;

	/** ID */
	private String userid;

	/** 用户名 */
	private String username;

	/**
	 * @param id
	 *            ID
	 * @param username
	 *            用户名
	 */
	public Principal(String userid, String username) {
		this.userid = userid;
		this.username = username;
	}

	/**
	 * 获取ID
	 * 
	 * @return ID
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * 设置ID
	 * 
	 * @param id
	 *            ID
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * 获取用户名
	 * 
	 * @return 用户名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置用户名
	 * 
	 * @param username
	 *            用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return username;
	}

}