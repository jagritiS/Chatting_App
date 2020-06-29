package sample;

import com.abyj.Chat.ChatServer;
import com.abyj.login.ConnectUsers;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chatting Application");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName,0,1);
        //grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);
        Button btn = new Button("Sign in");
        Button btn2 = new Button("Open Chat Box ");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(btn);
        hbBtn.getChildren().add(btn2);
        grid.add(hbBtn, 1, 4);
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) { //for signin

               /* primaryStage.setTitle("TextArea Experiment 1");
                TextArea textArea = new TextArea();
                VBox vbox = new VBox(textArea);
                Button newButton= new Button ("I'm a Button on new Window");
                StackPane secondaryLayout = new StackPane();
                secondaryLayout.getChildren().add( newButton);*/
                /*Scene scene = new Scene(vbox, 200, 100);
                primaryStage.setScene(scene);
                primaryStage.show();
*/

              /*  Scene secondScene = new Scene(secondaryLayout, 230, 100);
                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("Second Stage");
                newWindow.setScene(secondScene);
                // Set position of second window, related to primary window.
                newWindow.setX(primaryStage.getX() + 200);
                newWindow.setY(primaryStage.getY() + 100);

                newWindow.show();*/
                ConnectUsers c = new ConnectUsers();
                try {
                    //c.signIn(username,password);

                    c.signIns();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                String username = userTextField.getText();
                String password = pwBox.getText();
                System.out.println("u name : "+username+" password = "+password);
              ConnectUsers c = new ConnectUsers();
                try {
                    //c.signIn(username,password);
                    c.signUp(username,password);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //actiontarget.setFill(Color.FIREBRICK);
                //actiontarget.setText("Sign in button pressed");
            }
        });
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void dashboard(){
       // Scene scene = new Scene(grid, 300, 275);
       // primaryStage.setScene(scene);
       // primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
