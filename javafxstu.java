package javafxstu;
import java.sql.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.geometry.Insets;
import java.lang.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author patri
 */
public class Javafxstu extends Application {
    
    String[][] arr = new String[10][5];
    int i=0;
    @Override
    public void start(Stage primaryStage) {
        String conString = "jdbc:mysql://localhost:3306/patri";
        String username = "root";
        String password = "UtcL664+";
        Button save = new Button();
        save.setText("SAVE");
        Button b1=new Button();
        b1.setText("<<");
        Button b2=new Button();
        b2.setText(">>");
        Button b3=new Button();
        b3.setText("Search");
        Label l1=new Label("First Name: ");
        Label l2= new Label("Last Name: ");
        Label l3=new Label("Gender: ");
        Label l4= new Label("Reg. No: ");
        Label l5=new Label("Branch: ");
        Label l6=new Label("Search Reg No: ");
        Label l7=new Label("");
        Label l8=new Label("");
        Label l9=new Label("");
        Label l10=new Label("");
        Label l11=new Label("");
        Label l12=new Label("");
        Label l13=new Label("");
        Label l14=new Label("");
        Label l15=new Label("");
        Label l16=new Label("");
        TextField t1=new TextField("");
        TextField t2=new TextField("");
        TextField t3=new TextField("");
        TextField t4=new TextField("");
        TextField t5=new TextField("");
        TextField t6=new TextField("");
        
        HBox h1=new HBox();
        h1.getChildren().addAll(l6,t1,b3);
        HBox h2=new HBox();
        h1.setSpacing(10);
        h2.getChildren().addAll(l1,t2);
        HBox h3=new HBox();
        h3.getChildren().addAll(l2,t3);
        HBox h4=new HBox();
        h4.getChildren().addAll(l3,t5);
        HBox h5=new HBox();
        h4.setSpacing(10);
        h5.getChildren().addAll(l4,t4);
        HBox h6=new HBox();
        h6.getChildren().addAll(l5,t6);
        HBox h7=new HBox();
        h7.getChildren().addAll(b1,save,b2);
        h7.setSpacing(10);
        HBox h8=new HBox();
        h8.getChildren().addAll(l12,l13,l14,l15,l16);
        h8.setSpacing(30);
        HBox h9=new HBox();
        h9.getChildren().addAll(l7,l8,l9,l10,l11);
        h9.setSpacing(45);
        VBox v=new VBox();
        
        v.getChildren().addAll(h1,h2,h3,h4,h5,h6,h7,h8,h9);
        v.setPadding(new Insets(10,50,50,50));
        v.setSpacing(20);
        Scene scene = new Scene(v, 680, 600);
        save.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent e)
           {
               //firstname,lastname,gender,reg.no,branch
                
                try
                {     
                        System.out.println("connecting to the data source......");
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/patri","root","UtcL664+");  
                        PreparedStatement ps = con.prepareStatement("insert into student values (?,?,?,?,?)");
                        ps.setString(1, t2.getText());
                        ps.setString(2,t3.getText());
                    ps.setString(3, t5.getText());
                    ps.setString(4,t4.getText());
                    ps.setString(5, t6.getText());
                       
                        int count = ps.executeUpdate();
                        System.out.println(count + " Number of records inserted");
                        con.close();
                }
                catch(Exception ea){ System.out.println(ea);}  
                } 
                       
           
        });
        try 
                {
                   String query ="select * from student;";
                   Connection Conn = DriverManager.getConnection(conString, username, password);
                   PreparedStatement s=Conn.prepareStatement(query);
                   ResultSet rs=s.executeQuery(); 
                   while(rs.next())
                   {
                       l12.setText("Firstname");
                       l13.setText("Lastname");
                       l14.setText("Gender");
                       l15.setText("Regno");
                       l16.setText("Branch");
                       arr[i][0]=rs.getString("firstname");
                       arr[i][1]=rs.getString("lastname");
                       arr[i][2]=rs.getString("gender");
                       arr[i][3]=rs.getString("regno");
                       arr[i][4]=rs.getString("branch");
                       i++;
                   }
                    
                       
                Conn.close(); 
                }
                
                catch (Exception ex) 
                {
                   //return false;
                   System.out.println(ex);
                }
        b3.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent e)
           {
               int ij=0;
               
                   while(ij<i)
                   {
        
                       if(t1.getText().equalsIgnoreCase(arr[ij][3]))
                       {
                           l7.setText(arr[ij][0]);
                   l8.setText(arr[ij][1]);
                   l9.setText(arr[ij][2]);
                   l10.setText(arr[ij][3]);     
                   l11.setText(arr[ij][4]); 
                       i=ij;
                       break;
                       } 
                     
                       ij++;
                   }   
               
           }
        });
        
        
        b1.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent e)
           {
               if(i>0){
            i--;
            t2.setText(arr[i][0]);
            t3.setText(arr[i][1]);
            t5.setText(arr[i][2]);
                t4.setText(arr[i][3]);
            t6.setText(arr[i][4]);
                    l7.setText(arr[i][0]);
                   l8.setText(arr[i][1]);
                   l9.setText(arr[i][2]);
                   l10.setText(arr[i][3]);     
                   l11.setText(arr[i][4]); 
            
                }
           }
        });
        b2.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent e)
           {
                i++;  
        t2.setText(arr[i][0]);
        t3.setText(arr[i][1]);
        t5.setText(arr[i][2]);
            t4.setText(arr[i][3]);
        t6.setText(arr[i][4]);
                l7.setText(arr[i][0]);
                   l8.setText(arr[i][1]);
                   l9.setText(arr[i][2]);
                   l10.setText(arr[i][3]);     
                   l11.setText(arr[i][4]); 
            
                
           }
        });
        primaryStage.setTitle("STUDENTS INFO");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
