package rpc.server;

import org.apache.hadoop.ipc.VersionedProtocol;

public interface MyBusiness extends VersionedProtocol{

	//ָ��һ���汾�ţ�ʹ������汾�Ŵ���ǩ��
	public static long versionID = 1;
	
	//����ҵ�񷽷�
	public String sayHello(String name);
}
