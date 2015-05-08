/**
 * @Title: PublisherServiceImpl.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.framework.service.publishers
 * @Description: 接口实现类。
 * @author hankaibo
 * @date 2013-12-25 上午10:20:42
 * @version V1.0
 */
package com.framework.service.tree;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.dao.tree.TreeDao;
import com.framework.entity.tree.TreeEntity;
import com.orm.BaseServiceImpl;
import com.orm.BaseService;

/**
 * @ClassName: PublisherServiceImpl
 * @Description: 接口实现类。
 * @author hankaibo
 * @date 2013-12-25 上午10:20:42
 * 
 */
@Service("treeService")
@Transactional
public class TreeServiceImpl extends BaseServiceImpl<TreeEntity> implements TreeService {

    @Resource(name = "treeDao")
    private TreeDao dao;

	@Override
	public BaseService<TreeEntity> getDao() {
		return dao;
	}

   

}
