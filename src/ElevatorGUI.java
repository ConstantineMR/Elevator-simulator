import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class ElevatorGUI extends Application implements Runnable {
    static Data data;
    static Label crFloor;
    static Button[] floors;
    static Button previous;

    @Override
    public void run() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        InsideElevator insideElevator = new InsideElevator();
        insideElevator.start();
        OutsideElevator outsideElevator = new OutsideElevator();
        outsideElevator.start();
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
            crFloor = new Label("01");
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
                    try {
                        data.semAge.acquire();
                        data.age[finalI] = (data.age[finalI] < 0)? 99 : data.age[finalI] - 1;
                        data.semAge.release();
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
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
            this.setX(0);
            this.setY(0);
            this.show();
        }
    }

    class OutsideElevator extends Stage {
        public void start() {
            //------------------------------------------------------------------------------------------------------------//
            //LAYOUTS
            StackPane root = new StackPane();
            AnchorPane anchorPane = new AnchorPane();
            VBox vBox = new VBox(0);
            //------------------------------------------------------------------------------------------------------------//
            //OBJECTS
            //Background Image
            FileInputStream inputstream = null;
            try {
                inputstream = new FileInputStream("JC.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image image = new Image(inputstream);
            ImageView imageView = new ImageView(image);
            //Floor Button
            floors = new Button[15];
            for (int i = 0; i < 15; i++) {
                floors[i] = new Button("Floor " + (i + 1));
                floors[i].setStyle( "-fx-background-color: #333333; " +
                        "-fx-border-color: #000000; " +
                        "-fx-text-fill: #FFFFFF; " +
                        "-fx-font-size: 12px;");
                floors[i].setMinSize(105, 30);
                floors[i].setMaxSize(105, 30);
            }
            //------------------------------------------------------------------------------------------------------------//
            //DISPLAY LAYERS
            for (int i = 14; i >= 0; i--) {
                vBox.getChildren().add(floors[i]);
            }
            AnchorPane.setLeftAnchor(vBox,205.0);
            AnchorPane.setBottomAnchor(vBox,0.0);
            anchorPane.getChildren().addAll(vBox);
            //------------------------------------------------------------------------------------------------------------//
            //FUNCTION
            for (int i = 0; i < 15; i++) {
                int finalI = i;
                floors[i].setOnMouseEntered(e -> {
                    floors[finalI].setStyle("-fx-background-color: #232323; " +
                            "-fx-border-color: #000000; " +
                            "-fx-text-fill: #FFFFFF; " +
                            "-fx-font-size: 12px;");
                });
                floors[i].setOnMouseExited(e -> {
                    floors[finalI].setStyle("-fx-background-color: #333333; " +
                            "-fx-border-color: #000000; " +
                            "-fx-text-fill: #FFFFFF; " +
                            "-fx-font-size: 12px;");
                });
                floors[i].setOnMouseClicked(e -> {
                    try {
                        data.semAge.acquire();
                        data.age[finalI] = (data.age[finalI] < 0)? 99 : data.age[finalI] - 1;
                        data.semAge.release();
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                });
            }
            //------------------------------------------------------------------------------------------------------------//
            //FINAL ADJUSTMENT
            root.getChildren().add(imageView);
            root.getChildren().add(anchorPane);
            Scene scene = new Scene(root);
            scene.setFill(Color.BLACK);
            this.setMinWidth(515);
            this.setMinHeight(700);
            this.setMaxWidth(515);
            this.setMaxHeight(700);
            this.setResizable(false);
            this.setScene(scene);
            this.setTitle("Outside The Elevator");
            this.show();
        }
    }

    public static void update(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String str = String.valueOf((data.level > 9)? data.level:("0" + data.level));
                if (data.direction == Data.Direction.D) {
                    str += "⇩";
                    crFloor.setStyle("-fx-control-inner-background: #333333; " +
                            "-fx-background-color: #333333; " +
                            "-fx-border-color: #000000; " +
                            "-fx-text-fill: #FF0000; " +
                            "-fx-font-size: 55px;");
                }
                else if (data.direction == Data.Direction.U){
                    str += "⇧";
                    crFloor.setStyle("-fx-control-inner-background: #333333; " +
                            "-fx-background-color: #333333; " +
                            "-fx-border-color: #000000; " +
                            "-fx-text-fill: #00FF00; " +
                            "-fx-font-size: 55px;");
                }
                else {
                    crFloor.setStyle("-fx-control-inner-background: #333333; " +
                            "-fx-background-color: #333333; " +
                            "-fx-border-color: #000000; " +
                            "-fx-text-fill: #FFFFFF; " +
                            "-fx-font-size: 55px;");
                }
                crFloor.setText(str);
                if (previous != null){
                    previous.setStyle( "-fx-background-color: #333333; " +
                            "-fx-border-color: #000000; " +
                            "-fx-text-fill: #FFFFFF; " +
                            "-fx-font-size: 12px;");
                }
                floors[data.level - 1].setStyle( "-fx-background-color: #EDA800; " +
                        "-fx-border-color: #000000; " +
                        "-fx-text-fill: #000000; " +
                        "-fx-font-size: 12px;");
                previous = floors[data.level - 1];
            }
        });
    }

}