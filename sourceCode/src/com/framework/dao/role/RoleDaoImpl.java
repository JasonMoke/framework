/**
* @Title: RoleDaoImpl.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.framework.dao.role   
* @Description:    
* @author guangchao    
* @date 2013-12-24 下午1:38:43   
* @version V1.0 
*/
package com.framework.dao.role;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.framework.entity.role.RoleManager;
import com.framework.entity.role.UserRoleManager;
import com.orm.BaseDaoImpl;

/**
 * @ClassName: RoleDaoImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author guangchao
 * @date 2013-12-24 下午1:38:43
 *
 */
@Repository("roleDaoImpl")
public class RoleDaoImpl extends BaseDaoImpl<RoleManager> implements RoleDao{
	@Autowired
    private SqlSession sqlSession;
	
	public boolean updateRoleStatus(Object o){
		sqlSession.update("updateRoleStatus",o);
		return true;
	}
	@Override
    public String getNameSpace() {
        return "com.framework.role";
    }
	/**
	 * 
	* @Title: getAllModulesForUser   
	* @Description:   根据用户id获取其角色id 
	* @param @param userId
	* @param @return    
	* @return UserRoleManager      
	* @author guangchao
	* @date 2013-12-24 下午1:39:51 
	* @throws
	 */
	public List<UserRoleManager> getUserRoleByUserId(String userId){
		
		return sqlSession.selectList("getUserRoleByUserId",userId);
	}
	/**
	 * 
	 * @Title: getRoleByUserId   
	 * @Description:   根据用户id获取其角色 
	 * @param @param userId
	 * @param @return    
	 * @return UserRoleManager      
	 * @author guangchao
	 * @date 2013-12-24 下午1:39:51 
	 * @throws
	 */
	public List<RoleManager> getRoleByUserId(String userId){
		
		return sqlSession.selectList("getRoleByUserId",userId);
	}
}
