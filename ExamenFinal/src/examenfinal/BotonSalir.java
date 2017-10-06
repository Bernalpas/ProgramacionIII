
package examenfinal;

import com.itextpdf.text.Font;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/*
 * @author Bernal Pas
 */
public class BotonSalir extends WindowEvent{
    
        static boolean respuesta;
        Font verso;
                       
    public static boolean Ventana(){
        
        Stage venta = new Stage();
        venta.initModality(Modality.APPLICATION_MODAL);
        venta.setTitle("Confirmar");
                
        Label texto = new Label();
        texto.setText("¿Desea salir del Programa?");
                
        Button si = new Button("Si");
        Button no = new Button("No");
        
        si.setOnAction(e -> {
            respuesta = true;
            venta.close();
        });
        
        no.setOnAction(e -> {
            respuesta = false;
            venta.close();
        });
        
        HBox diseño = new HBox(10);
        diseño.getChildren().add(texto);
        diseño.getChildren().add(si);
        diseño.getChildren().add(no);
        diseño.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(diseño, 300, 50);
        venta.setScene(scene);
        venta.initStyle(StageStyle.UNIFIED);
        venta.showAndWait();
        
        return respuesta;
        
    }

    public BotonSalir(Window source, EventType<? extends Event> eventType) {
        super(source, eventType);  
            
    }

    
}
