package chomik.mqtt;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerConnector {


	public static MqttClient connectClient() throws MqttException, FileNotFoundException, IOException {
		log.debug("Reading connection.properties");
		Properties connectionProps = new Properties();
		InputStream inputStream = ServerConnector.class.getResourceAsStream("connection.properties");
		connectionProps.load(inputStream);
		
		String serverURI = connectionProps.getProperty("server.uri");

		log.debug("ServerURI={}", serverURI);
		String clientId = MqttClient.generateClientId();
		log.debug("clientId={}", clientId);
		MqttClient client = new MqttClient(serverURI, clientId);
		client.setCallback(new DefaultCallback());
		MqttConnectOptions options = new MqttConnectOptions();
		String userName = connectionProps.getProperty("username");
		String password = connectionProps.getProperty("password");
		if (userName != null && password != null) {
			log.debug("Setting username and password");
			options.setUserName(userName);
			options.setPassword(password.toCharArray());
		}
		log.debug("Establishing connection");
		client.connect(options);
		return client;
	}
}
