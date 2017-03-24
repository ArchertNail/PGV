package pgv.proyecto.escritores.lectores;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EscritoresLectoresAPP extends Application{

	private LectoresEscritoresController controller;
	private Zonas zonas;
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		zonas = new Zonas();
		
		controller = new LectoresEscritoresController();
		controller.bind(zonas);
		primaryStage.setTitle("Escritores y Lectores");
		primaryStage.setScene(new Scene(controller.getView(),620,440));
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
