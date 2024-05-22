import javafx.animation.Animation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.VideoTrack;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import com.sun.javafx.collections.*;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class CustomMain extends Application {
    Stage styleStage;
    Stage UserManual;
    Scene scene;
    Scene styleScene[] = new Scene[1000000];
    Pane[] pages = new Pane[1000000];
    GridPane[] stylePane = new GridPane[1000000];
    Pane Mpane = new Pane();
    Pane styleMPane[] = new Pane[1000000];
     public int currPage = 0;
    @Override
    public void start(Stage primaryStage) throws IOException {

        Pane pane = new Pane();


        pane = Welcome(primaryStage);
        scene = new Scene(Mpane, 1350, 700);

        primaryStage.setTitle("Title");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public Pane Welcome(Stage stage) throws IOException {
        Pane pane = new Pane();
        pane.setMinWidth(1350);
        pane.setMinHeight(700);
        { BackgroundFill backgroundFill =
                new BackgroundFill(
                        Color.LIGHTSTEELBLUE,

                        new CornerRadii(0),

                        new Insets(0));
        Background background = new Background(backgroundFill);
        pane.setBackground(background);}

        Mpane.getChildren().add(pane);

        Label label = new Label("Welcome To the");
        label.setLayoutX(470);
        Label subLabel = new Label("Ultimate Customizable Interface");
        subLabel.setLayoutY(55);
        subLabel.setLayoutX(280);
        Label click = new Label("Click here to begin");
        Button begin = new Button("Begin");
        label.setFont(Font.font("times new roman", 60));
        subLabel.setFont(Font.font("times new roman", 60));
        click.setFont(Font.font("times new roman", 40));
        begin.setFont(Font.font("times new roman", 25));
        click.setLayoutY(500);
        click.setLayoutX(500);
        begin.setLayoutY(540);
        begin.setLayoutX(615);
        begin.setOnAction( e ->{
            stage.setTitle("Page " + (currPage + 1));

                mainPane(stage);

        });
        pane.getChildren().addAll(label, click, begin, subLabel);
        return pane;
    }

    public void mainPane(Stage stage) {
        Mpane.getChildren().clear();
        pages[0] = new Pane();
        pages[0].setMinHeight(700);
        pages[0].setMinWidth(1350);

        Mpane.getChildren().add(pages[0]);
        Text label1 = new Text("First text");
        Text label2 = new Text("Second text");
        Text label3 = new Text("Third text");

        label1.setFont(Font.font("times new roman", 40));
        label2.setFont(Font.font("times new roman", 30));
        label3.setFont(Font.font("times new roman", 25));

        label1.setLayoutY(40);
        label2.setLayoutY(90);
        label3.setLayoutY(130);
        pages[0].getChildren().addAll(label1, label2, label3);

        Button newPage = new Button("Create new Page");
        pages[0].getChildren().add(newPage);
        newPage.setLayoutY(0);
        newPage.setLayoutX(1245);
        newPage.setOnAction( e ->{
            pages[currPage].getChildren().remove(newPage);
            GeneralPages(stage);
        });
        pages[currPage].setMinWidth(scene.getWidth());
        pages[currPage].setMinHeight(scene.getHeight());
        Button next = new Button("Next>>");
        next.setOnAction(e->{
            if(pages[currPage + 1] != null) {

                Mpane.getChildren().clear();
                currPage++;

                stage.setTitle("Page " + (currPage + 1));
                Mpane.getChildren().add(pages[currPage]);
            }
        });

        next.setLayoutY(600);
        next.setLayoutX(1290);
        Button Back = new Button("I don't Exist");

        Button EDIT = new Button("Open Editor");
        EDIT.setLayoutX(1270);
        EDIT.setLayoutY(25);


        pages[0].getChildren().addAll(next, EDIT);
        EDIT.setOnAction( e->{
            Style(label1, label2, label3, next, Back, newPage, EDIT, pages[currPage], stage);
        });





    }
    public void GeneralPages(Stage stage) {
        Mpane.getChildren().clear();
        currPage++;
        Button EDIT = new Button("Open Editor");
        Text label1 = new Text("First text");
        Text label2 = new Text("Second text");
        Text label3 = new Text("Third text");

        label1.setFont(Font.font("times new roman", 40));
        label2.setFont(Font.font("times new roman", 30));
        label3.setFont(Font.font("times new roman", 25));

        label1.setLayoutY(40);
        label2.setLayoutY(90);
        label3.setLayoutY(130);

        EDIT.setLayoutX(1270);
        EDIT.setLayoutY(25);




        stage.setTitle("Page " + (currPage + 1));
        pages[currPage] = new Pane();
        Mpane.getChildren().add(pages[currPage]);



        pages[currPage].getChildren().addAll( EDIT, label1, label2, label3);




        Button next = new Button("Next>>");
        Button previous = new Button("<<Back");

            next.setOnAction(e->{
                if(pages[currPage + 1] != null) {
                    delete();
                    Mpane.getChildren().clear();
                    currPage++;

                    stage.setTitle("Page " + (currPage + 1));
                    Mpane.getChildren().add(pages[currPage]);
                }
        });

        if(pages[currPage - 1] != null){
            previous.setOnAction(e->{

                Mpane.getChildren().clear();
            currPage--;

                stage.setTitle("Page " + (currPage + 1));
            Mpane.getChildren().add(pages[currPage]);
            });
        }
        pages[currPage].setMinHeight(scene.getHeight());
        pages[currPage].getChildren().addAll(next, previous);
        next.setLayoutY(600);
        next.setLayoutX(1290);
        previous.setLayoutX(0);
        previous.setLayoutY(600);
        Button newPage = new Button("Create new Page");
        pages[currPage].getChildren().add(newPage);
        newPage.setLayoutY(0);
        newPage.setLayoutX(1245);
        pages[currPage].setMinWidth(scene.getWidth());
        newPage.setOnAction( e ->{
            pages[currPage].getChildren().remove(newPage);
                GeneralPages(stage);
        });

        EDIT.setOnAction( e->{
            Style(label1, label2, label3, next, previous, newPage, EDIT, pages[currPage], stage);
        });
    }

    public void Style(Text label1, Text label2, Text label3, Button next, Button back, Button newPage,
                      Button Edit, Pane thePane, Stage theStage

                      ){
        styleStage = new Stage();

            stylePane[currPage] = new GridPane();

        styleScene[currPage] = new Scene(stylePane[currPage], 1350, 700);


        styleStage.setTitle("Title");
        styleStage.setScene(styleScene[currPage]);
        if (!styleStage.isShowing()) {
            styleStage.show();
        }

        Text forlabel1 = new Text("Settings for Text 1");

        TextField label1Size = new TextField("" + label1.getFont().getSize());
        TextField label1Font = new TextField("" + label1.getFont().getName());
        TextField label1X = new TextField("" + label1.getLayoutX());
        TextField label1Y = new TextField("" + label1.getLayoutY());
        TextField label1Wrap = new TextField("" + label1.getWrappingWidth());
        TextField label1Rotate = new TextField("" + label1.getRotate());
        TextField Red1 = new TextField("0");
        TextField Green1 = new TextField("0");
        TextField Blue1 = new TextField("0");
        TextField setText1 = new TextField(label1.getText());
        Label yourText1 = new Label("Enter Text");
        Label redL1 = new Label("Enter Red");
        Label greenL1 = new Label("Enter Green");
        Label blueL1 = new Label("Enter Blue");
        Label size1 = new Label("Enter Font Size");
        Label FontType1 = new Label("Enter Font Type");
        Label X = new Label("Enter X Pos");
        Label Y = new Label("Enter Y Pos");
        Label wrap1 = new Label("Enter Wrap Width");
        Label rotate1 = new Label("Enter Rotation Degree");
        Button change1 = new Button("Change Text 1");
        change1.setOnAction( e->{

            try {
                label1.setFont(Font.font(label1Font.getText(), Integer.parseInt(label1Size.getText())));
            }
            catch (NumberFormatException y){
                label1.setFont(Font.font(label1Font.getText()));
            }
            int R;
            int G;
            int B;
            try {
                 R = Integer.parseInt(Red1.getText());
                 G = Integer.parseInt(Green1.getText());
                 B = Integer.parseInt(Blue1.getText());
            }
            catch (NumberFormatException y){
                R = 0;
                G = 0;
                B = 0;
            }
            if (R > 255){
                R = 255;
            }
            if (R < 0){
                R = 0;
            }
            if (G > 255){
                G = 255;
            }
            if (G < 0){
                G = 0;
            }
            if (B > 255){
                B = 255;
            }
            if (B < 0){
                B = 0;
            }
            label1.setFill(Color.rgb(R, G, B));
            try {
                label1.setLayoutX(Integer.parseInt(label1X.getText()));
                label1.setLayoutY(Integer.parseInt(label1Y.getText()));
            }
            catch (NumberFormatException y){

            }
            try {
                label1.setWrappingWidth(Double.parseDouble(label1Wrap.getText()));
            }
            catch (NumberFormatException y){

            }
            try {
                label1.setRotate(Double.parseDouble(label1Rotate.getText()));
            }
            catch (NumberFormatException y){

            }
            label1.setText(setText1.getText());
            //color
            //change text

        });


        stylePane[currPage].add(change1, 1, 0);
        stylePane[currPage].add(size1, 0, 1);
        stylePane[currPage].add(label1Size, 1, 1);
        stylePane[currPage].add(FontType1, 0, 2);
        stylePane[currPage].add(label1Font, 1, 2);
        stylePane[currPage].add(label1X, 1, 3);
        stylePane[currPage].add(X, 0, 3);
        stylePane[currPage].add(label1Y, 1, 4);
        stylePane[currPage].add(Y, 0, 4);
        stylePane[currPage].add(wrap1, 0 , 5);
        stylePane[currPage].add(label1Wrap, 1 , 5);
        stylePane[currPage].add(rotate1, 0 , 6);
        stylePane[currPage].add(label1Rotate, 1 , 6);
        stylePane[currPage].add(Red1, 1, 7);
        stylePane[currPage].add(redL1, 0, 7);
        stylePane[currPage].add(Green1, 1 , 8);
        stylePane[currPage].add(greenL1, 0 , 8);
        stylePane[currPage].add(Blue1, 1 , 9);
        stylePane[currPage].add(blueL1, 0 , 9);
        stylePane[currPage].add(yourText1, 0, 10);
        stylePane[currPage].add(setText1, 1, 10);



        TextField label2Size = new TextField("" + label2.getFont().getSize());
        TextField label2Font = new TextField("" + label2.getFont().getName());
        TextField label2X = new TextField("" + label2.getLayoutX());
        TextField label2Y = new TextField("" + label2.getLayoutY());
        TextField label2Wrap = new TextField("" + label2.getWrappingWidth());
        TextField label2Rotate = new TextField("" + label2.getRotate());
        TextField Red2 = new TextField("0");
        TextField Green2 = new TextField("0");
        TextField Blue2 = new TextField("0");
        TextField setText2 = new TextField(label2.getText());

        Button change2 = new Button("Change Text 2");
        change2.setOnAction( e->{
            label2.setText(setText2.getText());

            try {
                label2.setFont(Font.font(label2Font.getText(), Integer.parseInt(label2Size.getText())));
            }
            catch (NumberFormatException y){
                label2.setFont(Font.font(label2Font.getText()));
            }
            int R;
            int G;
            int B;
            try {
                R = Integer.parseInt(Red2.getText());
                G = Integer.parseInt(Green2.getText());
                B = Integer.parseInt(Blue2.getText());
            }
            catch (NumberFormatException y){
                R = 0;
                G = 0;
                B = 0;
            }
            if (R > 255){
                R = 255;
            }
            if (R < 0){
                R = 0;
            }
            if (G > 255){
                G = 255;
            }
            if (G < 0){
                G = 0;
            }
            if (B > 255){
                B = 255;
            }
            if (B < 0){
                B = 0;
            }
            label2.setFill(Color.rgb(R, G, B));
            try {
                label2.setLayoutX(Integer.parseInt(label2X.getText()));
                label2.setLayoutY(Integer.parseInt(label2Y.getText()));
            }
            catch (NumberFormatException y){

            }
            try {
                label2.setWrappingWidth(Double.parseDouble(label2Wrap.getText()));
            }
            catch (NumberFormatException y){

            }
            try {
                label2.setRotate(Double.parseDouble(label2Rotate.getText()));
            }
            catch (NumberFormatException y){

            }
            //color
            //change text

        });


        stylePane[currPage].add(change2, 2, 0);
        stylePane[currPage].add(label2Size, 2, 1);
        stylePane[currPage].add(label2Font, 2, 2);
        stylePane[currPage].add(label2X, 2, 3);
        stylePane[currPage].add(label2Y, 2, 4);
        stylePane[currPage].add(label2Wrap, 2 , 5);
        stylePane[currPage].add(label2Rotate, 2 , 6);
        stylePane[currPage].add(Red2, 2, 7);
        stylePane[currPage].add(Green2, 2 , 8);
        stylePane[currPage].add(Blue2, 2 , 9);
        stylePane[currPage].add(setText2, 2, 10);

        TextField label3Size = new TextField("" + label3.getFont().getSize());
        TextField label3Font = new TextField("" + label3.getFont().getName());
        TextField label3X = new TextField("" + label3.getLayoutX());
        TextField label3Y = new TextField("" + label3.getLayoutY());
        TextField label3Wrap = new TextField("" + label3.getWrappingWidth());
        TextField label3Rotate = new TextField("" + label3.getRotate());
        TextField Red3 = new TextField("0");
        TextField Green3 = new TextField("0");
        TextField Blue3 = new TextField("0");
        TextField setText3 = new TextField(label3.getText());
        Pane node = new Pane();

        Button change3 = new Button("Change Text 3");
        change3.setOnAction( e->{
            label3.setText(setText3.getText());


            try {
                label3.setFont(Font.font(label3Font.getText(), Integer.parseInt(label3Size.getText())));
            }
            catch (NumberFormatException y){
                label3.setFont(Font.font(label3Font.getText()));
            }
            int R;
            int G;
            int B;
            try {
                R = Integer.parseInt(Red3.getText());
                G = Integer.parseInt(Green3.getText());
                B = Integer.parseInt(Blue3.getText());
            }
            catch (NumberFormatException y){
                R = 0;
                G = 0;
                B = 0;
            }
            if (R > 255){
                R = 255;
            }
            if (R < 0){
                R = 0;
            }
            if (G > 255){
                G = 255;
            }
            if (G < 0){
                G = 0;
            }
            if (B > 255){
                B = 255;
            }
            if (B < 0){
                B = 0;
            }
            label3.setFill(Color.rgb(R, G, B));
            try {
                label3.setLayoutX(Integer.parseInt(label3X.getText()));
                label3.setLayoutY(Integer.parseInt(label3Y.getText()));
            }
            catch (NumberFormatException y){

            }
            try {
                label3.setWrappingWidth(Double.parseDouble(label3Wrap.getText()));
            }
            catch (NumberFormatException y){

            }
            try {
                label3.setRotate(Double.parseDouble(label3Rotate.getText()));
            }
            catch (NumberFormatException y){

            }


        });


        stylePane[currPage].add(change3, 3, 0);
        stylePane[currPage].add(label3Size, 3, 1);
        stylePane[currPage].add(label3Font, 3, 2);
        stylePane[currPage].add(label3X, 3, 3);
        stylePane[currPage].add(label3Y, 3, 4);
        stylePane[currPage].add(label3Wrap, 3 , 5);
        stylePane[currPage].add(label3Rotate, 3 , 6);
        stylePane[currPage].add(Red3, 3, 7);
        stylePane[currPage].add(Green3, 3, 8);
        stylePane[currPage].add(Blue3, 3, 9);
        stylePane[currPage].add(setText3, 3, 10);

        Label paneRed = new Label("Enter Red");
        Label paneBlue = new Label("Enter Blue");
        Label paneGreen = new Label("Enter Green");
        TextField paneSR = new TextField("230");
        TextField paneSG = new TextField("230");
        TextField paneSB = new TextField("230");

        Label paneRad = new Label("Enter Corner Radius");

        TextField paneSetRad = new TextField("0");


        Button setPane = new Button("Change Pane");
        stylePane[currPage].add(setPane, 1, 11);
        stylePane[currPage].add(paneRed, 0, 12);
        stylePane[currPage].add(paneGreen, 0, 13);
        stylePane[currPage].add(paneBlue, 0, 14);
        stylePane[currPage].add(paneSR, 1, 12);
        stylePane[currPage].add(paneSG, 1, 13);
        stylePane[currPage].add(paneSB, 1, 14);
        stylePane[currPage].add(paneRad, 0, 15 );
        stylePane[currPage].add(paneSetRad, 1, 15 );


        setPane.setOnAction( e->{
            int R;
            int G;
            int B;
            try {
                R = Integer.parseInt(paneSR.getText());
                G = Integer.parseInt(paneSG.getText());
                B = Integer.parseInt(paneSB.getText());
            }
            catch (NumberFormatException y){
                R = 0;
                G = 0;
                B = 0;
            }
            if (R > 255){
                R = 255;
            }
            if (R < 0){
                R = 0;
            }
            if (G > 255){
                G = 255;
            }
            if (G < 0){
                G = 0;
            }
            if (B > 255){
                B = 255;
            }
            if (B < 0){
                B = 0;
            }
            double radius;
            double insets;
            try {
                radius = Double.parseDouble(paneSetRad.getText());
            }
            catch (NumberFormatException y){
                radius = 0;
            }


        BackgroundFill backgroundFill =
                new BackgroundFill(
                        Color.rgb(R, G, B),

                            new CornerRadii(radius),

                        new Insets(0)
                );
        Background background =
                new Background(backgroundFill);

        thePane.setBackground(background);
        });
        /*
        next back newpage edit
         */
        //width
        //height
        //button color
        //text color
        //rotation
        //position
        // font
        //font size
        //Text

        Button changeNext = new Button("Change Next");
        Button changeBack = new Button("Change Back");
        Button changeNew = new Button("Change New Page");
        Button changeEdit = new Button("Change Edit");

        stylePane[currPage].add(changeNext, 5, 0);
        stylePane[currPage].add(changeBack, 6, 0);
        stylePane[currPage].add(changeNew, 7, 0);
        stylePane[currPage].add(changeEdit, 8, 0);

        TextField nextWidth = new TextField("" + next.getMinWidth());
        TextField nextHeight = new TextField("" + next.getMinHeight());
        TextField nextBTRed = new TextField("230");
        TextField nextBTGreen = new TextField("230");
        TextField nextBTBlue = new TextField("230");
        TextField nextTXTRed = new TextField("0");
        TextField nextTXTGreen = new TextField("0");
        TextField nextTXTBlue = new TextField("0");
        TextField nextRotat = new TextField("" + next.getRotate());
        TextField nextPosX = new TextField("" + next.getLayoutX());
        TextField nextPosY = new TextField("" + next.getLayoutY());
        TextField nextFont = new TextField("" + next.getFont().getName());
        TextField nextFontSize = new TextField("" + next.getFont().getSize());
        TextField nextText = new TextField("" + next.getText());
        TextField nextcornerRad = new TextField("5");


        stylePane[currPage].add(nextWidth,5, 1);
        stylePane[currPage].add(nextHeight,5, 2);
        stylePane[currPage].add(nextBTRed,5, 3);
        stylePane[currPage].add(nextBTGreen,5, 4);
        stylePane[currPage].add(nextBTBlue,5, 5);
        stylePane[currPage].add(nextTXTRed,5, 6);
        stylePane[currPage].add(nextTXTGreen,5, 7);
        stylePane[currPage].add(nextTXTBlue,5, 8);
        stylePane[currPage].add(nextRotat,5, 9);
        stylePane[currPage].add(nextPosX,5, 10);
        stylePane[currPage].add(nextPosY,5, 11);
        stylePane[currPage].add(nextFont,5, 12);
        stylePane[currPage].add(nextFontSize,5, 13);
        stylePane[currPage].add(nextText,5, 14);
        stylePane[currPage].add(nextcornerRad,5, 15);


        TextField backWidth = new TextField("" + back.getMinWidth());
        TextField backHeight = new TextField("" + back.getMinHeight());
        TextField backBTRed = new TextField("230");
        TextField backBTGreen = new TextField("230");
        TextField backBTBlue = new TextField("230");
        TextField backTXTRed = new TextField("0");
        TextField backTXTGreen = new TextField("0");
        TextField backTXTBlue = new TextField("0");
        TextField backRotat = new TextField("" + back.getRotate());
        TextField backPosX = new TextField("" + back.getLayoutX());
        TextField backPosY = new TextField("" + back.getLayoutY());
        TextField backFont = new TextField("" + back.getFont().getName());
        TextField backFontSize = new TextField("" + back.getFont().getSize());
        TextField backText = new TextField("" + back.getText());
        TextField backcornerRad = new TextField("5");


        stylePane[currPage].add(backWidth,6, 1);
        stylePane[currPage].add(backHeight,6, 2);
        stylePane[currPage].add(backBTRed,6, 3);
        stylePane[currPage].add(backBTGreen,6, 4);
        stylePane[currPage].add(backBTBlue,6, 5);
        stylePane[currPage].add(backTXTRed,6, 6);
        stylePane[currPage].add(backTXTGreen,6, 7);
        stylePane[currPage].add(backTXTBlue,6, 8);
        stylePane[currPage].add(backRotat,6, 9);
        stylePane[currPage].add(backPosX,6, 10);
        stylePane[currPage].add(backPosY,6, 11);
        stylePane[currPage].add(backFont,6, 12);
        stylePane[currPage].add(backFontSize,6, 13);
        stylePane[currPage].add(backText,6, 14);
        stylePane[currPage].add(backcornerRad,6, 15);


        TextField newWidth = new TextField("" + newPage.getMinWidth());
        TextField newHeight = new TextField("" + newPage.getMinHeight());
        TextField newBTRed = new TextField("230");
        TextField newBTGreen = new TextField("230");
        TextField newBTBlue = new TextField("230");
        TextField newTXTRed = new TextField("0");
        TextField newTXTGreen = new TextField("0");
        TextField newTXTBlue = new TextField("0");
        TextField newRotat = new TextField("" + newPage.getRotate());
        TextField newPosX = new TextField("" + newPage.getLayoutX());
        TextField newPosY = new TextField("" + newPage.getLayoutY());
        TextField newFont = new TextField("" + newPage.getFont().getName());
        TextField newFontSize = new TextField("" + newPage.getFont().getSize());
        TextField newText = new TextField("" + newPage.getText());
        TextField newcornerRad = new TextField("5");


        stylePane[currPage].add(newWidth,7, 1);
        stylePane[currPage].add(newHeight,7, 2);
        stylePane[currPage].add(newBTRed,7, 3);
        stylePane[currPage].add(newBTGreen,7, 4);
        stylePane[currPage].add(newBTBlue,7, 5);
        stylePane[currPage].add(newTXTRed,7, 6);
        stylePane[currPage].add(newTXTGreen,7, 7);
        stylePane[currPage].add(newTXTBlue,7, 8);
        stylePane[currPage].add(newRotat,7, 9);
        stylePane[currPage].add(newPosX,7, 10);
        stylePane[currPage].add(newPosY,7, 11);
        stylePane[currPage].add(newFont,7, 12);
        stylePane[currPage].add(newFontSize,7, 13);
        stylePane[currPage].add(newText,7, 14);
        stylePane[currPage].add(newcornerRad,7, 15);


        TextField editWidth = new TextField("" + Edit.getMinWidth());
        TextField editHeight = new TextField("" + Edit.getMinHeight());
        TextField editBTRed = new TextField("230");
        TextField editBTGreen = new TextField("230");
        TextField editBTBlue = new TextField("230");
        TextField editTXTRed = new TextField("0");
        TextField editTXTGreen = new TextField("0");
        TextField editTXTBlue = new TextField("0");
        TextField editRotat = new TextField("" + Edit.getRotate());
        TextField editPosX = new TextField("" + Edit.getLayoutX());
        TextField editPosY = new TextField("" + Edit.getLayoutY());
        TextField editFont = new TextField("" + Edit.getFont().getName());
        TextField editFontSize = new TextField("" + Edit.getFont().getSize());
        TextField editText = new TextField("" + Edit.getText());
        TextField editcornerRad = new TextField("5");


        stylePane[currPage].add(editWidth,8, 1);
        stylePane[currPage].add(editHeight,8, 2);
        stylePane[currPage].add(editBTRed,8, 3);
        stylePane[currPage].add(editBTGreen,8, 4);
        stylePane[currPage].add(editBTBlue,8, 5);
        stylePane[currPage].add(editTXTRed,8, 6);
        stylePane[currPage].add(editTXTGreen,8, 7);
        stylePane[currPage].add(editTXTBlue,8, 8);
        stylePane[currPage].add(editRotat,8, 9);
        stylePane[currPage].add(editPosX,8, 10);
        stylePane[currPage].add(editPosY,8, 11);
        stylePane[currPage].add(editFont,8, 12);
        stylePane[currPage].add(editFontSize,8, 13);
        stylePane[currPage].add(editText,8, 14);
        stylePane[currPage].add(editcornerRad,8, 15);


        Label Width = new Label("Enter MinWidth");
        Label Height = new Label("Enter MinHeight");
        Label BTRed = new Label("Enter Button Red");
        Label BTGreen = new Label("Enter Button Green");
        Label BTBlue = new Label("Enter Button Blue");
        Label TXTRed = new Label("Enter Text Red");
        Label TXTGreen = new Label("Enter Text Green");
        Label TXTBlue = new Label("Enter Text Blue");
        Label Rotat = new Label("Enter Rotation Degree");
        Label PosX = new Label("Enter X Pos");
        Label PosY = new Label("Enter Y Pos");
        Label FontName = new Label("Enter Font Type");
        Label FontSize = new Label("Enter Font Size");
        Label Text = new Label("Enter Text");
        Label cornerRad = new Label("Enter Corner Radius");


        stylePane[currPage].add(Width,4, 1);
        stylePane[currPage].add(Height,4, 2);
        stylePane[currPage].add(BTRed,4, 3);
        stylePane[currPage].add(BTGreen,4, 4);
        stylePane[currPage].add(BTBlue,4, 5);
        stylePane[currPage].add(TXTRed,4, 6);
        stylePane[currPage].add(TXTGreen,4, 7);
        stylePane[currPage].add(TXTBlue,4, 8);
        stylePane[currPage].add(Rotat,4, 9);
        stylePane[currPage].add(PosX,4, 10);
        stylePane[currPage].add(PosY,4, 11);
        stylePane[currPage].add(FontName,4, 12);
        stylePane[currPage].add(FontSize,4, 13);
        stylePane[currPage].add(Text,4, 14);
        stylePane[currPage].add(cornerRad,4, 15);


        /*

        next back newpage edit
         */
        //Text

        changeNext.setOnAction( e->{
            try {
                next.setMinWidth(Double.parseDouble(nextWidth.getText()));
            }
            catch (NumberFormatException y){}
            try{
                next.setMinHeight(Double.parseDouble(nextHeight.getText()));
            }
            catch (NumberFormatException y){}
            int BTR;
            int BTG;
            int BTB;
            int TXTR;
            int TXTG;
            int TXTB;
            try{
                BTR = Integer.parseInt( nextBTRed.getText());
                BTG = Integer.parseInt( nextBTGreen.getText());
                BTB = Integer.parseInt( nextBTBlue.getText());
            }
            catch (NumberFormatException y){
                BTR = 0;
                BTG = 0;
                BTB = 0;
            }
            if (BTR > 255){
                BTR = 255;
            }
            if(BTR < 0){
                BTR = 0;
            }
            if (BTG > 255){
                BTG = 255;
            }
            if(BTG < 0){
                BTG = 0;
            }
            if (BTB > 255){
                BTB = 255;
            }
            if(BTB < 0){
                BTB = 0;
            }

            try{
                TXTR = Integer.parseInt( nextTXTRed.getText());
                TXTG = Integer.parseInt( nextTXTGreen.getText());
                TXTB = Integer.parseInt( nextTXTBlue.getText());
            }
            catch (NumberFormatException y){
                TXTR = 0;
                TXTG = 0;
                TXTB = 0;
            }
            if (TXTR > 255){
                TXTR = 255;
            }
            if(TXTR < 0){
                TXTR = 0;
            }
            if (TXTG > 255){
                TXTG = 255;
            }
            if(TXTG < 0){
                TXTG = 0;
            }
            if (TXTB > 255){
                TXTB = 255;
            }
            if(TXTB < 0){
                TXTB = 0;
            }
            try {
                next.setRotate(Double.parseDouble(nextRotat.getText()));
            }
            catch (NumberFormatException y){}
            try{
                next.setLayoutX( Double.parseDouble(nextPosX.getText()));
                next.setLayoutY( Double.parseDouble(nextPosY.getText()));
            }
            catch (NumberFormatException y){}
            try {
                next.setFont(Font.font(nextFont.getText(), Double.parseDouble(nextFontSize.getText())));
            }
            catch (NumberFormatException y){
                next.setFont(Font.font(nextFont.getText()));
            }
            next.setText(nextText.getText());

            double radius;
            double insets;
            try {
                radius = Double.parseDouble(nextcornerRad.getText());
            }
            catch (NumberFormatException y){
                radius = 0;
            }


            BackgroundFill backgroundFill =
                    new BackgroundFill(
                            Color.rgb(BTR, BTG, BTB),

                            new CornerRadii(radius),

                            new Insets(0)
                    );
            Background background =
                    new Background(backgroundFill);

            next.setBackground(background);
            next.setTextFill(Color.rgb(TXTR, TXTG, TXTB));
        });


        changeBack.setOnAction( e->{
            try {
                back.setMinWidth(Double.parseDouble(backWidth.getText()));
            }
            catch (NumberFormatException y){}
            try{
                back.setMinHeight(Double.parseDouble(backHeight.getText()));
            }
            catch (NumberFormatException y){}
            int BTR;
            int BTG;
            int BTB;
            int TXTR;
            int TXTG;
            int TXTB;
            try{
                BTR = Integer.parseInt( backBTRed.getText());
                BTG = Integer.parseInt( backBTGreen.getText());
                BTB = Integer.parseInt( backBTBlue.getText());
            }
            catch (NumberFormatException y){
                BTR = 0;
                BTG = 0;
                BTB = 0;
            }
            if (BTR > 255){
                BTR = 255;
            }
            if(BTR < 0){
                BTR = 0;
            }
            if (BTG > 255){
                BTG = 255;
            }
            if(BTG < 0){
                BTG = 0;
            }
            if (BTB > 255){
                BTB = 255;
            }
            if(BTB < 0){
                BTB = 0;
            }

            try{
                TXTR = Integer.parseInt( backTXTRed.getText());
                TXTG = Integer.parseInt( backTXTGreen.getText());
                TXTB = Integer.parseInt( backTXTBlue.getText());
            }
            catch (NumberFormatException y){
                TXTR = 0;
                TXTG = 0;
                TXTB = 0;
            }
            if (TXTR > 255){
                TXTR = 255;
            }
            if(TXTR < 0){
                TXTR = 0;
            }
            if (TXTG > 255){
                TXTG = 255;
            }
            if(TXTG < 0){
                TXTG = 0;
            }
            if (TXTB > 255){
                TXTB = 255;
            }
            if(TXTB < 0){
                TXTB = 0;
            }
            try {
                back.setRotate(Double.parseDouble(backRotat.getText()));
            }
            catch (NumberFormatException y){}
            try{
                back.setLayoutX( Double.parseDouble(backPosX.getText()));
                back.setLayoutY( Double.parseDouble(backPosY.getText()));
            }
            catch (NumberFormatException y){}
            try {
                back.setFont(Font.font(backFont.getText(), Double.parseDouble(backFontSize.getText())));
            }
            catch (NumberFormatException y){
                back.setFont(Font.font(backFont.getText()));
            }
            back.setText(backText.getText());

            double radius;
            double insets;
            try {
                radius = Double.parseDouble(backcornerRad.getText());
            }
            catch (NumberFormatException y){
                radius = 0;
            }


            BackgroundFill backgroundFill =
                    new BackgroundFill(
                            Color.rgb(BTR, BTG, BTB),

                            new CornerRadii( radius),

                            new Insets(0)
                    );
            Background background =
                    new Background(backgroundFill);

            back.setBackground(background);
            back.setTextFill(Color.rgb(TXTR, TXTG, TXTB));
        });


        changeNew.setOnAction( e->{
            try {
                newPage.setMinWidth(Double.parseDouble(newWidth.getText()));
            }
            catch (NumberFormatException y){}
            try{
                newPage.setMinHeight(Double.parseDouble(newHeight.getText()));
            }
            catch (NumberFormatException y){}
            int BTR;
            int BTG;
            int BTB;
            int TXTR;
            int TXTG;
            int TXTB;
            try{
                BTR = Integer.parseInt( newBTRed.getText());
                BTG = Integer.parseInt( newBTGreen.getText());
                BTB = Integer.parseInt( newBTBlue.getText());
            }
            catch (NumberFormatException y){
                BTR = 0;
                BTG = 0;
                BTB = 0;
            }
            if (BTR > 255){
                BTR = 255;
            }
            if(BTR < 0){
                BTR = 0;
            }
            if (BTG > 255){
                BTG = 255;
            }
            if(BTG < 0){
                BTG = 0;
            }
            if (BTB > 255){
                BTB = 255;
            }
            if(BTB < 0){
                BTB = 0;
            }

            try{
                TXTR = Integer.parseInt( newTXTRed.getText());
                TXTG = Integer.parseInt( newTXTGreen.getText());
                TXTB = Integer.parseInt( newTXTBlue.getText());
            }
            catch (NumberFormatException y){
                TXTR = 0;
                TXTG = 0;
                TXTB = 0;
            }
            if (TXTR > 255){
                TXTR = 255;
            }
            if(TXTR < 0){
                TXTR = 0;
            }
            if (TXTG > 255){
                TXTG = 255;
            }
            if(TXTG < 0){
                TXTG = 0;
            }
            if (TXTB > 255){
                TXTB = 255;
            }
            if(TXTB < 0){
                TXTB = 0;
            }
            try {
                newPage.setRotate(Double.parseDouble(newRotat.getText()));
            }
            catch (NumberFormatException y){}
            try{
                newPage.setLayoutX( Double.parseDouble(newPosX.getText()));
                newPage.setLayoutY( Double.parseDouble(newPosY.getText()));
            }
            catch (NumberFormatException y){}
            try {
                newPage.setFont(Font.font(newFont.getText(), Double.parseDouble(newFontSize.getText())));
            }
            catch (NumberFormatException y){
                newPage.setFont(Font.font(newFont.getText()));
            }
            newPage.setText(newText.getText());

            double radius;
            double insets;
            try {
                radius = Double.parseDouble(newcornerRad.getText());
            }
            catch (NumberFormatException y){
                radius = 0;
            }


            BackgroundFill backgroundFill =
                    new BackgroundFill(
                            Color.rgb(BTR, BTG, BTB),

                            new CornerRadii(radius),

                            new Insets(0)
                    );
            Background background =
                    new Background(backgroundFill);

            newPage.setBackground(background);
            newPage.setTextFill(Color.rgb(TXTR, TXTG, TXTB));
        });


        changeEdit.setOnAction( e->{
            try {
                Edit.setMinWidth(Double.parseDouble(editWidth.getText()));
            }
            catch (NumberFormatException y){}
            try{
                Edit.setMinHeight(Double.parseDouble(editHeight.getText()));
            }
            catch (NumberFormatException y){}
            int BTR;
            int BTG;
            int BTB;
            int TXTR;
            int TXTG;
            int TXTB;
            try{
                BTR = Integer.parseInt( editBTRed.getText());
                BTG = Integer.parseInt( editBTGreen.getText());
                BTB = Integer.parseInt( editBTBlue.getText());
            }
            catch (NumberFormatException y){
                BTR = 0;
                BTG = 0;
                BTB = 0;
            }
            if (BTR > 255){
                BTR = 255;
            }
            if(BTR < 0){
                BTR = 0;
            }
            if (BTG > 255){
                BTG = 255;
            }
            if(BTG < 0){
                BTG = 0;
            }
            if (BTB > 255){
                BTB = 255;
            }
            if(BTB < 0){
                BTB = 0;
            }

            try{
                TXTR = Integer.parseInt( editTXTRed.getText());
                TXTG = Integer.parseInt( editTXTGreen.getText());
                TXTB = Integer.parseInt( editTXTBlue.getText());
            }
            catch (NumberFormatException y){
                TXTR = 0;
                TXTG = 0;
                TXTB = 0;
            }
            if (TXTR > 255){
                TXTR = 255;
            }
            if(TXTR < 0){
                TXTR = 0;
            }
            if (TXTG > 255){
                TXTG = 255;
            }
            if(TXTG < 0){
                TXTG = 0;
            }
            if (TXTB > 255){
                TXTB = 255;
            }
            if(TXTB < 0){
                TXTB = 0;
            }
            try {
                Edit.setRotate(Double.parseDouble(editRotat.getText()));
            }
            catch (NumberFormatException y){}
            try{
                Edit.setLayoutX( Double.parseDouble(editPosX.getText()));
                Edit.setLayoutY( Double.parseDouble(editPosY.getText()));
            }
            catch (NumberFormatException y){}
            try {
                Edit.setFont(Font.font(editFont.getText(), Double.parseDouble(editFontSize.getText())));
            }
            catch (NumberFormatException y){
                Edit.setFont(Font.font(editFont.getText()));
            }
            Edit.setText(editText.getText());

            double radius;
            double insets;
            try {
                radius = Double.parseDouble(editcornerRad.getText());
            }
            catch (NumberFormatException y){
                radius = 0;
            }



            BackgroundFill backgroundFill =
                    new BackgroundFill(
                            Color.rgb(BTR, BTG, BTB),

                            new CornerRadii(radius),

                            new Insets(0)
                    );
            Background background =
                    new Background(backgroundFill);

            Edit.setBackground(background);
            Edit.setTextFill(Color.rgb(TXTR, TXTG, TXTB));
        });




    Button SetSquare = new Button("Add Square");
    Label SQred = new Label("Enter Red");
    Label SQgreen = new Label("Enter Green");
    Label SQblue = new Label("Enter Blue");
    Label rotation = new Label("Enter Rotation");
    Label height = new Label("Enter Height");
    Label width = new Label("Enter Width");
    Label posX = new Label("Enter X Pos");
    Label posY = new Label("Enter Y Pos");
    Label arcHeight = new Label("Enter Arc Height");
    Label arcWidth = new Label("Enter Arc Width");
    TextField SetRed = new TextField("0");
    TextField SetGreen = new TextField("0");
    TextField SetBlue = new TextField("0");
    TextField SetRotat = new TextField("0");
    TextField SetHeight = new TextField("0");
    TextField SetWidth = new TextField("0");
    TextField SetPosX = new TextField("0");
    TextField SetPosY = new TextField("0");
    TextField SetAH = new TextField("0");
    TextField SetAW = new TextField("0");



    stylePane[currPage].add(SetSquare, 1, 16);
    stylePane[currPage].add(SQred, 0, 17);
    stylePane[currPage].add(SetRed, 1, 17);
    stylePane[currPage].add(SQgreen, 0, 18);
    stylePane[currPage].add(SetGreen, 1, 18);
    stylePane[currPage].add(SQblue, 0, 19);
    stylePane[currPage].add(SetBlue, 1, 19);
    stylePane[currPage].add(rotation, 0, 20);
    stylePane[currPage].add(SetRotat, 1, 20);
    stylePane[currPage].add(height, 0, 21);
    stylePane[currPage].add(SetHeight, 1, 21);
    stylePane[currPage].add(width, 0, 22);
    stylePane[currPage].add(SetWidth, 1, 22);
    stylePane[currPage].add(posX, 0, 23);
    stylePane[currPage].add(SetPosX, 1, 23);
    stylePane[currPage].add(posY, 0, 24);
    stylePane[currPage].add(SetPosY, 1, 24);
    stylePane[currPage].add(arcHeight, 0, 25);
    stylePane[currPage].add(SetAH, 1, 25);
    stylePane[currPage].add(arcWidth, 0, 26);
    stylePane[currPage].add(SetAW, 1, 26);

    SetSquare.setOnAction( e->{
    Rectangle addedRect = new Rectangle();
    int R;
    int G;
    int B;
    try{
        R = Integer.parseInt( SetRed.getText());
        G = Integer.parseInt( SetGreen.getText());
        B = Integer.parseInt( SetBlue.getText());
    }
    catch (NumberFormatException y){
        R = 0;
        G = 0;
        B = 0;
    }
        if (R > 255){
            R = 255;
        }
        if (R < 0){
            R = 0;
        }
        if (G > 255){
            G = 255;
        }
        if (G < 0){
            G = 0;
        }
        if (B > 255){
            B = 255;
        }
        if (B < 0){
            B = 0;
        }

    addedRect.setFill(Color.rgb(R, G, B));

    try{
        addedRect.setRotate( Double.parseDouble( SetRotat.getText()));
    }
    catch (NumberFormatException y){}

    try {
        addedRect.setHeight(Double.parseDouble(SetHeight.getText()));
    }
    catch (NumberFormatException y){}
    try{
        addedRect.setWidth(Double.parseDouble(SetWidth.getText()));
    }
    catch (NumberFormatException y){}
    try{
        addedRect.setLayoutX(Double.parseDouble(SetPosX.getText()));
    }
    catch (NumberFormatException y){}
    try {
        addedRect.setLayoutY(Double.parseDouble(SetPosY.getText()));
    }
    catch (NumberFormatException y){}

    try {
        addedRect.setArcHeight(Double.parseDouble(SetAH.getText()));
    }
    catch (NumberFormatException y){}
    try{
        addedRect.setArcWidth(Double.parseDouble(SetAW.getText()));
    }
    catch (NumberFormatException y){}
    Shape shape = addedRect;
    thePane.getChildren().add(shape);
    addedRect.setOnMouseDragged(event -> drag(event));

    });
        Label CirRed = new Label("Enter Red");
        Label CirGreen = new Label("Enter Green");
        Label CirBlue = new Label("Enter Blue");
        Label CirX = new Label("Enter X Pos");
        Label CirY = new Label("Enter Y Pos");
        Label CirRad = new Label("Enter Radius");
        Button ChangeCir = new Button("Add Circle");

        TextField SetCirRed = new TextField("0");
        TextField SetCirGreen = new TextField("0");
        TextField SetCirBlue = new TextField("0");
        TextField SetCirX = new TextField("0");
        TextField SetCirY = new TextField("0");
        TextField SetCirRad = new TextField("0");

        stylePane[currPage].add(ChangeCir, 3,11);
        stylePane[currPage].add(SetCirRed, 3,12);
        stylePane[currPage].add(SetCirGreen, 3,13);
        stylePane[currPage].add(SetCirBlue, 3,14);
        stylePane[currPage].add(SetCirX, 3,15);
        stylePane[currPage].add(SetCirY, 3,16);
        stylePane[currPage].add(SetCirRad, 3,17);
        stylePane[currPage].add(CirRed, 2,12);
        stylePane[currPage].add(CirGreen, 2,13);
        stylePane[currPage].add(CirBlue, 2,14);
        stylePane[currPage].add(CirX, 2,15);
        stylePane[currPage].add(CirY, 2,16);
        stylePane[currPage].add(CirRad, 2,17);

        ChangeCir.setOnAction( e->{
            Circle addedCir = new Circle();
            int R;
            int G;
            int B;
            try{
                R = Integer.parseInt( SetCirRed.getText());
                G = Integer.parseInt( SetCirGreen.getText());
                B = Integer.parseInt( SetCirBlue.getText());
            }
            catch (NumberFormatException y){
                R = 0;
                G = 0;
                B = 0;
            }
            if (R > 255){
                R = 255;
            }
            if (R < 0){
                R = 0;
            }
            if (G > 255){
                G = 255;
            }
            if (G < 0){
                G = 0;
            }
            if (B > 255){
                B = 255;
            }
            if (B < 0){
                B = 0;
            }
            addedCir.setFill(Color.rgb(R, G, B));
            try{
                addedCir.setLayoutX(Double.parseDouble(SetCirX.getText()));
            }
            catch (NumberFormatException y){}
            try{
                addedCir.setLayoutY(Double.parseDouble(SetCirY.getText()));
            }
            catch (NumberFormatException y){}
            try{
                addedCir.setRadius(Double.parseDouble(SetCirRad.getText()));
            }
            catch (NumberFormatException y){}

            thePane.getChildren().add(addedCir);
            addedCir.setOnMouseDragged(event -> drag(event));
        });
        Label imgAdress = new Label("Enter Image WebAddress");
        Label imgX = new Label("Enter X Pos");
        Label imgY = new Label("Enter Y Pos");
        Label imgSX = new Label("Enter X Scale");
        Label imgSY = new Label("Enter Y Scale");
        Label imgRotat = new Label("Enter Rotation");
        TextField setImgAdress = new TextField("");
        TextField setImgX = new TextField("0");
        TextField setImgY = new TextField("0");
        TextField setImgSX = new TextField("1");
        TextField setImgSY = new TextField("1");
        TextField setImgRotat = new TextField("0");
        Button setImg = new Button("Add Image");
        stylePane[currPage].add(setImg, 3, 18);
        stylePane[currPage].add(imgAdress, 2, 19);
        stylePane[currPage].add(imgX, 2, 20);
        stylePane[currPage].add(imgY, 2, 21);
        stylePane[currPage].add(imgSX, 2, 22);
        stylePane[currPage].add(imgSY, 2, 23);
        stylePane[currPage].add(imgRotat, 2, 24);
        stylePane[currPage].add(setImgAdress, 3, 19);
        stylePane[currPage].add(setImgX, 3, 20);
        stylePane[currPage].add(setImgY, 3, 21);
        stylePane[currPage].add(setImgSX, 3, 22);
        stylePane[currPage].add(setImgSY, 3, 23);
        stylePane[currPage].add(setImgRotat, 3, 24);
        setImg.setOnAction( e->{
            try {
                Image img = new Image(setImgAdress.getText());
                ImageView imgView = new ImageView(img);
                try {
                    imgView.setLayoutX(Double.parseDouble(setImgX.getText()));
                } catch (NumberFormatException y) {
                    imgView.setLayoutX(0);
                }
                try {
                    imgView.setLayoutY(Double.parseDouble(setImgY.getText()));
                } catch (NumberFormatException y) {
                    imgView.setLayoutY(0);
                }
                double wid = img.getWidth();
                double hi = img.getHeight();
                double Ly = imgView.getLayoutY();
                double Lx = imgView.getLayoutX();
                double scX;
                double scY;
                try {
                    scX = Double.parseDouble(setImgSX.getText());

                } catch (NumberFormatException y) {
                    scX = 0;
                }
                try {
                    scY = Double.parseDouble(setImgSY.getText());
                } catch (NumberFormatException y) {
                    scY = 0;
                }
                if(scX < .5){
                    scX = .5;
                }
                if(scY < .5){
                    scY = .5;
                }
                imgView.setScaleX(scX);
                imgView.setScaleY(scY);

                imgView.setLayoutX(Lx - ((wid - (wid * imgView.getScaleX())) / 2) );
                imgView.setLayoutY(Ly - ((hi - (hi * imgView.getScaleY())) / 2) );

                thePane.getChildren().add(imgView);
                imgView.setOnMouseDragged(event -> dragImg(event, img, imgView));
            }
            catch (IllegalArgumentException y){}
        });
        Button addLabel = new Button("Add Text");
        TextField labelSize = new TextField("12");
        TextField labelFont = new TextField("System Regular");
        TextField labelX = new TextField("0");
        TextField labelY = new TextField("0");
        TextField labelRotate = new TextField("0");
        TextField Red = new TextField("0");
        TextField Green = new TextField("0");
        TextField Blue = new TextField("0");
        TextField setText = new TextField("");

        Label yourText = new Label("Enter Text");
        Label redL = new Label("Enter Red");
        Label greenL = new Label("Enter Green");
        Label blueL = new Label("Enter Blue");
        Label size = new Label("Enter Font Size");
        Label FontType = new Label("Enter Font Type");
        Label newX = new Label("Enter X Pos");
        Label newY = new Label("Enter Y Pos");
        Label rotate = new Label("Enter Rotation Degree");

        addLabel.setOnAction( e->{
            Label newlabel = new Label("");
            newlabel.setText(setText.getText());


            try {
                newlabel.setFont(Font.font(labelFont.getText(), Integer.parseInt(labelSize.getText())));
            }
            catch (NumberFormatException y){
                newlabel.setFont(Font.font(labelFont.getText()));
            }
            int R;
            int G;
            int B;
            try {
                R = Integer.parseInt(Red.getText());
                G = Integer.parseInt(Green.getText());
                B = Integer.parseInt(Blue.getText());
            }
            catch (NumberFormatException y){
                R = 0;
                G = 0;
                B = 0;
            }
            if (R > 255){
                R = 255;
            }
            if (R < 0){
                R = 0;
            }
            if (G > 255){
                G = 255;
            }
            if (G < 0){
                G = 0;
            }
            if (B > 255){
                B = 255;
            }
            if (B < 0){
                B = 0;
            }
            newlabel.setTextFill(Color.rgb(R, G, B));

            try {
                newlabel.setTranslateX(Integer.parseInt(labelX.getText()));
                newlabel.setTranslateY(Integer.parseInt(labelY.getText()));
            }
            catch (NumberFormatException y){
            }

            try {
                newlabel.setRotate(Double.parseDouble(labelRotate.getText()));
            }
            catch (NumberFormatException y){
            }
            thePane.getChildren().add(newlabel);

            newlabel.setOnMouseDragged(event -> drag(event));
        });

        stylePane[currPage].add(addLabel, 5, 16);
        stylePane[currPage].add(size, 4, 17);
        stylePane[currPage].add(labelSize, 5, 17);
        stylePane[currPage].add(FontType, 4, 18);
        stylePane[currPage].add(labelFont, 5, 18);
        stylePane[currPage].add(labelX, 5, 19);
        stylePane[currPage].add(newX, 4, 19);
        stylePane[currPage].add(labelY, 5, 20);
        stylePane[currPage].add(newY, 4, 20);
        stylePane[currPage].add(rotate, 4 , 21);
        stylePane[currPage].add(labelRotate, 5, 21);
        stylePane[currPage].add(Red, 5, 22);
        stylePane[currPage].add(redL, 4, 22);
        stylePane[currPage].add(Green, 5 , 23);
        stylePane[currPage].add(greenL, 4 , 23);
        stylePane[currPage].add(Blue, 5 , 24);
        stylePane[currPage].add(blueL, 4 , 24);
        stylePane[currPage].add(yourText, 4, 25);
        stylePane[currPage].add(setText, 5, 25);

        Button manual = new Button("Open User manual");
        manual.setLayoutX(1000);
        manual.setLayoutY(700);

        stylePane[currPage].add(manual, 8, 25);

        manual.setOnAction( e->{
            manual();
        });

        Polygon polygon = new Polygon();
        TextField polyX = new TextField("0");
        TextField polyY = new TextField("0");
        TextField RotDeg = new TextField("0");
        TextField polyR = new TextField("0");
        TextField polyG = new TextField("0");
        TextField polyB = new TextField("0");

        TextField P1 = new TextField("0");
        TextField P2 = new TextField("0");

        Label LpolyX = new Label("Enter X Pos");
        Label LpolyY = new Label("Enter Y Pos");
        Label LRotDeg = new Label("Enter Rotation Degree");
        Label LpolyR = new Label("Enter Red");
        Label LpolyG = new Label("Enter Green");
        Label LpolyB = new Label("Enter Blue");

        Label LP1 = new Label("Enter X Point");
        Label LP2 = new Label("Enter Y Point");

        stylePane[currPage].add(polyR, 7, 17);
        stylePane[currPage].add(polyG, 7, 18);
        stylePane[currPage].add(polyB, 7, 19);
        stylePane[currPage].add(RotDeg, 7, 20);

        stylePane[currPage].add(P1, 7, 22);
        stylePane[currPage].add(P2, 7, 23);


        stylePane[currPage].add(LpolyR, 6, 17);
        stylePane[currPage].add(LpolyG, 6, 18);
        stylePane[currPage].add(LpolyB, 6, 19);
        stylePane[currPage].add(LRotDeg, 6, 20);

        stylePane[currPage].add(LP1, 6, 22);
        stylePane[currPage].add(LP2, 6, 23);
        Button addPoint = new Button("Add Point");
        Button AddPoly = new Button("Add Polygon");
        stylePane[currPage].add(addPoint, 7, 21);
        stylePane[currPage].add(AddPoly, 7, 16);

        addPoint.setOnAction(e->{
            double point1;
            try {
                 point1 = Double.parseDouble(P1.getText());
            }
            catch (NumberFormatException y){
                 point1 = 0;
            }
            double point2;
            try {
                point2 = Double.parseDouble(P2.getText());
            }
            catch (NumberFormatException y){
                point2 = 0;
            }
            polygon.getPoints().addAll(new Double[]{
                    point1, point2
            });
        });

        AddPoly.setOnAction(e->{
            System.out.println(polygon.getPoints());
            Polygon newgon = new Polygon();
            newgon.getPoints().addAll(polygon.getPoints());
            polygon.getPoints().clear();
            System.out.println(polygon.getPoints());


            newgon.setRotate(Double.parseDouble(RotDeg.getText()));
            int R;
            int G;
            int B;
            try {
                R = Integer.parseInt(polyR.getText());
                G = Integer.parseInt(polyG.getText());
                B = Integer.parseInt(polyB.getText());
            }
            catch (NumberFormatException u){
                R =0;
                G =0;
                B =0;
            }
            if (R > 255){
                R = 255;
            }
            if (R < 0){
                R = 0;
            }
            if (G > 255){
                G = 255;
            }
            if (G < 0){
                G = 0;
            }
            if (B > 255){
                B = 255;
            }
            if (B < 0){
                B = 0;
            }

            newgon.setFill(Color.rgb(R, G, B));
            newgon.setLayoutY(newgon.getLayoutY());
            newgon.setLayoutX(newgon.getLayoutX());
            System.out.println(newgon.getLayoutX());
            System.out.println(newgon.getLayoutY());
            thePane.getChildren().add(newgon);
            newgon.setOnMouseDragged(event -> dragGon(event));


        });
        {
            BackgroundFill backgroundFill =
                    new BackgroundFill(
                            Color.WHITE,

                            new CornerRadii(0),

                            new Insets(0));
            Background background = new Background(backgroundFill);
        stylePane[currPage].setBackground(background);}

    }
    public void manual(){

        UserManual = new Stage();
        UserManual.close();
        Pane manualPane = new Pane();
        Scene manualScene = new Scene(manualPane,  290, 700);
        UserManual.setScene(manualScene);
        UserManual.show();
        UserManual.setTitle("User Manual");

        Label labelText = new Label("Text Options");
        labelText.setFont(Font.font("Times new Roman",30));

        Label textDefinition = new Label("There are 4 different Texts you can change Text 1, 2, 3, and" +
                " new text.");
        textDefinition.setFont(Font.font("times new roman",15));

        labelText.setLayoutY(0);

        textDefinition.setMaxWidth(275);
        textDefinition.setWrapText(true);
        textDefinition.setLayoutY(50);

        Label ExplainFSize = new Label("Font Size will change how large the font is.");
        ExplainFSize.setFont(Font.font("times new roman", 15));
        ExplainFSize.setMaxWidth(275);
        ExplainFSize.setWrapText(true);
        ExplainFSize.setLayoutY(100);

        Label ExplainFType = new Label("Font Type will change what font the text is.");
        ExplainFType.setFont(Font.font("times new roman", 15));
        ExplainFType.setMaxWidth(275);
        ExplainFType.setWrapText(true);
        ExplainFType.setLayoutY(125);

        Label ExplainXANDY = new Label("By default your Window will be 1350 by 700, Setting the X will change " +
                "where on the horizontal axis it is set, Setting the Y will change where on the vertical" +
                " axis it is set (Higher X more to the right) (Higher Y more downwards).");
        ExplainXANDY.setFont(Font.font("times new roman", 15));
        ExplainXANDY.setMaxWidth(275);
        ExplainXANDY.setWrapText(true);
        ExplainXANDY.setLayoutY(150);

        Label ExplainWrap = new Label("By default Wrap width is 0 which means it wont wrap, However by " +
                "increasing the number you will set how wide the text will be before wrapping under itself.");
        ExplainWrap.setFont(Font.font("times new roman", 15));
        ExplainWrap.setMaxWidth(275);
        ExplainWrap.setWrapText(true);
        ExplainWrap.setLayoutY(275);

        Label ExplainRot = new Label("Setting the rotation will rotate Node by that many degrees clockwise.");
        ExplainRot.setFont(Font.font("times new roman", 15));
        ExplainRot.setMaxWidth(275);
        ExplainRot.setWrapText(true);
        ExplainRot.setLayoutY(360);

        Label ExplainRGB = new Label("Red Green Blue Numbers go from 0 to 255 and they set the color of the text." +
                " The Higher the Number the more of" +
                " that color there will be.");
        ExplainRGB.setFont(Font.font("times new roman", 15));
        ExplainRGB.setMaxWidth(275);
        ExplainRGB.setWrapText(true);
        ExplainRGB.setLayoutY(405);

        Label ExplainText = new Label("Setting the text will change that nodes text.");
        ExplainText.setFont(Font.font("times new roman", 15));
        ExplainText.setMaxWidth(275);
        ExplainText.setWrapText(true);
        ExplainText.setLayoutY(495);

        Label future = new Label("Any repeat settings will not be explained again.");
        future.setFont(Font.font("times new roman", 15));
        future.setMaxWidth(275);
        future.setWrapText(true);
        future.setLayoutY(520);

        Button next = new Button("Next>>");
        next.setLayoutY(600);
        next.setLayoutX(230);

        Pane ExplainBtt = new Pane();
        Scene BTTScene = new Scene(ExplainBtt, 290, 700);

        next.setOnAction( e->{
            UserManual.setScene(BTTScene);
        });



        manualPane.getChildren().addAll(labelText, textDefinition, ExplainXANDY, ExplainWrap, ExplainRot,
                ExplainRGB, ExplainText, next, future, ExplainFSize, ExplainFType);

        Label labelButton = new Label("Button Options");
        labelButton.setFont(Font.font("Times new Roman",30));
        labelButton.setLayoutY(0);

        Label allBtt = new Label("There are 4 different Buttons you can change Next, Back, New Page, and Edit.");
        allBtt.setFont(Font.font("times new roman",15));
        allBtt.setMaxWidth(275);
        allBtt.setWrapText(true);
        allBtt.setLayoutY(50);

        Label BttWidHeight = new Label("Changing minimum Width and Height will change the absolute smallest the button can" +
                " be on the X and Y axis respectively. ");
        BttWidHeight.setFont(Font.font("times new roman",15));
        BttWidHeight.setMaxWidth(275);
        BttWidHeight.setWrapText(true);
        BttWidHeight.setLayoutY(100);

        Label CornRad = new Label("Changing Corner Radius will set the radius for the Buttons corners.");
        CornRad.setFont(Font.font("times new roman",15));
        CornRad.setMaxWidth(275);
        CornRad.setWrapText(true);
        CornRad.setLayoutY(165);

        Pane square = new Pane();
        Scene squareScene = new Scene(square, 290, 700);

        Button bttnext = new Button("Next>>");
        bttnext.setLayoutY(600);
        bttnext.setLayoutX(230);

        Button bttback = new Button("<<Back");
        bttback.setLayoutY(600);

        ExplainBtt.getChildren().addAll(labelButton, allBtt, BttWidHeight, CornRad,bttnext, bttback);

        bttnext.setOnAction( e->{
            UserManual.setScene(squareScene);
        });

        bttback.setOnAction( e->{
            UserManual.setScene(manualScene);
        });

        Label squareLabel = new Label("Square options");
        squareLabel.setFont(Font.font("Times new Roman",30));
        squareLabel.setLayoutY(0);

        Label SquareArc = new Label("Changing Square Arc Width and Height will get the corners an arc with the set width and height.");
        SquareArc.setFont(Font.font("times new roman",15));
        SquareArc.setMaxWidth(275);
        SquareArc.setWrapText(true);
        SquareArc.setLayoutY(50);

        Button sqNext = new Button("Next>>");

        Button sqBack = new Button("<<Back");

        sqNext.setLayoutX(230);
        sqNext.setLayoutY(600);
        sqBack.setLayoutY(600);

        Pane CirPane = new Pane();

        Scene cirScene = new Scene(CirPane,290, 700);

        sqNext.setOnAction( e->{
            UserManual.setScene(cirScene);
        });

        sqBack.setOnAction( e->{
            UserManual.setScene(BTTScene);
        });

        square.getChildren().addAll(squareLabel, SquareArc, sqBack, sqNext);

        Label cirLabel = new Label("Circle options");
        cirLabel.setFont(Font.font("Times new Roman",30));
        cirLabel.setLayoutY(0);

        Label cirRad = new Label("Setting the circles radius will make the circle twice the width of that number.");
        cirRad.setFont(Font.font("times new roman",15));
        cirRad.setMaxWidth(275);
        cirRad.setWrapText(true);
        cirRad.setLayoutY(50);

        Button cirNext = new Button("Next>>");

        Button cirBack = new Button("<<Back");

        cirNext.setLayoutX(230);
        cirNext.setLayoutY(600);

        cirBack.setLayoutY(600);
        cirBack.setOnAction( e->{
            UserManual.setScene(squareScene);
        });

        Pane imgPane = new Pane();
        Scene imageScene = new Scene(imgPane, 290, 700);

        cirNext.setOnAction( e->{
            UserManual.setScene(imageScene);
        });

        CirPane.getChildren().addAll(cirNext, cirBack, cirRad, cirLabel);

        Label imgLabel = new Label("Image options");
        imgLabel.setFont(Font.font("Times new Roman",30));
        imgLabel.setLayoutY(0);

        Label imgWeb = new Label("To get an image WebAddress go to google find an image right click and select " +
                "'Copy Image Address' then paste into 'Enter Image WebAddress' TextField.");
        imgWeb.setFont(Font.font("times new roman",15));
        imgWeb.setMaxWidth(275);
        imgWeb.setWrapText(true);
        imgWeb.setLayoutY(50);

        Label imgScale = new Label("The image scale options X and Y wil set the scale of the width and height" +
                " respectively the default is one and will be the same scale as the image you pasted. (Note Image Scale" +
                " does not go below .5 and works less well the lower you go down)");
        imgScale.setFont(Font.font("times new roman",15));
        imgScale.setMaxWidth(275);
        imgScale.setWrapText(true);
        imgScale.setLayoutY(140);

        Button imgBack = new Button("<<Back");
        imgBack.setLayoutY(600);
        imgBack.setOnAction(e->{
            UserManual.setScene(cirScene);
        });
        Button imgNext = new Button("Next>>");
        imgNext.setLayoutX(230);
        imgNext.setLayoutY(600);
        Pane polyPane = new Pane();
        Scene polyScene = new Scene(polyPane, 290, 700);
        imgNext.setOnAction(e->{
            UserManual.setScene(polyScene);
        });

        imgPane.getChildren().addAll(imgBack, imgWeb, imgLabel, imgScale, imgNext);

        Label polyLabel = new Label("Polygon options");
        polyLabel.setFont(Font.font("Times new Roman",30));
        polyLabel.setLayoutY(0);

        Label polyPoint = new Label("To add a Polygon you will First type in a set of points below the " +
                "'Add Point' button the points locations are at the x and y coordinates, then you may continue adding points " +
                "Once you have added all the points you want click 'Add Polygon' button, this will reset the points and" +
                " now you can add more points. ");
        polyPoint.setFont(Font.font("times new roman",15));
        polyPoint.setMaxWidth(275);
        polyPoint.setWrapText(true);
        polyPoint.setLayoutY(50);
        Button polyBack = new Button("<<Back");
        polyBack.setLayoutY(600);
        polyBack.setOnAction(e->{
            UserManual.setScene(imageScene);
        });
        polyPane.getChildren().addAll(polyLabel, polyPoint, polyBack);




    }
    public void delete(){styleStage.close();}
    public void drag(MouseEvent event) {
        Node n = (Node)event.getSource();
        n.setTranslateX(n.getTranslateX() + event.getX());
        n.setTranslateY(n.getTranslateY() + event.getY());
    }

    public void dragGon(MouseEvent event) {
        Node n = (Node)event.getSource();
        n.setTranslateX(n.getTranslateX() + event.getX() - 75);
        n.setTranslateY(n.getTranslateY() + event.getY() - 200);
    }
    public void dragImg(MouseEvent event, Image img, ImageView imgView) {
        Node n = (Node)event.getSource();
        n.setTranslateX(n.getTranslateX() + event.getX());
        n.setTranslateY(n.getTranslateY() + event.getY());
    }

}
