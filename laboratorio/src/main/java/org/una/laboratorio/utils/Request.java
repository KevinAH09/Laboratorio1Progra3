
package org.una.laboratorio.utils;

import java.util.Map;
import javafx.scene.control.Alert;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
/**
 *
 * @author ccarranza
 */
public class Request {

    private Client client;
    private Invocation.Builder builder;
    private WebTarget webTarget;
    private Response response;

    public Request() {
        this.client = ClientBuilder.newClient();
    }

    public Request(String target) {
        this.client = ClientBuilder.newClient();
        setTarget(target);
    }

    public Request(String target, String parametros, Map<String, Object> valores) {
        this.client = ClientBuilder.newClient();
        this.webTarget = client.target(target).path(parametros).resolveTemplates(valores);
        this.builder = webTarget.request(MediaType.APPLICATION_JSON);
        MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
        headers.add("Content-Type", "application/json; charset=UTF-8");
//        if(AppContext.getInstance().get("Token") != null){
//            headers.add("Authorization", AppContext.getInstance().get("Token").toString());
//        }
        builder.headers(headers);
        
          
    }

    /**
     * Ingresa el objetivo de la petición
     *
     * @param target Objetivo de la petición
     */
    public void setTarget(String target) {
        this.webTarget = client.target(target);
        this.builder = webTarget.request(MediaType.APPLICATION_JSON);
        MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
        headers.add("Content-Type", "application/json; charset=UTF-8");        
//        if(AppContext.getInstance().get("Token") != null){
//            headers.add("Authorization", AppContext.getInstance().get("Token").toString());
//        }
        builder.headers(headers);
    }

    public void setHeader(String nombre, Object valor) {
        builder.header(nombre, valor);
    }

    public void setHeader(MultivaluedMap<String, Object> valores) {
        valores.add("Content-Type", "application/json; charset=UTF-8");
        builder.headers(valores);
    }

    public void get() {        
        response = builder.get();
    }

    public void post(Object clazz) {
        Entity<?> entity = Entity.entity(clazz, "application/json; charset=UTF-8");
        response = builder.post(entity);
    }

    public void put(Object clazz) {
        Entity<?> entity = Entity.entity(clazz, "application/json; charset=UTF-8");
        response = builder.put(entity);
    }

    public void delete() {
        response = builder.delete();
    }

    public int getStatus() {        
        if(response.getStatus() == 401){
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", null, "Su secion ha expirado, ingrese nuevamente");
//            FlowController.getInstance().goLogIn("LogIng2");
        }
        return response.getStatus();
    }
    
    public Boolean isError(){
        return getStatus() != 200;
    }
  
    public String getError() {
        if (response.getStatus() != 200) {
            String mensaje;
            if (response.getMediaType().equals(MediaType.APPLICATION_JSON_TYPE)) {
                mensaje = response.readEntity(String.class);
            } else {
                mensaje = response.getStatusInfo().getReasonPhrase();
            }
            return mensaje;
        }
        return null;
    }
    
    public Object readEntity(Class clazz) {
        return response.readEntity(clazz);
    }
    
    public Object readEntity(GenericType<?> genericType) {
        return response.readEntity(genericType);
    }
 
}
