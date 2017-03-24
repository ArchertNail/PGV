package pgv.proyecto.escritores.lectores;

import java.io.IOException;
import java.util.concurrent.Semaphore;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class LectoresEscritoresController {
	
	@FXML
	private TextField nHilosText,probabilidadText;
	
	@FXML 
	private ListView<String> zonaEspera,zonaTarea;
	
	
	private BorderPane view;
	private Zonas zonas;
	private int idLector=0;
	private int idEscritor=0;
	private Semaphore barreraLector,barreraEscritor;
	private LightSwitch escritorLight, lectorLight;
	public LectoresEscritoresController() {
		
		//barrera = new Semaphore(1);
		barreraLector = new Semaphore(1);
		barreraEscritor =  new Semaphore(1);
		escritorLight = new LightSwitch();
		lectorLight = new LightSwitch();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LectoresEscritoresView.fxml"));
			loader.setController(this);
			view = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void onPlayProcesoAction(){
		int numHilos =Integer.valueOf(nHilosText.getText());
		Double probabilidad = Double.valueOf(probabilidadText.getText());
		
		Thread aux;
		for (int hilo = 0; hilo < numHilos; hilo++) {
			if(Math.random()<probabilidad){
				aux=new Lector(lectorLight,barreraLector,barreraEscritor,zonas);
				aux.setName("LECTOR:"+idLector++);
			}
			else{
				aux=new Escritor(escritorLight,barreraLector,barreraEscritor,zonas);
				aux.setName("ESCRITOR:" + idEscritor++);
			}
		zonas.getZonaEspera().add(aux.getName());
			aux.start();
		}
	}
	
	public void bind(Zonas zonas) {
		this.zonas = zonas;
		zonaEspera.itemsProperty().bind(zonas.zonaEsperaProperty());
		zonaTarea.itemsProperty().bind(zonas.zonaTareaProperty());
		
		
	}
	
	
	public BorderPane getView() {
		return view;
	}

	
}
