/**
 * 
 */
package com.core.dataload;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.core.db.ILoadData;
import com.framework.entity.organ.Organ;


/**
 * 
* @ClassName: LoadOrgan
* @Description: 
* @author gaoguangchao
* @date 2014年9月9日 下午5:12:45
*
 */
public class LoadOrgan implements ILoadData{

	@SuppressWarnings("unchecked")
	@Override
	public <T> T loadData(ResultSet rs) throws SQLException {
		
		Organ entity = new Organ();
		entity.getClass().getConstructors();
		entity.setOrganId(rs.getNString("OrganId"));
		entity.setCname(rs.getNString("Cname"));
		entity.setShortCname(rs.getNString("ShortCname"));
		entity.setOrgCode(rs.getNString("OrgCode"));
		entity.setEnname(rs.getNString("Enname"));
		entity.setOrgGrade(rs.getNString("OrgGrade"));
		entity.setParentId(rs.getNString("ParentId"));
		entity.setOrgPhone(rs.getNString("OrgPhone"));
		entity.setOrgAddr(rs.getNString("OrgAddr"));
		entity.setOrgEmail(rs.getNString("OrgEmail"));
		entity.setOrgDoorNum(rs.getNString("OrgDoorNum"));
		entity.setSeqNum(rs.getInt("SeqNum"));
		entity.setMemo(rs.getNString("Memo"));
		entity.setRespPerson(rs.getNString("RespPerson"));
		entity.setLinkMan(rs.getNString("LinkMan"));
		entity.setCreateTime(rs.getDate("CreateTime"));
		entity.setUpdateTime(rs.getDate("UpdateTime"));
		entity.setIsResrved(rs.getNString("IsResrved"));
		entity.setStatus(rs.getInt("Status"));
		entity.setTreePath(rs.getNString("TreePath"));
		entity.setCreatePerson(rs.getNString("CreatePerson"));
		entity.setUpdatePerson(rs.getNString("UpdatePerson"));
		
		return (T)entity;
	}

}
