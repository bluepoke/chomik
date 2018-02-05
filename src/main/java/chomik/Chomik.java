package chomik;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import chomik.mqtt.ServerConnector;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Chomik {
	
	private static final String ASCII_ART_LOGO =	" _______  __   __  _______  __   __  ___   ___   _ \r\n" + 
													"|       ||  | |  ||       ||  |_|  ||   | |   | | |\r\n" + 
													"|       ||  |_|  ||   _   ||       ||   | |   |_| |\r\n" + 
													"|       ||       ||  | |  ||       ||   | |      _|\r\n" + 
													"|      _||       ||  |_|  ||       ||   | |     |_ \r\n" + 
													"|     |_ |   _   ||       || ||_|| ||   | |    _  |\r\n" + 
													"|_______||__| |__||_______||_|   |_||___| |___| |_|"; // generated at http://patorjk.com/software/taag/#p=display&f=Modular&t=chomik

	private static final String ASCII_ART_IMAGE = 	"        _           _\r\n" + 
													"      (`-`;-\"```\"-;`-`)\r\n" + 
													"       \\.'         './\r\n" + 
													"       /             \\\r\n" + 
													"       ;   0     0   ;\r\n" + 
													"      /| =         = |\\\r\n" + 
													"     ; \\   '._Y_.'   / ;\r\n" + 
													"    ;   `-._ \\|/ _.-'   ;\r\n" + 
													"   ;        `\"\"\"`        ;\r\n" + 
													"   ;    `\"\"-.   .-\"\"`    ;\r\n" + 
													"   /;  '--._ \\ / _.--   ;\\\r\n" + 
													"  :  `.   `/|| ||\\`   .'  :\r\n" + 
													"   '.  '-._       _.-'   .'\r\n" + 
													"   (((-'`  `\"\"\"\"\"`   `'-)))";
	
	public static void main(String[] args) throws FileNotFoundException, MqttException, IOException {
		
		System.out.println(ASCII_ART_IMAGE);
		System.out.println();
		System.out.println(ASCII_ART_LOGO);
		System.out.println();
		
		MqttClient client = ServerConnector.connectClient();
		client.setCallback(new MqttCallback() {
			
			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				log.info("{} <= {}", topic, new String(message.getPayload()));
			}
			
			@Override
			public void deliveryComplete(IMqttDeliveryToken token) {
				
			}
			
			@Override
			public void connectionLost(Throwable cause) {
				log.error("Connection lost!");
			}
		});
		String topic = "zanzara/#";
		log.debug("Subscribing to {}", topic);
		client.subscribe(topic);
	}

}
