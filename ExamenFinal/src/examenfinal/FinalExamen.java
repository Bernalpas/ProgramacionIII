
package examenfinal;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/*
 * @author Bernal Pas
 */

public class FinalExamen extends Application {
    
    //Defino los atributos principales de la clase
    private static FileWriter fichero;
    private static PrintWriter pw;
    private static FileReader fr;
    private static BufferedReader br;
    private final Toolkit sonido;
    private HBox botones;
    int i = -1;
    int opciones;
    ArrayList<String> arrayresp;
    TextField archivar1;
        
    public FinalExamen(){//Constructor de la clase - método de sonido al abrir la ventana
        
        sonido = Toolkit.getDefaultToolkit();
        sonido.beep();
    }
   
    
    @Override
    public void start(final Stage primaryStage) {
        
        //creo los componentes de la escena principal y los ubico
        final Button profesor = new Button();
        profesor.setLayoutX(180);
        profesor.setLayoutY(250);
        profesor.setText("Profesor");
        
        final Button alumno = new Button();
        alumno.setLayoutX(350);
        alumno.setLayoutY(250);
        alumno.setText("Alumno");
        
               
        final Text menu = new Text();
        menu.setLayoutX(230);
        menu.setLayoutY(150);
        menu.setText("MENU PRINCIPAL DEL SISTEMA");
        
        final Text menu1 = new Text("INSTITUTO DE EDUCACIÓN SUPERIOR 9 - 024 LAVALLE");
        menu1.setFill(Color.DARKBLUE);
        menu1.setLayoutX(150);
        menu1.setLayoutY(330);
                
        //Creo los componentes del Menu y sus respectivos ítems
        final Menu menu4 = new Menu("Archivo");
        final Menu menu5 = new Menu("Opciones");
        final Menu menu6 = new Menu("Ayuda");
        final Menu menu7 = new Menu("                              ");
        final Menu menu8 = new Menu("                              ");
        final Menu menu9 = new Menu("                              ");
        final Menu menu10 = new Menu("                  ");
        
        MenuItem nuevo = new MenuItem("Nuevo");
        MenuItem save = new MenuItem("Guardar");
        MenuItem salir = new MenuItem("Salir");
        
        //Agrego el evento al menú item salir
        salir.setOnAction((ActionEvent e) -> {
            boolean prueba;
            prueba = BotonSalir.Ventana();
            System.out.println(prueba);
            
            if(prueba){
                primaryStage.close(); 
                System.exit(0);
            }else{
                primaryStage.show();
            }
        });
        
        MenuItem conexion = new MenuItem("Conectar");
        MenuItem enviar = new MenuItem("Enviar");
        MenuItem foto = new MenuItem("Foto");
        
        MenuItem sobre = new MenuItem("Manual");
        
        //Agrego los ítems a cada componente del menú
        menu4.getItems().addAll(nuevo,save,salir);
        menu5.getItems().addAll(conexion, enviar, foto);
        menu6.getItems().add(sobre);
        
        //Creo el menú bar y le agrego los componentes
        MenuBar menubar = new MenuBar();
        menubar.getMenus().addAll(menu4, menu5, menu6); 
        menubar.getMenus().addAll(menu7, menu8, menu9, menu10);
        
        //Creo una caja horizontal y agrego el menubar
        botones = new HBox(5);
        botones.getChildren().add(menubar);
        
        //Creo un Panel de borde y le agrego los componentes del menú
        BorderPane root = new BorderPane();
        root.setTop(botones);
        
        //Creo un Panel y le agrego un color para resaltar el título del sistema
        StackPane root1 = new StackPane();
        root1.setLayoutX(100);
        root1.setLayoutY(100);
        root1.setPrefSize(400, 100);
        root1.setStyle("-fx-background-color: Gainsboro;-fx-border-color: blue");
        root1.getChildren().add(menu);
        
        //Creo el grupo principal y agrego los elementos 
        final Group controlmenu = new Group();
        controlmenu.getChildren().add(root);
        controlmenu.getChildren().add(root1);
        controlmenu.getChildren().add(alumno);
        controlmenu.getChildren().add(profesor);
        controlmenu.getChildren().add(menu);
        controlmenu.getChildren().add(menu1);
        
        //Creo la escena y agrego el grupo con sus respectivas dimensiones 
        final Scene scene = new Scene(controlmenu, 600, 400);
        scene.setFill(Color.LIGHTBLUE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sistema de Evaluacion IES 9 - 024");
        
        //Creo un requerimiento de cierre de ventana para confirmar la salida
        primaryStage.setOnCloseRequest(e12 -> {
            e12.consume();
            cerrarVentana();
        }); 
         
        //Creo el evento al botón profesor 
        profesor.setOnAction((ActionEvent event) -> {
            
            //Instancio los arrays para almacenar las preguntas del profesor
            final ArrayList<String> lista = new ArrayList<>();
            final ArrayList<String> lista2 = new ArrayList<>();
            
            //Creo los componentes de la escena del Profesor y los ubico
            final Text docente = new Text();
            docente.setLayoutX(80);
            docente.setLayoutY(60);
            docente.setText("Profesor, ingrese su nombre: ");
            
            final TextField nombre = new TextField();
            nombre.setLayoutX(280);
            nombre.setLayoutY(40);
            nombre.setText("Escriba su nombre");
            
            final Text escribapreg = new Text();
            escribapreg.setLayoutX(80);
            escribapreg.setLayoutY(120);
            escribapreg.setText("Escriba una pregunta y 4 respuestas: ");
            
            final Text aclaracion = new Text();
            aclaracion.setLayoutX(300);
            aclaracion.setLayoutY(160);
            aclaracion.setText("La CUARTA RESPUESTA que Ud. ingrese, será marcada\n"
                    + " como la CORRECTA por el Sistema.");
            
            final Text archivar = new Text();
            archivar.setLayoutX(80);
            archivar.setLayoutY(360);
            archivar.setText("Para guardar, ingrese el NOMBRE del ARCHIVO: ");
            
            archivar1 = new TextField();
            archivar1.setLayoutX(350);
            archivar1.setLayoutY(340);
            archivar1.setText("Nombre del Archivo");
            
            final TextField preguntaprof = new TextField();
            preguntaprof.setLayoutX(320);
            preguntaprof.setLayoutY(100);
            preguntaprof.setText("Ingrese una pregunta");
            preguntaprof.setUserData("0");
            
            final TextField uno = new TextField();
            uno.setLayoutX(120);
            uno.setLayoutY(150);
            uno.setText("Ingrese una respuesta");
            uno.setUserData("1");
            
            final TextField dos = new TextField();
            dos.setLayoutX(120);
            dos.setLayoutY(200);
            dos.setText("Ingrese una respuesta");
            dos.setUserData("2");
            
            final TextField tres = new TextField();
            tres.setLayoutX(120);
            tres.setLayoutY(250);
            tres.setText("Ingrese una respuesta");
            tres.setUserData("3");
            
            final TextField cuatro = new TextField();
            cuatro.setLayoutX(120);
            cuatro.setLayoutY(300);
            cuatro.setText("Ingrese una respuesta");
            cuatro.setUserData("4");
            
            final Button guardar = new Button();
            guardar.setLayoutX(330);
            guardar.setLayoutY(250);
            guardar.setText("Guardar Pregunta");
            
            final Button inicio = new Button();
            inicio.setLayoutX(330);
            inicio.setLayoutY(300);
            inicio.setText("Inicio del Sistema");
            
            //Instancio el grupo y agrego los elementos
            final Group gprofesor = new Group();
            gprofesor.getChildren().add(escribapreg);
            gprofesor.getChildren().add(uno);
            gprofesor.getChildren().add(dos);
            gprofesor.getChildren().add(tres);
            gprofesor.getChildren().add(cuatro);
            gprofesor.getChildren().add(guardar);
            gprofesor.getChildren().add(inicio);
            gprofesor.getChildren().add(aclaracion);
            gprofesor.getChildren().add(archivar);
            gprofesor.getChildren().add(archivar1);
            gprofesor.getChildren().add(preguntaprof);
            gprofesor.getChildren().add(docente);
            gprofesor.getChildren().add(nombre);
            
            //Instancio la escena y sus dimensiones y color
            final Scene scene1 = new Scene(gprofesor, 600, 400);
            scene1.setFill(Color.LIGHTBLUE);
            primaryStage.setScene(scene1);
            
            //Agrego el evento al botón inicio
            inicio.setOnAction((ActionEvent event1) -> {
                primaryStage.setScene(scene);
            });
            
        //Creo el evento al botón guardar    
        guardar.setOnAction((ActionEvent event1) -> {
                           
                                               
                System.out.println("Su Pregunta se guardará para el examen.");
                                
                guardar.setOnAction((ActionEvent event2) -> {
                    
                    //Cargo el array con las preguntas del profesor
                    lista.add(preguntaprof.getText());
                    lista.add(uno.getText());
                    lista.add(dos.getText());
                    lista.add(tres.getText());
                    lista.add(cuatro.getText());
                    
                    //Cargo otro array para usarlo en el pdf y desordeno las respuestas
                    i = i + 1;
                                        
                    lista2.add(lista.get(5*i + 0));
                    lista2.add(lista.get(5*i + 1));
                    lista2.add(lista.get(5*i + 2));
                    lista2.add(lista.get(5*i + 3));
                    lista2.add(lista.get(5*i + 4));
                    Collections.shuffle(lista2.subList(5*i + 1, 5*i + 5));
                    
                    System.out.println(i);
                    
                    lista2.forEach((verDesorden) -> {
                        System.out.println(verDesorden);
                    });
                    
                    //Escribo las preguntas en un fichero txt y lo guardo en c
                    try{
                        fichero = new FileWriter("c:/" + archivar1.getText() + ".txt");
                        pw = new PrintWriter(fichero);
                        //pw.print("Examen de Programación II - Análisis de Sistemas");
                        //pw.println();
                        Date fecha = new Date();
                        fecha.getTime();
                        //pw.print(fecha);
                        //pw.println();
                        //pw.println();
                        lista.forEach((listas3) -> {
                            pw.println(listas3);
                        });
                        
                    } catch (IOException e1) {
                        System.out.println("El examen no se ha guardado correctamente");
                    }
                    
                    finally {
                        try {
                            if (null != fichero)
                                fichero.close();
                            preguntaprof.clear();//Borro las orintaciones al profesor 
                            uno.clear();
                            dos.clear();
                            tres.clear();
                            cuatro.clear();
                            
                        } catch (IOException e2) {
                        }
                    }
                    
                    //Escribo las preguntas del profesor en un archivo pdf, agregándole imagen y encabezado
                    try {
                        FileOutputStream pdf = new FileOutputStream("c:/" + archivar1.getText() + ".pdf");
                        Document doc = new Document(PageSize.A4);
                        PdfWriter.getInstance(doc, pdf);
                        doc.open();
                        try {
                            Image ies = Image.getInstance("src/examenfinal/Logo IES.jpg");
                            ies.scaleAbsolute(60, 60);
                            ies.setAlignment(Element.ALIGN_LEFT);
                            doc.add(ies);
                        } catch (BadElementException ex) {
                        } catch (IOException ex) {
                            System.out.println("No se encuentra la Imagen");
                        }
                        Date fecha = new Date();
                        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
                        df.format(fecha);
                        Paragraph g = new Paragraph();
                        Paragraph p = new Paragraph();
                        Paragraph m = new Paragraph();
                        Paragraph n = new Paragraph();
                        Paragraph o = new Paragraph();
                        Paragraph h = new Paragraph();
                        g.setFont(new Font(FontFactory.getFont("Courier", 12, Font.BOLD, BaseColor.BLUE)));
                        g.add("IES 9 - 024 LAVALLE");
                        g.setAlignment(Paragraph.ALIGN_CENTER);
                        p.add("Examen de Programación II - Análisis de Sistemas");
                        p.setAlignment(Paragraph.ALIGN_CENTER);
                        m.setFont(new Font(FontFactory.getFont("Candara", 10, BaseColor.RED)));
                        m.setAlignment(Paragraph.ALIGN_CENTER);
                        m.add("La fecha del examen es: " + fecha);
                        o.add("Profesor/a: " + nombre.getText());
                        o.setAlignment("Center");
                        n.add("Nombre del Alumno/a:  ");
                        n.setAlignment("Left");
                        h.add(" ");
                        doc.add(g);
                        doc.add(p);
                        doc.add(m);
                        doc.add(o);
                        doc.add(n);
                        doc.add(h);
                        for (String lista3 : lista2) {
                        Paragraph texto  = new Paragraph(lista3);
                        doc.add(texto);
                        }
                        doc.close();
                        
                    } catch (FileNotFoundException | DocumentException ex) {
                        System.out.println("No se realizó la tarea correctamente");
                    }
                                                            
                    System.out.println(lista.size());
                    lista.trimToSize();
                
                //Creo los elementos para un nuevo grupo y escena y los ubico
                final Text aviso = new Text("Su Pregunta y Respuesta se han guerdado");
                aviso.setLayoutX(200);
                aviso.setLayoutY(100);
                                
                final Text otrap = new Text("¿Desea escribir otra Pregunta para el Examen?");
                otrap.setLayoutX(190);
                otrap.setLayoutY(150);
                
                final Text otrotex = new Text("INSTITUTO DE EDUCACIÓN SUPERIOR 9 - 024 LAVALLE");
                otrotex.setFill(Color.DARKBLUE);
                otrotex.setLayoutX(150);
                otrotex.setLayoutY(350);
                
                final Button otro = new Button("Nueva Pregunta");
                otro.setLayoutX(150);
                otro.setLayoutY(250);
                
                final Button menu2 = new Button("Finalizar el Examen");
                menu2.setLayoutX(350);
                menu2.setLayoutY(250);
                
                //Creo el grupo para la escena y agrego los componentes
                final Group opcion = new Group();
                opcion.getChildren().add(aviso);
                opcion.getChildren().add(otrap);
                opcion.getChildren().add(otro);
                opcion.getChildren().add(menu2);
                opcion.getChildren().add(otrotex);
                
                //Creo la escena y agrego el grupo
                Scene scena2 = new Scene(opcion, 600, 400);
                scena2.setFill(Color.POWDERBLUE);
                primaryStage.setTitle("Sistema de Evaluacion IES 9 - 024");
                primaryStage.setScene(scena2);
                primaryStage.show();
                
                //Agrego los eventos a los botones del nuevo grupo
                menu2.setOnAction(e3 -> {
                    primaryStage.setScene(scene);
                });
                
                otro.setOnAction(e4 -> {
                    primaryStage.setScene(scene1);      
                });
                
            });
        });
        });
        
        //Creo el evento al botón del alumno
        alumno.setOnAction(new EventHandler<ActionEvent>(){
                        
                                    
                //Defino los atributos 
                final Text rb_pregunta = new Text();
                final RadioButton rb_resp1 = new RadioButton();
                final RadioButton rb_resp2 = new RadioButton();
                final RadioButton rb_resp3 = new RadioButton();
                final RadioButton rb_resp4 = new RadioButton();
                final Button siguiente = new Button();
                final Button control = new Button();
                final Button inicio1 = new Button();
                 
                 @Override
            public void handle(ActionEvent event) {
           
              final ArrayList<String> arrayresp = new ArrayList<>();
              final ArrayList<String> resp2 = new ArrayList<>();
              
             //Creo un lector de stream para leer las preguntas del profesor
              try{
                  
                  fr = new FileReader("c:/" + archivar1.getText() + ".txt");
                  br = new BufferedReader(fr);
                  String lineaDeArchivo;
                  
                  while((lineaDeArchivo = br.readLine()) != null){
                      arrayresp.add(lineaDeArchivo);
                      resp2.add(lineaDeArchivo);
                      System.out.println(lineaDeArchivo);
                    }
                }catch(IOException e3){
                }finally{
                  
                  try{
                      if(null != fr)
                          fr.close();
                    }catch(IOException e4){
            }
        }
      
               
            final ToggleGroup grpRB = new ToggleGroup();   
            final Group galumno = new Group();
            
            //Instancio los componentes para la evaluación del alumno y los ubico
            rb_pregunta.setLayoutX(50);
            rb_pregunta.setLayoutY(100);
            rb_pregunta.setText("EVALUACIÓN IES 9 - 024 LAVALLE.");
     
            rb_resp1.setLayoutX(70);
            rb_resp1.setLayoutY(150);
            rb_resp1.setUserData("1");
            rb_resp1.setText("Marca sólo UNA respuesta.");
            rb_resp1.setToggleGroup(grpRB);
             
            rb_resp2.setLayoutX(70);
            rb_resp2.setLayoutY(200);
            rb_resp2.setUserData("2");
            rb_resp2.setText("Pulsa CONTROLAR para guardar tu puntaje.");
            rb_resp2.setToggleGroup(grpRB);
    
            rb_resp3.setLayoutX(70);
            rb_resp3.setLayoutY(250);
            rb_resp3.setUserData("3");
            rb_resp3.setText("Luego, pulsa SIGUIENTE.");
            rb_resp3.setToggleGroup(grpRB);
             
            rb_resp4.setLayoutX(70);
            rb_resp4.setLayoutY(300);
            rb_resp4.setUserData("4");
            rb_resp4.setText("Para SALIR, pulsa Inicio del Sistema.");
            rb_resp4.setToggleGroup(grpRB);
              
            siguiente.setLayoutX(350);
            siguiente.setLayoutY(160);
            siguiente.setTextFill(Color.DARKBLUE);
            siguiente.setText("Siguiente");
             
            control.setLayoutX(350);
            control.setLayoutY(200);
            control.setTextFill(Color.DARKBLUE);
            control.setText("Controlar");
            
            inicio1.setLayoutX(350);
            inicio1.setLayoutY(240);
            inicio1.setTextFill(Color.DARKBLUE);
            inicio1.setText("Inicio del Sistema");
                    
        //Creo el evento al botón siguiente de la evaluación             
        siguiente.setOnAction(new EventHandler<ActionEvent>(){
                                       
            int i = -1;
            
            int puntaje = 0; 
               
                @Override
                   public void handle(ActionEvent event) {
                        control.setDisable(false);
                       
                           i = i + 1;
                                                                                     
                    System.out.println(i);
                    
                    ArrayList <String> respuestasdes = new ArrayList<>();
                    
                    //Recogo las preguntas en un nuevo array y los desordeno
                    respuestasdes.add(arrayresp.get(5*i + 1)) ;
                    respuestasdes.add(arrayresp.get(5*i + 2)) ;
                    respuestasdes.add(arrayresp.get(5*i + 3)) ;
                    respuestasdes.add(arrayresp.get(5*i + 4)) ;
                    Collections.shuffle(respuestasdes);  
                    
                    //Establezco las respuestas obtenidas en la evaluación al alumno
                    rb_pregunta.setText(resp2.get(5*i + 0));
                    rb_resp1.setText(respuestasdes.get(0));
                    rb_resp2.setText(respuestasdes.get(1));
                    rb_resp3.setText(respuestasdes.get(2));
                    rb_resp4.setText(respuestasdes.get(3));
                    
                    
        //Agrego el evento al botón controlar           
        control.setOnAction((ActionEvent event1) -> {
                
                //Creo atributos para guardar las respuesatas marcadas
                boolean seleccion1, seleccion2;
                boolean seleccion3, seleccion4;
                opciones = 0;
                
                //Comparo los objetos para la corrección de la evaluación
                if(rb_resp1.isSelected()){
                    seleccion1 = rb_resp1.getText().equals(arrayresp.get(5*i + 4));
                    opciones++;
                    if (seleccion1) {puntaje++;}
                    else{puntaje--;}
                    System.out.println(seleccion1);
                }
                                
                if(rb_resp2.isSelected()){
                    seleccion2 = rb_resp2.getText().equals(arrayresp.get(5*i + 4));
                    opciones++;
                    if (seleccion2) {puntaje++;}
                    else{puntaje--;}
                }  
                
                if(rb_resp3.isSelected()){
                    seleccion3 = rb_resp3.getText().equals(arrayresp.get(5*i + 4));
                    opciones++;
                    if (seleccion3) {puntaje++;}
                    else{puntaje--;}
                }        
                
                if(rb_resp4.isSelected()){
                    seleccion4 = rb_resp4.getText().equals(arrayresp.get(5*i + 4));
                    opciones++;
                    if (seleccion4) {puntaje++;}
                    else{puntaje--;}
                }    
                
                if(opciones == 0){
                    puntaje--;
                }
                
                //Deshabilito los botones control y dejo en blanco la selección del alumno
                control.setDisable(true);
                
                rb_resp1.setSelected(false);
                rb_resp2.setSelected(false);
                rb_resp3.setSelected(false);
                rb_resp4.setSelected(false);
                                    
                System.out.println(puntaje + "puntos");
                System.out.println(i);
                
                //Establezco la corrección final al terminar el array de preguntas                
                if (i==(arrayresp.size()/5)-1) {
                    
                    final Group evaluar = new Group();
                    
                    final Text puntajefinal = new Text();
                    puntajefinal.setLayoutX(180);
                    puntajefinal.setLayoutY(150);
                    puntajefinal.setText("El Puntaje final de su Evaluación es: " + puntaje);
                    
                    if(puntaje<=3){
                        Text superar = new Text();
                        superar.setText("Usted DESAPROBÓ LA EVALUACIÓN");
                        superar.setLayoutX(180);
                        superar.setLayoutY(200);
                        evaluar.getChildren().add(superar);
                    }else{
                        Text superar1 = new Text();
                        superar1.setText("Felicidades!!!! Usted APROBÓ LA EVALUACIÓN");
                        superar1.setLayoutX(150);
                        superar1.setLayoutY(200);
                        evaluar.getChildren().add(superar1);
                    }
                    
                    //Agrego un botón final para el retorno al menú principal
                    final Button menu2 = new Button();
                    menu2.setLayoutX(200);
                    menu2.setLayoutY(250);
                    menu2.setTextFill(Color.DARKBLUE);
                    menu2.setText("Menú Principal del Sistema");
                    
                    menu2.setOnAction((ActionEvent event2) -> {
                        primaryStage.setScene(scene);
                    });
        
                    evaluar.getChildren().add(menu2);
                    evaluar.getChildren().add(puntajefinal);
                                        
                    Scene scene4 = new Scene(evaluar, 600, 400);
                    scene4.setFill(Color.LAVENDER);
                                        
                    primaryStage.setScene(scene4);
                }
        }); 
            }
        }); 
                       
                galumno.getChildren().add(rb_pregunta);
                galumno.getChildren().add(rb_resp1);
                galumno.getChildren().add(rb_resp2);
                galumno.getChildren().add(rb_resp3);
                galumno.getChildren().add(rb_resp4);
                galumno.getChildren().add(siguiente);
                galumno.getChildren().add(control);
                galumno.getChildren().add(inicio1);
               
                Scene scene3 = new Scene(galumno, 600, 400);
                scene3.setFill(Color.LAVENDER);
                                
                primaryStage.setScene(scene3);  
                
        inicio1.setOnAction((ActionEvent event1) -> {
            primaryStage.setScene(scene);
              });
        }}); 
             
            primaryStage.show();
    }
    
    //Establezco el método para confirmar la salida del sistema de evaluación
    private void cerrarVentana(){
        
        boolean si = BotonSalir.Ventana();
                    
       if(si){
           System.exit(0);
        }
    }

public static void main(String[] args) {
    launch(args);
}
  
}