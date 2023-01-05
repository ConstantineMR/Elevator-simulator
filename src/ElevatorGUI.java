import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ElevatorGUI extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //------------------------------------------------------------------------------------------------------------//
        //LAYOUTS
        AnchorPane anchorPane = new AnchorPane();
        VBox vBox = new VBox(10);
        VBox vBoxButtons = new VBox(10);
        HBox hBoxOut = new HBox(10);
        HBox hBoxIn = new HBox(10);
        //------------------------------------------------------------------------------------------------------------//
        //OBJECTS
        //Send Button
        Button SButton = new Button("1");
        SButton.setStyle(   "-fx-background-color: #333333; " +
                "-fx-border-color: #000000; " +
                "-fx-text-fill: #FFFFFF; " +
                "-fx-font-size: 16px;" );
        SButton.setMinSize(90,40);
        SButton.setMaxSize(90,40);
        //Exit Button
        Button EButton = new Button("2");
        EButton.setStyle(   "-fx-background-color: #333333; " +
                "-fx-border-color: #000000; " +
                "-fx-text-fill: #FFFFFF; " +
                "-fx-font-size: 16px;" );
        EButton.setMinSize(90,40);
        EButton.setMaxSize(90,40);
        //Send Button
        Button YButton = new Button("3");
        YButton.setStyle(   "-fx-background-color: #333333; " +
                "-fx-border-color: #000000; " +
                "-fx-text-fill: #FFFFFF; " +
                "-fx-font-size: 16px;" );
        YButton.setMinSize(90,40);
        YButton.setMaxSize(90,40);
        //Exit Button
        Button NButton = new Button("4");
        NButton.setStyle(   "-fx-background-color: #333333; " +
                "-fx-border-color: #000000; " +
                "-fx-text-fill: #FFFFFF; " +
                "-fx-font-size: 16px;" );
        NButton.setMinSize(90,40);
        NButton.setMaxSize(90,40);
        //------------------------------------------------------------------------------------------------------------//
        //DISPLAY LAYERS
        vBoxButtons.getChildren().addAll( SButton, EButton, YButton, NButton );
        hBoxIn.getChildren().addAll( vBoxButtons );
        vBox.getChildren().addAll( hBoxOut, hBoxIn );
        AnchorPane.setBottomAnchor( vBox,20.0 );
        AnchorPane.setRightAnchor( vBox,20.0 );
        AnchorPane.setLeftAnchor( vBox,20.0 );
        AnchorPane.setTopAnchor( vBox,20.0 );
        anchorPane.getChildren().add(vBox);
        anchorPane.setStyle("-fx-background-color: #464646;");
        //------------------------------------------------------------------------------------------------------------//
        //FUNCTION

        //Color change during mouse in
        SButton.setOnMouseEntered( e -> {
            SButton.setStyle(   "-fx-background-color: #232323; " +
                    "-fx-border-color: #000000; " +
                    "-fx-text-fill: #FFFFFF; " +
                    "-fx-font-size: 16px;" );
        });
        SButton.setOnMouseExited( e -> {
            SButton.setStyle(   "-fx-background-color: #333333; " +
                    "-fx-border-color: #000000; " +
                    "-fx-text-fill: #FFFFFF; " +
                    "-fx-font-size: 16px;" );
        });
        EButton.setOnMouseEntered( e -> {
            EButton.setStyle(   "-fx-background-color: #232323; " +
                    "-fx-border-color: #000000; " +
                    "-fx-text-fill: #FFFFFF; " +
                    "-fx-font-size: 16px;" );
        });
        EButton.setOnMouseExited( e -> {
            EButton.setStyle(   "-fx-background-color: #333333; " +
                    "-fx-border-color: #000000; " +
                    "-fx-text-fill: #FFFFFF; " +
                    "-fx-font-size: 16px;" );
        });
        YButton.setOnMouseEntered( e -> {
            YButton.setStyle(   "-fx-background-color: #232323; " +
                    "-fx-border-color: #000000; " +
                    "-fx-text-fill: #FFFFFF; " +
                    "-fx-font-size: 16px;" );
        });
        YButton.setOnMouseExited( e -> {
            YButton.setStyle(   "-fx-background-color: #333333; " +
                    "-fx-border-color: #000000; " +
                    "-fx-text-fill: #FFFFFF; " +
                    "-fx-font-size: 16px;" );
        });
        NButton.setOnMouseEntered( e -> {
            NButton.setStyle(   "-fx-background-color: #232323; " +
                    "-fx-border-color: #000000; " +
                    "-fx-text-fill: #FFFFFF; " +
                    "-fx-font-size: 16px;" );
        });
        NButton.setOnMouseExited( e -> {
            NButton.setStyle(   "-fx-background-color: #333333; " +
                    "-fx-border-color: #000000; " +
                    "-fx-text-fill: #FFFFFF; " +
                    "-fx-font-size: 16px;" );
        });

        //Exit when clicked on exit button
        EButton.setOnMouseClicked( e -> primaryStage.close() );

        //------------------------------------------------------------------------------------------------------------//
        //FINAL ADJUSTMENT
        Scene scene = new Scene(anchorPane);
        scene.setFill(Color.BLACK);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Elevator-simulator");
        primaryStage.show();
    }
}
