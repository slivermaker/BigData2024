package rpc.client;

import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import rpc.server.MyBusiness;

public class RPCClient {

	public static void main(String[] args) throws Exception {
		// 调用RPCServer的功能
		MyBusiness proxy = RPC.getProxy(MyBusiness.class,       //调用的接口
										 MyBusiness.versionID,         //版本号，必须跟Server一致
										 new InetSocketAddress("localhost", 7788), 
										 new Configuration());
		String result = proxy.sayHello("Tom");
		System.out.print(result);
	}

}
