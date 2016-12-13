package com.jzctb.mq;

import com.ibm.mq.MQException;

public class MQRecv extends MQConnector {
	public MQRecv()
	{

	}

	public String getMessages(String[] args) throws MQException
	{
		String message=getMessageFromQueue();
		return message;
	}

	public String recv() throws MQException{
		initMq();
	    openQueue(RecvQueue);
	    String message = getMessageFromQueue();
		if(message!=null)
		{
			logger.debug("message is: "+message);      
		}

		return message;
	}
	
	public static void main(String[] args)
	{
		MQRecv mqget = new MQRecv();
		MQConnector.DEBUG=true;
		try
		{
			mqget.initMq();
			mqget.openQueue("PBC.313501000006.ONLINE.OUT");

			String msg=mqget.getMessages(args);
			if(msg!=null)
			{
				System.out.println("msg is: "+msg);      
			}

			mqget.closeQueue();
			//mqget.disconnectMq();      
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Usage: "+mqget.getClass().getName()+" ");
		}
	}
}
