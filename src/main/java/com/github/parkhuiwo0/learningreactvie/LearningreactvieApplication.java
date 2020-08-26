package com.github.parkhuiwo0.learningreactvie;

import com.github.parkhuiwo0.learningreactvie.echo.client.EchoClient;
import com.github.parkhuiwo0.learningreactvie.echo.server.EchoServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearningreactvieApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(LearningreactvieApplication.class, args);
		EchoServer echoServer = new EchoServer(123);
		EchoClient echoClient = new EchoClient("127.0.0.1", 123);
		echoServer.start();
		echoClient.start();
	}



}
