
package com.core.framework.spring;

import javax.naming.NamingException;
import javax.transaction.SystemException;

import org.objectweb.jotm.Current;
import org.objectweb.jotm.Jotm;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;

/**
 * 
* @ClassName: JotmFactoryBean
* @Description: 由于spring 3.0 之后不再提供对jotm的封装，我们可以手动的编写加入代码
* @author gaoguangchao
* @date 2015年3月2日 上午11:05:20
*
 */
@SuppressWarnings("rawtypes")
public class JotmFactoryBean implements FactoryBean, DisposableBean {

	private Current jotmCurrent;

	private Jotm jotm;

	public JotmFactoryBean() throws NamingException {
		// Check for already active JOTM instance.
		this.jotmCurrent = Current.getCurrent();

		// If none found, create new local JOTM instance.
		if (this.jotmCurrent == null) {
			// Only for use within the current Spring context:
			// local, not bound to registry.
			this.jotm = new Jotm(true, false);
			this.jotmCurrent = Current.getCurrent();
		}
	}

	public void setDefaultTimeout(int defaultTimeout) {
		this.jotmCurrent.setDefaultTimeout(defaultTimeout);
		// The following is a JOTM oddity: should be used for demarcation
		// transaction only,
		// but is required here in order to actually get rid of JOTM's default
		// (60 seconds).
		try {
			this.jotmCurrent.setTransactionTimeout(defaultTimeout);
		} catch (SystemException ex) {
			// should never happen
		}
	}

	public Jotm getJotm() {
		return this.jotm;
	}

	public Object getObject() {
		return this.jotmCurrent;
	}

	public Class getObjectType() {
		return this.jotmCurrent.getClass();
	}

	public boolean isSingleton() {
		return true;
	}

	public void destroy() {
		if (this.jotm != null) {
			this.jotm.stop();
		}
	}

}
