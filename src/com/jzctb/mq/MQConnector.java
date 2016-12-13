package com.jzctb.mq;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.MQException;
import com.ibm.mq.constants.CMQC;

public class MQConnector {
	protected String qManagerHost = ""; // MQ服务器地址
	protected String qManagerPort = ""; // MQ服务器端口
	protected String qManager = ""; // 队列管理器名称
	protected String queuName = ""; // define name of queue
	protected String SendQueue = ""; // 发送队列
	protected String RecvQueue = ""; // 接收队列
	protected String channel = ""; // 连接通道

	protected MQQueue mqQueue;
	protected MQQueueManager qMgr = null;

	public static boolean DEBUG = true;

	public MQConnector() {

	}
	
	public void initMq(String host,String port,String qName,String channel){
		MQEnvironment.hostname = host;
		MQEnvironment.port = Integer.parseInt(port);
		MQEnvironment.channel = channel;
		
		try {
			qMgr = new MQQueueManager(qName);
		} catch (MQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initMq() {
		try {
			InputStream fis = getClass().getResourceAsStream(
					"/com/jzctb/mq/mq.properties"); // new
															// FileInputStream(new
															// File("/mq.properties"));
			Properties props = new Properties();
			props.load(fis);
			fis.close();
			qManager = props.getProperty("queue.manager");
			qManagerHost = props.getProperty("queue.manager.host");
			qManagerPort = props.getProperty("queue.manager.port");
			queuName = props.getProperty("queue.name");
			SendQueue = props.getProperty("SendQueue");
			RecvQueue = props.getProperty("RecvQueue");
			channel = props.getProperty("channel");
			logger.debug(qManager + "+" + qManagerHost + "+" + qManagerPort
					+ "+" + queuName + "+" + SendQueue + "+" + RecvQueue + "+"
					+ channel);

			// Create a connection to the queue manager
			MQEnvironment.channel = channel;
			MQEnvironment.hostname = qManagerHost;
			MQEnvironment.port = Integer.parseInt(qManagerPort);
			MQEnvironment.userID = "mqm";
			MQEnvironment.CCSID = 819;

			logger.debug("Connecting to QueueManager " + qManager + " on "
					+ qManagerHost);
			qMgr = new MQQueueManager(qManager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openQueue() throws MQException {
		// Set up the options on the queue we wish to open...
		// Note. All WebSphere MQ Options are prefixed with MQC in Java.
		int openOptions = CMQC.MQOO_FAIL_IF_QUIESCING | CMQC.MQOO_OUTPUT
				| CMQC.MQOO_INPUT_AS_Q_DEF;
		// Now specify the queue that we wish to open,
		// and the open options...
		logger.debug("Opening queue: " + queuName);
		try {
			mqQueue = qMgr.accessQueue(queuName, openOptions);
		} catch (MQException mqe) {
			// check if MQ reason code 2045
			// means that opened queu is remote and it can not be opened as
			// input queue
			// try to open as output only
			if (mqe.reasonCode == 2045) { // 此队列只能写入消息
				openOptions = CMQC.MQOO_FAIL_IF_QUIESCING | CMQC.MQOO_OUTPUT;
				mqQueue = qMgr.accessQueue(queuName, openOptions);
			} else if (mqe.reasonCode == 2037) { // 此队列只能读取消息
				openOptions = CMQC.MQOO_FAIL_IF_QUIESCING | CMQC.MQOO_INPUT_AS_Q_DEF;
				mqQueue = qMgr.accessQueue(queuName, openOptions);
			} else {
				logger.debug("打开队列失败：" + queuName);
			}
		}
	}

	/**
	 * 打开指定的队列
	 * 
	 * @param q
	 *            - 队列名称
	 * @throws MQException
	 */
	public void openQueue(String q) throws MQException {
		// Set up the options on the queue we wish to open...
		// Note. All WebSphere MQ Options are prefixed with MQC in Java.
		int openOptions = CMQC.MQOO_FAIL_IF_QUIESCING | CMQC.MQOO_OUTPUT
				| CMQC.MQOO_INPUT_AS_Q_DEF;
		
		// Now specify the queue that we wish to open,
		// and the open options...
		logger.debug("Opening queue: " + q);
		try {
			mqQueue = qMgr.accessQueue(q, openOptions);
		} catch (MQException mqe) {
			// check if MQ reason code 2045
			// means that opened queu is remote and it can not be opened as
			// input queue
			// try to open as output only
			if (mqe.reasonCode == 2045) { // 此队列只能写入消息
				logger.debug("2045");
				openOptions = CMQC.MQOO_FAIL_IF_QUIESCING | CMQC.MQOO_OUTPUT;
				mqQueue = qMgr.accessQueue(q, openOptions);
			} else if (mqe.reasonCode == 2037) { // 此队列只能取出消息
				logger.debug("2037");
				openOptions = CMQC.MQOO_FAIL_IF_QUIESCING | CMQC.MQOO_INPUT_AS_Q_DEF;
				mqQueue = qMgr.accessQueue(q, openOptions);
			} else {
				logger.debug("打开队列失败：" + q);
			}
		}
	}

	public void putMessageToQueue(String msg) throws MQException {
		try {
			logger.debug("Sending message: " + msg);

			MQPutMessageOptions pmo = new MQPutMessageOptions();
			MQMessage mqMsg = new MQMessage();
			mqMsg.write(msg.getBytes());

			// put the message on the queue
			mqQueue.put(mqMsg, pmo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getMessageFromQueue() throws MQException {
		try {
			MQMessage mqMsg = new MQMessage();

			MQGetMessageOptions gmo = new MQGetMessageOptions();
			gmo.options = CMQC.MQGMO_WAIT | CMQC.MQGMO_ACCEPT_TRUNCATED_MSG;
			//gmo.matchOptions = CMQC.MQMO_MATCH_CORREL_ID;// .MQMO_MATCH_MSG_ID;
			mqMsg.messageId = CMQC.MQMI_NONE;
			mqMsg.correlationId = CMQC.MQCI_NONE;//  "11111".getBytes("GBK");

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
				throw mqe;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void closeQueue() throws MQException {
		logger.debug("Closing queue and disconnecting QueueManager...");

		// Close the queue...
		mqQueue.close();
		// Disconnect from the queue manager
		qMgr.disconnect();
	}

	protected boolean hasArg(String arg, String[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals(arg)) {
				return true;
			}
		}
		return false;
	}

	protected static Logger logger = Logger.getLogger(MQConnector.class);
}
