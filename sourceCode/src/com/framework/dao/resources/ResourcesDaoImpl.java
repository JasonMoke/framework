
package com.framework.dao.resources;


import org.springframework.stereotype.Repository;

import com.framework.entity.resources.Resources;
import com.orm.BaseDaoImpl;


@Repository("ResourcesDaoImpl")
public class ResourcesDaoImpl extends BaseDaoImpl<Resources> implements ResourcesDao{
	@Override
	public String getNameSpace() {
		return "com.framework.maps.resources";
	}

}
