
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@ManagedBean(name = "consumo")
@SessionScoped
public class BeanConsumo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Respuesta[] listadoRespuesta;

	public Respuesta[] getListadoRespuesta() {
		return listadoRespuesta;
	}

	public void setListadoRespuesta(Respuesta[] listadoRespuesta) {
		this.listadoRespuesta = listadoRespuesta;
	}
	public String consumirserviciobackend() {
		final ClientConfig clientConfig = new DefaultClientConfig();
		final Client client = Client.create(clientConfig);
		final WebResource webResource = client
				.resource(UriBuilder.fromUri("http://localhost:8081").build());
		System.out.println(webResource.path("/Index").accept(MediaType.APPLICATION_JSON).get(String.class));
		String respuesta = webResource.path("/Index").accept(MediaType.APPLICATION_JSON).get(String.class);

		Gson gson = new Gson();
		listadoRespuesta = gson.fromJson(respuesta, Respuesta[].class);
		return "ok";
	}
}
