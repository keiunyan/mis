package com.jzctb.mq;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.constants.CMQC;

/**
 * 身份证联网核查
 * @author YXS
 *
 */
public class IDVerification extends MQConnector {

	public IDVerification(){
		
	}
	
	public void idv(String id,String name){
		
	}
	
	public void browser(){
		logger.debug("browser()--------------------------");
		String msg = get();
		logger.debug(msg);
	}
	
	private boolean init(){
		logger.debug("init()--------------------------");
		InputStream fis = getClass().getResourceAsStream("/com/jzctb/mq/mq.properties");
		Properties props = new Properties();
		try {
			props.load(fis);
			fis.close();
			String host = props.getProperty("picp.host");
			String port = props.getProperty("picp.port");
			String qName = props.getProperty("picp.manage");
			String channel = props.getProperty("picp.channel");
			SendQueue = props.getProperty("picp.SendQueue");
			RecvQueue = props.getProperty("picp.RecvQueue");

			initMq(host,port,qName,channel);
			return true;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private String get(){
		if(!init())
			return null;
		logger.debug("get()--------------------------");
		
		String msg = "";
		try {
			int openOptions = CMQC.MQOO_FAIL_IF_QUIESCING | CMQC.MQOO_INPUT_AS_Q_DEF|CMQC.MQOO_BROWSE;
			mqQueue = qMgr.accessQueue(RecvQueue, openOptions);

			MQMessage mqMsg = new MQMessage();
			MQGetMessageOptions gmo = new MQGetMessageOptions();
			gmo.options         = CMQC.MQGMO_WAIT | CMQC.MQGMO_ACCEPT_TRUNCATED_MSG;
			gmo.matchOptions    = CMQC.MQMO_NONE;//CMQC.MQMO_MATCH_CORREL_ID;// .MQMO_MATCH_MSG_ID;
			mqMsg.messageId     = CMQC.MQMI_NONE;
			mqMsg.correlationId = CMQC.MQCI_NONE;
	
			// Get a message from the queue
			mqQueue.get(mqMsg, gmo);
	
			// Extract the message data
			int len = mqMsg.getDataLength();
			byte[] message = new byte[len];
			mqMsg.readFully(message, 0, len);
			return new String(message);
			
		} catch (MQException mqe) {
			int reason = mqe.reasonCode;

			if (reason == 2033)// no messages
			{
				logger.debug("当前队列中没有消息");
				return null;
			} else {
				logger.error("读取消息失败！");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}
	
	private void put(String msg){
		
	}
}
