package mx.uv.practica11;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@SpringBootApplication
public class Practica11Application {

	Color color = new Color();
	List<Color> Colores = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(Practica11Application.class, args);
	}

	//API relacionada a los colores


	//curl -X PUT -d @color.json -H "Content-Type: application/json" http://localhost:8080/modificarColor
@RequestMapping(method = RequestMethod.PUT, value = "/modificarColor")
	String cambiarColor(@RequestBody Color objeto){
		System.out.println(objeto);
		color = objeto;
		return "Color cambiado";
	}

	//curl -X DELETE http://localhost:8080/color/99
@RequestMapping(method = RequestMethod.DELETE, value = "/eliminarColor/{id}")
	Color eliminarColor(@PathVariable String id){
		System.out.println(id);
		color = null;
		return color;
	}

	//curl -X GET http://localhost:8080/color?nombreColor=blanco&valor=255
	//
@RequestMapping(method = RequestMethod.GET, value = "/listarColor")
	//List<Color> listarColores(@RequestParam(required = false) String id){
	List<Color> listarColores(@RequestParam(required = false) String id){	
		if (id !="") {
			List<Color> colores = new ArrayList<>();
			colores.add(this.Colores.get(Integer.parseInt(id)));
			return colores;

			//return (List<Color>) Colores.get(Integer.parseInt(id));
			
		}
		//System.out.println(nombreColor);
		//System.out.println(valor);
		//color.setNombre(nombreColor);
		//color.setValorColor(valor);
		
		return Colores;
	}

	//curl -X POST -d @color.json http://localhost
	//este metodo recibe objetos de tipo Content-Type Application/json
@RequestMapping(method = RequestMethod.POST, value = "/crearColor")	
	//String crearColores(@RequestBody Color color){
	Object crearColores(@RequestParam Color color){
		String id=UUID.randomUUID().toString();
		color.setId(id);
		/* color.setId("1");
		color.setNombre("Blanco");
		color.setValorColor("255"); */
		Colores.add(color);
		System.out.println(color.getNombre());
		Properties resultado = new Properties();
		resultado.setProperty("id", id);
		resultado.setProperty(id , id);
		return "Color creado";
	}

}
