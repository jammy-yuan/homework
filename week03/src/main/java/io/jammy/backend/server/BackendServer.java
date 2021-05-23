package io.jammy.backend.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

// 创建了一个固定大小的线程池处理请求
public class BackendServer {
	private static int port = 8088;
	
	// 运行标志位
    public static final AtomicBoolean FLAG = new AtomicBoolean(true);
	
    public static void process(String[] args) throws IOException{

    	final ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() + 2);
        final ServerSocket serverSocket = new ServerSocket(port);
        while (FLAG.get()) {
            try {
                final Socket socket = serverSocket.accept();
                executorService.execute(() -> service(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        //close all resources
        serverSocket.close();
        executorService.shutdown();
    }
    
    private static void service(Socket socket) {
    	InputStream in = null;
    	ByteArrayOutputStream out = null;
    	try {
    		// get request content
			in = socket.getInputStream();
			byte[] b = new byte[in.available()];
			int length = in.read(b);
			out = new ByteArrayOutputStream();
			out.write(b, 0, length);
			byte[] outBuffer = out.toByteArray();
			
			// print out the request content
			System.out.println("request content: " + outBuffer.toString());
		} catch (IOException e1) {
			System.out.println("Can not get request content");
		} finally {
			try {
				if(in != null) {
					in.close();
				}
				if(out != null) {
					out.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
    	
        try {
//            Thread.sleep(5);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,nio";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
        } catch (IOException e) { // | InterruptedException e) {
            e.printStackTrace();
        }
    }
}