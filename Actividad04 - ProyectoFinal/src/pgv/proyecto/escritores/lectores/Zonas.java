package pgv.proyecto.escritores.lectores;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Zonas {

	private ListProperty<String> zonaEspera,zonaTarea;
	
	public Zonas(){
		zonaEspera = new SimpleListProperty<>(this, "zonoaEspera", FXCollections.observableArrayList());
		zonaTarea = new SimpleListProperty<>(this, "zonaTarea", FXCollections.observableArrayList());
	}

	public final ListProperty<String> zonaEsperaProperty() {
		return this.zonaEspera;
	}
	

	public final ObservableList<String> getZonaEspera() {
	return this.zonaEsperaProperty().get();
	}
	

	public final  void setZonaEspera(final ObservableList<String> zonaEspera) {
	this.zonaEsperaProperty().set(zonaEspera);
	}
	

	public final ListProperty<String> zonaTareaProperty() {
		return this.zonaTarea;
	}
	

	public final ObservableList<String> getZonaTarea() {
	return this.zonaTareaProperty().get();
	}
	

	public final  void setZonaTarea(final ObservableList<String> zonaTarea) {
	this.zonaTareaProperty().set(zonaTarea);
	}
	
	
	
}
