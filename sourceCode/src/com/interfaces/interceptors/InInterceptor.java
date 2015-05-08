/**
* @Title: InInterceptor.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.interfaces.interceptors   
* @Description: 
* @author gaoguangchao    
* @date 2014年7月3日 下午2:31:00   
* @version V1.0 
*/

package com.interfaces.interceptors;


import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.message.Message;

/**
 * @ClassName: InInterceptor
 * @Description: 
 * @author gaoguangchao
 * @date 2014年7月3日 下午2:31:00
 *
 */

public class InInterceptor extends AbstractPhaseInterceptor<Message>{
	//至少要一个带参的构造函数
    public InInterceptor(String phase) {
        super(phase);
    }
    public InInterceptor()
	{
		super(Phase.PRE_INVOKE);
	}
	@Override
	public void handleMessage(Message message) throws Fault {
		System.out.println("############handleMessage##########");
        System.out.println(message);
        if (message.getDestination() != null) {
            System.out.println(message.getId() + "#" + message.getDestination().getMessageObserver());
        }
        if (message.getExchange() != null) {
            System.out.println(message.getExchange().getInMessage() + "#" + message.getExchange().getInFaultMessage());
            System.out.println(message.getExchange().getOutMessage() + "#" + message.getExchange().getOutFaultMessage());
        }
		
	}

}
