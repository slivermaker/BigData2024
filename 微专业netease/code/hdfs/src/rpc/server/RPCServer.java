package rpc.server;

import java.io.IOException;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Server;

public class RPCServer {

	public static void main(String[] args) throws Exception {
		// ����һ��RPC Server
		RPC.Builder builder = new RPC.Builder(new Configuration());
		
		//ָ��RPC Server �ĵ�ַ�Ͷ˿�
		builder.setBindAddress("localhost");
		builder.setPort(7788);
		
		//�����Լ��ĳ���
		builder.setProtocol(MyBusiness.class); //�ӿ�
		builder.setInstance(new MyBusinessImpl());//ʵ����
		
		//����RPCServer
		Server server = builder.build();
		//����Server
		server.start();
	}
}
