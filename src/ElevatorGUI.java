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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class ElevatorGUI extends Application implements Runnable {
    static Data data;
    static Label[] crFloor;

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
            HBox root = new HBox(0);
            AnchorPane[] elevator = new AnchorPane[3];
            VBox[] MotherBox = new VBox[3];
            HBox[] hBox = new HBox[3];
            VBox[][] vBox = new VBox[3][2];
            Button[][] buttons = new Button[3][15];
            Button[] exit = new Button[3];
            crFloor = new Label[3];
            for (int k = 0; k < 3; k++) {
                elevator[k] = new AnchorPane();
                MotherBox[k] = new VBox(10);
                hBox[k] = new HBox(20);
                vBox[k][0] = new VBox(10);
                vBox[k][1] = new VBox(10);

                //------------------------------------------------------------------------------------------------------------//
                //OBJECTS
                //Floor Current Level
                crFloor[k] = new Label("01");
                crFloor[k].setStyle("-fx-control-inner-background: #333333; " +
                        "-fx-background-color: #333333; " +
                        "-fx-border-color: #000000; " +
                        "-fx-text-fill: #FFFFFF; " +
                        "-fx-font-size: 55px;");
                crFloor[k].setAlignment(Pos.CENTER);
                crFloor[k].setMinHeight(100);
                crFloor[k].setMinWidth(200);
                crFloor[k].setMaxHeight(100);
                crFloor[k].setMaxWidth(200);
                //Floor Button
                for (int i = 0; i < 15; i++) {
                    buttons[k][i] = new Button((i + 1) + "");
                    buttons[k][i].setStyle("-fx-background-color: #333333; " +
                            "-fx-border-color: #000000; " +
                            "-fx-text-fill: #FFFFFF; " +
                            "-fx-font-size: 16px;");
                    buttons[k][i].setMinSize(90, 40);
                    buttons[k][i].setMaxSize(90, 40);
                }
                //Exit Button
                exit[k] = new Button("EXIT");
                exit[k].setStyle("-fx-background-color: #FF1111; " +
                        "-fx-border-color: #000000; " +
                        "-fx-text-fill: #FFFFFF; " +
                        "-fx-font-size: 16px;");
                exit[k].setMinSize(90, 40);
                exit[k].setMaxSize(90, 40);
                //------------------------------------------------------------------------------------------------------------//
                //DISPLAY LAYERS
                for (int i = 0; i < 15; i++) {
                    vBox[k][i % 2].getChildren().add(buttons[k][i]);
                }
                vBox[k][1].getChildren().add(exit[k]);
                hBox[k].getChildren().addAll(vBox[k]);
                MotherBox[k].getChildren().addAll(crFloor[k], hBox[k]);
                AnchorPane.setBottomAnchor(MotherBox[k], 70.0);
                AnchorPane.setRightAnchor(MotherBox[k], 40.0);
                AnchorPane.setLeftAnchor(MotherBox[k], 40.0);
                AnchorPane.setTopAnchor(MotherBox[k], 70.0);
                elevator[k].getChildren().add(MotherBox[k]);
                elevator[k].setStyle("-fx-background-color: #464646;");
                root.getChildren().add(elevator[k]);
                //------------------------------------------------------------------------------------------------------------//
                //FUNCTION
                int finalK = k;
                for (int i = 0; i < 15; i++) {
                    int finalI = i;
                    buttons[k][i].setOnMouseEntered(e -> {
                        buttons[finalK][finalI].setStyle("-fx-background-color: #232323; " +
                                "-fx-border-color: #000000; " +
                                "-fx-text-fill: #FFFFFF; " +
                                "-fx-font-size: 16px;");
                    });
                    buttons[k][i].setOnMouseExited(e -> {
                        buttons[finalK][finalI].setStyle("-fx-background-color: #333333; " +
                                "-fx-border-color: #000000; " +
                                "-fx-text-fill: #FFFFFF; " +
                                "-fx-font-size: 16px;");
                    });
                    buttons[k][i].setOnMouseClicked(e -> {
                        try {
                            data.semAge[finalK].acquire();
                            data.age[finalK][finalI] = (data.age[finalK][finalI] < 0) ? 99 : data.age[finalK][finalI] - 1;
                            data.semAge[finalK].release();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    });
                }
                exit[k].setOnMouseEntered(e -> {
                    exit[finalK].setStyle("-fx-background-color: #EE0101; " +
                            "-fx-border-color: #000000; " +
                            "-fx-text-fill: #FFFFFF; " +
                            "-fx-font-size: 16px;");
                });
                exit[k].setOnMouseExited(e -> {
                    exit[finalK].setStyle("-fx-background-color: #FF1111; " +
                            "-fx-border-color: #000000; " +
                            "-fx-text-fill: #FFFFFF; " +
                            "-fx-font-size: 16px;");
                });
                exit[k].setOnMouseClicked(e -> this.close());
            }
            //------------------------------------------------------------------------------------------------------------//
            //FINAL ADJUSTMENT
            Scene scene = new Scene(root);
            scene.setFill(Color.BLACK);
            this.setMinWidth(855);
            this.setMinHeight(700);
            this.setMaxWidth(855);
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
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream("JC.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image image = new Image(inputStream);
            ImageView imageView = new ImageView(image);
            //Floor Button
            Button[] floors = new Button[15];
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
                    Panel panel = new Panel();
                    panel.setValue(finalI);
                    panel.start();
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
            this.setX(857);
            this.setY(0);
            this.show();
        }
    }

    class Panel extends Stage {
        int crLevel;

        public void setValue(int value) {
            this.crLevel = value;
        }

        public void start() {
            //------------------------------------------------------------------------------------------------------------//
            //LAYOUTS
            AnchorPane root = new AnchorPane();
            HBox hBox = new HBox(20);
            //------------------------------------------------------------------------------------------------------------//
            //OBJECTS
            //Circle
            int bestOption = ElevatorOS.BestElevator(crLevel);
            Button[] elNum = new Button[3];
            for (int i = 0; i < 3; i++) {
                elNum[i] = new Button();
                elNum[i].setShape(new Circle(40));
                elNum[i].setMinSize(80,80);
                elNum[i].setText((i + 1) + "");
                elNum[i].setStyle("-fx-background-color: " + (( i == bestOption )? "#EDA800; ":"#333333; " ) +
                        "-fx-border-color: #000000; " +
                        "-fx-text-fill: " + (( i == bestOption )? "#000000; ":"#FFFFFF; " ) +
                        "-fx-font-size: 20px;");
            }
            //------------------------------------------------------------------------------------------------------------//
            //DISPLAY LAYERS
            hBox.getChildren().addAll(elNum);
            AnchorPane.setLeftAnchor(hBox,40.0);
            AnchorPane.setRightAnchor(hBox,40.0);
            AnchorPane.setTopAnchor(hBox,20.0);
            AnchorPane.setBottomAnchor(hBox,20.0);
            root.getChildren().add(hBox);
            //------------------------------------------------------------------------------------------------------------//
            //FUNCTION
            for (int i = 0; i < 3; i++) {
                int finalI = i;
                elNum[i].setOnMouseEntered(e -> {
                    elNum[finalI].setStyle("-fx-background-color: #232323; " +
                            "-fx-border-color: #000000; " +
                            "-fx-text-fill: #FFFFFF; " +
                            "-fx-font-size: 20px;");
                });
                elNum[i].setOnMouseExited(e -> {
                    elNum[finalI].setStyle("-fx-background-color: " + (( finalI == bestOption )? "#EDA800; ":"#333333; " ) +
                            "-fx-border-color: #000000; " +
                            "-fx-text-fill: " + (( finalI == bestOption )? "#000000; ":"#FFFFFF; " ) +
                            "-fx-font-size: 20px;");
                });
                elNum[i].setOnMouseClicked(e -> {
                    try {
                        data.semAge[finalI].acquire();
                        data.age[finalI][crLevel] = (data.age[finalI][crLevel] < 0) ? 99 : data.age[finalI][crLevel] - 1;
                        data.semAge[finalI].release();
                        this.close();
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                });
            }
            root.setStyle("-fx-background-color: #464646;");
            //------------------------------------------------------------------------------------------------------------//
            //FINAL ADJUSTMENT
            Scene scene = new Scene(root);
            scene.setFill(Color.BLACK);
            this.setScene(scene);
            this.setTitle("Panel");
            this.show();
        }
    }

    public static void update(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    if (crFloor[i] != null) {
                        String str = String.valueOf((data.level[i] > 9) ? data.level[i] : ("0" + data.level[i]));
                        if (data.direction[i] == Data.Direction.D) {
                            str += "⇩";
                            crFloor[i].setStyle("-fx-control-inner-background: #333333; " +
                                    "-fx-background-color: #333333; " +
                                    "-fx-border-color: #000000; " +
                                    "-fx-text-fill: #FF0000; " +
                                    "-fx-font-size: 55px;");
                        } else if (data.direction[i] == Data.Direction.U) {
                            str += "⇧";
                            crFloor[i].setStyle("-fx-control-inner-background: #333333; " +
                                    "-fx-background-color: #333333; " +
                                    "-fx-border-color: #000000; " +
                                    "-fx-text-fill: #00FF00; " +
                                    "-fx-font-size: 55px;");
                        } else {
                            crFloor[i].setStyle("-fx-control-inner-background: #333333; " +
                                    "-fx-background-color: #333333; " +
                                    "-fx-border-color: #000000; " +
                                    "-fx-text-fill: #FFFFFF; " +
                                    "-fx-font-size: 55px;");
                        }
                        crFloor[i].setText(str);
                    }
                }
            }
        });
    }


}