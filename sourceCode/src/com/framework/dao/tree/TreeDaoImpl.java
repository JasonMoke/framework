/**
 * @Title: TreeDaoImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.dao.publishers
 * @Description: DAO实现类。
 * @author guangchao
 * @date 2013-12-25 上午10:28:56
 * @version V1.0
 */
package com.framework.dao.tree;

import org.springframework.stereotype.Repository;

import com.framework.entity.tree.TreeEntity;
import com.orm.BaseDaoImpl;

/**
 * @ClassName: TreeDaoImpl
 * @Description: DAO实现类。
 * @author guangchao
 * @date 2013-12-25 上午10:28:56
 * 
 */
@Repository("treeDao")
public class TreeDaoImpl extends BaseDaoImpl<TreeEntity> implements TreeDao {

    @Override
    public String getNameSpace() {
        return "com.framework.tree";
    }


}
