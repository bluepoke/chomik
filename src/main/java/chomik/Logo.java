package chomik;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Logo {

	public static String getLogo() {
		String logo = "CHOMIK";
		InputStream stream = Logo.class.getResourceAsStream("logo.ascii");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[1024];
			int length;
			while ((length = stream.read(buffer)) != -1) {
				baos.write(buffer, 0, length);
			}
			logo = baos.toString();
		} catch (IOException e) {
			log.error("Could not read logo from resource.", e);
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				log.error("Could not close logo inputstream.", e);
			}
			try {
				baos.close();
			} catch (IOException e) {
				log.error("Could not close logo outputstream.", e);
			}
		}
		return logo;
	}
}
