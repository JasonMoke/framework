package com.framework.dao.user;


import org.springframework.stereotype.Repository;

import com.framework.entity.user.UserInfoAndDataManager;
import com.orm.BaseDaoImpl;

@Repository("userImp")
public class UserImp extends BaseDaoImpl<UserInfoAndDataManager> implements UserDao {
	
	@Override
    public String getNameSpace() {
        return "com.framework.maps.user";
    }
    
}
