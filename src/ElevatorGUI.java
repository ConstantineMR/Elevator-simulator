import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class ElevatorGUI extends Application {
    static boolean[] floorReq = new boolean[15];
    static int[] atFloor = new int[15];

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            floorReq[i] = false;
            atFloor[i] = 0;
        }
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        InsideElevator insideElevator = new InsideElevator();
        insideElevator.start();
        OutsideElevator outsideElevator = new OutsideElevator();
        outsideElevator.start();
        Elevator elevator = new Elevator();
    }

    class InsideElevator extends Stage {
        public void start() {
            //------------------------------------------------------------------------------------------------------------//
            //LAYOUTS
            AnchorPane root = new AnchorPane();
            VBox MotherBox = new VBox(10);
            HBox hBox = new HBox(20);
            VBox[] vBox = new VBox[2];
            vBox[0] = new VBox(10);
            vBox[1] = new VBox(10);

            //------------------------------------------------------------------------------------------------------------//
            //OBJECTS
            //Floor Current Level
            Label crFloor = new Label("1");
            crFloor.setStyle("-fx-control-inner-background: #333333; " +
                    "-fx-background-color: #333333; " +
                    "-fx-border-color: #000000; " +
                    "-fx-text-fill: #FFFFFF; " +
                    "-fx-font-size: 55px;");
            crFloor.setAlignment(Pos.CENTER);
            crFloor.setMinHeight(100);
            crFloor.setMinWidth(200);
            crFloor.setMaxHeight(100);
            crFloor.setMaxWidth(200);
            //Floor Button
            Button[] buttons = new Button[15];
            for (int i = 0; i < 15; i++) {
                buttons[i] = new Button((i + 1) + "");
                buttons[i].setStyle("-fx-background-color: #333333; " +
                        "-fx-border-color: #000000; " +
                        "-fx-text-fill: #FFFFFF; " +
                        "-fx-font-size: 16px;");
                buttons[i].setMinSize(90, 40);
                buttons[i].setMaxSize(90, 40);
            }
            //Exit Button
            Button exit = new Button("EXIT");
            exit.setStyle("-fx-background-color: #FF1111; " +
                    "-fx-border-color: #000000; " +
                    "-fx-text-fill: #FFFFFF; " +
                    "-fx-font-size: 16px;");
            exit.setMinSize(90, 40);
            exit.setMaxSize(90, 40);
            //------------------------------------------------------------------------------------------------------------//
            //DISPLAY LAYERS
            for (int i = 0; i < 15; i++) {
                vBox[i % 2].getChildren().add(buttons[i]);
            }
            vBox[1].getChildren().add(exit);
            hBox.getChildren().addAll(vBox);
            MotherBox.getChildren().addAll(crFloor, hBox);
            AnchorPane.setBottomAnchor(MotherBox, 70.0);
            AnchorPane.setRightAnchor(MotherBox, 60.0);
            AnchorPane.setLeftAnchor(MotherBox, 60.0);
            AnchorPane.setTopAnchor(MotherBox, 70.0);
            root.getChildren().add(MotherBox);
            root.setStyle("-fx-background-color: #464646;");

            //------------------------------------------------------------------------------------------------------------//
            //FUNCTION
            for (int i = 0; i < 15; i++) {
                int finalI = i;
                buttons[i].setOnMouseEntered(e -> {
                    buttons[finalI].setStyle("-fx-background-color: #232323; " +
                            "-fx-border-color: #000000; " +
                            "-fx-text-fill: #FFFFFF; " +
                            "-fx-font-size: 16px;");
                });
                buttons[i].setOnMouseExited(e -> {
                    buttons[finalI].setStyle("-fx-background-color: #333333; " +
                            "-fx-border-color: #000000; " +
                            "-fx-text-fill: #FFFFFF; " +
                            "-fx-font-size: 16px;");
                });
                buttons[i].setOnMouseClicked(e -> {
                    /////CRITICAL ZONE\\\\\
                    floorReq[finalI] = true;
                });
            }

            exit.setOnMouseEntered(e -> {
                exit.setStyle("-fx-background-color: #EE0101; " +
                        "-fx-border-color: #000000; " +
                        "-fx-text-fill: #FFFFFF; " +
                        "-fx-font-size: 16px;");
            });
            exit.setOnMouseExited(e -> {
                exit.setStyle("-fx-background-color: #FF1111; " +
                        "-fx-border-color: #000000; " +
                        "-fx-text-fill: #FFFFFF; " +
                        "-fx-font-size: 16px;");
            });
            exit.setOnMouseClicked(e -> this.close());

            //------------------------------------------------------------------------------------------------------------//
            //FINAL ADJUSTMENT
            Scene scene = new Scene(root);
            scene.setFill(Color.BLACK);
            this.setMinWidth(340);
            this.setMinHeight(700);
            this.setMaxWidth(340);
            this.setMaxHeight(700);
            this.setResizable(false);
            this.setScene(scene);
            this.setTitle("Inside The Elevator");
            this.show();
        }
    }

    class OutsideElevator extends Stage {
        public void start() {
            //------------------------------------------------------------------------------------------------------------//
            //LAYOUTS
            AnchorPane root = new AnchorPane();
            VBox vBox = new VBox(0);
            //------------------------------------------------------------------------------------------------------------//
            //OBJECTS
            //Floor Button
            Button[] floors = new Button[15];
            for (int i = 0; i < 15; i++) {
                floors[i] = new Button("Floor " + (i + 1));
                floors[i].setStyle( "-fx-background-color: #333333; " +
                                    "-fx-border-color: #000000; " +
                                    "-fx-text-fill: #FFFFFF; " +
                                    "-fx-font-size: 16px;");
                floors[i].setMinSize(150, 40);
                floors[i].setMaxSize(150, 40);
            }
            //Earth
            Rectangle earth = new Rectangle(340,20);
            earth.setFill(Color.SADDLEBROWN);
            //------------------------------------------------------------------------------------------------------------//
            //DISPLAY LAYERS
            for (int i = 14; i >= 0; i--) {
                vBox.getChildren().add(floors[i]);
            }
            AnchorPane.setLeftAnchor(vBox,80.0);
            AnchorPane.setBottomAnchor(vBox,20.0);
            AnchorPane.setLeftAnchor(earth,0.0);
            AnchorPane.setBottomAnchor(earth,0.0);
            root.getChildren().addAll(vBox,earth);
            root.setStyle("-fx-background-color: #87CEEB;");
            //------------------------------------------------------------------------------------------------------------//
            //FUNCTION
            for (int i = 0; i < 15; i++) {
                int finalI = i;
                floors[i].setOnMouseEntered(e -> {
                    floors[finalI].setStyle("-fx-background-color: #232323; " +
                            "-fx-border-color: #000000; " +
                            "-fx-text-fill: #FFFFFF; " +
                            "-fx-font-size: 16px;");
                });
                floors[i].setOnMouseExited(e -> {
                    floors[finalI].setStyle("-fx-background-color: #333333; " +
                            "-fx-border-color: #000000; " +
                            "-fx-text-fill: #FFFFFF; " +
                            "-fx-font-size: 16px;");
                });
                floors[i].setOnMouseClicked(e -> {
                    /////CRITICAL ZONE\\\\\
                    atFloor[finalI]++;
                });
            }
            //------------------------------------------------------------------------------------------------------------//
            //FINAL ADJUSTMENT
            Scene scene = new Scene(root);
            scene.setFill(Color.BLACK);
            this.setMinWidth(340);
            this.setMinHeight(700);
            this.setMaxWidth(340);
            this.setMaxHeight(700);
            this.setResizable(false);
            this.setScene(scene);
            this.setTitle("Outside The Elevator");
            this.show();
        }
    }
}
