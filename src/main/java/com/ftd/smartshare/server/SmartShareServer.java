package com.ftd.smartshare.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.atomic.AtomicInteger;

public class SmartShareServer {
	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(3000)) {
			while (true) {
				new Thread(new ClientHandler(serverSocket.accept())).start();
			}
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
}