package com.core.shiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;





import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.framework.entity.user.UserInfoManager;
import com.framework.service.user.IUserService;
import com.util.SessionInfo;
import com.util.Util;

/**
 * 
* @ClassName: AuthenticationRealm
* @Description: 权限认证
* @author gaoguangchao
* @date 2014年8月27日 下午3:35:51
*
 */
public class AuthenticationRealm extends AuthorizingRealm {

	@Resource(name="userService")
	private IUserService userService;
	/**
	 * 获取认证信息
	 * 
	 * @param token
	 *            令牌
	 * @return 认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token) {
		AuthenticationToken authenticationToken = (AuthenticationToken) token;
		String username = authenticationToken.getUsername();
		String password = new String(authenticationToken.getPassword());
//		String captchaId = authenticationToken.getCaptchaId();
//		String captcha = authenticationToken.getCaptcha();
//		String ip = authenticationToken.getHost();
		if (username != null && password != null) {
			UserInfoManager user_r = new UserInfoManager();
	    	Map<String,String> user =  new HashMap<String, String>();
			String passWordMd5 = Util.MD5(password);
			user.put("username", username);
			user.put("password", passWordMd5);
	    	user_r = userService.findObjectByCondition("getUserInfoForLogin", user);
			if (user_r == null) {
				throw new UnknownAccountException();
			}
			if (user_r.getStatus()!=1) {
				throw new DisabledAccountException();
			}
     		SessionInfo.setCurUser(user_r);
			return new SimpleAuthenticationInfo(new Principal(user_r.getUserId(), username), password, getName());
		}
		throw new UnknownAccountException();
	}

	/**
	 * 获取授权信息
	 * 
	 * @param principals
	 *            principals
	 * @return 授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) principals.fromRealm(getName()).iterator().next();
		if (principal != null) {
			List<String> authorities = userService.findAuthorities(principal.getUserid());
			if (authorities != null) {
				SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
				authorizationInfo.addStringPermissions(authorities);
				return authorizationInfo;
			}
		}
		return null;
	}

}