package rpc.server;

import java.io.IOException;

import org.apache.hadoop.ipc.ProtocolSignature;

public class MyBusinessImpl implements MyBusiness {
	
	@Override
	public String sayHello(String name) {
		System.out.println("调用到了Server");
		return "Hello " + name;
	}

	@Override
	public ProtocolSignature getProtocolSignature(String arg0, long arg1, int arg2) throws IOException {
		// 返回一个签名的信息
		return new ProtocolSignature(MyBusiness.versionID,null);
	}

	@Override
	public long getProtocolVersion(String arg0, long arg1) throws IOException {
		// 返回签名的ID
		return MyBusiness.versionID;
	}

}
