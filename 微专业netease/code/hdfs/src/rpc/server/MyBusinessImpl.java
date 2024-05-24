package rpc.server;

import java.io.IOException;

import org.apache.hadoop.ipc.ProtocolSignature;

public class MyBusinessImpl implements MyBusiness {
	
	@Override
	public String sayHello(String name) {
		System.out.println("���õ���Server");
		return "Hello " + name;
	}

	@Override
	public ProtocolSignature getProtocolSignature(String arg0, long arg1, int arg2) throws IOException {
		// ����һ��ǩ������Ϣ
		return new ProtocolSignature(MyBusiness.versionID,null);
	}

	@Override
	public long getProtocolVersion(String arg0, long arg1) throws IOException {
		// ����ǩ����ID
		return MyBusiness.versionID;
	}

}
