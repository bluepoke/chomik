package chomik.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultCallback implements MqttCallback {

	@Override
	public void connectionLost(Throwable cause) {
		log.error("Connection lost!");
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		log.info("Message arrived: topic={}, message={}", topic, message);
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		log.info("Delivery complete: {}", token.getMessageId());
	}

}
