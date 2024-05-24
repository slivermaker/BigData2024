package rpc.server;

import java.io.IOException;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Server;

public class RPCServer {

	public static void main(String[] args) throws Exception {
		// 创建一个RPC Server
		RPC.Builder builder = new RPC.Builder(new Configuration());
		
		//指定RPC Server 的地址和端口
		builder.setBindAddress("localhost");
		builder.setPort(7788);
		
		//发布自己的程序
		builder.setProtocol(MyBusiness.class); //接口
		builder.setInstance(new MyBusinessImpl());//实现类
		
		//创建RPCServer
		Server server = builder.build();
		//启动Server
		server.start();
	}
}
