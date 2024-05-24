package rpc.client;

import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import rpc.server.MyBusiness;

public class RPCClient {

	public static void main(String[] args) throws Exception {
		// ����RPCServer�Ĺ���
		MyBusiness proxy = RPC.getProxy(MyBusiness.class,       //���õĽӿ�
										 MyBusiness.versionID,         //�汾�ţ������Serverһ��
										 new InetSocketAddress("localhost", 7788), 
										 new Configuration());
		String result = proxy.sayHello("Tom");
		System.out.print(result);
	}

}
