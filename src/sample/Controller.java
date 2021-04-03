package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Controller {
    @FXML
    private GridPane mainPane;
    private Stage mainStage;
    private int questionNum;
    private Button faceless;
    private Button council;
    private Button warband;
    private Button united;
    private Label questionLabel;
    private int facelessPts;
    private int councilPts;
    private int warbandPts;
    private int unitedPts;
    private String tieBreaker1;
    private String tieBreaker2;
    private String tieBreaker3;


    @FXML
    protected void HandleStartButton(ActionEvent event)
    {
        try
        {
            questionNum = 0; facelessPts = 0; councilPts = 0; warbandPts = 0; unitedPts = 0;
            mainStage = (Stage) mainPane.getScene().getWindow();
            BorderPane bp = new BorderPane();
            questionLabel = new Label("Question 1\n" +
                    "Complete the phrase:\n" +
                    "I'd die without my...");
            questionLabel.setFont(Font.font(48));
            questionLabel.setAlignment(Pos.CENTER);
            questionLabel.setPrefWidth(1280);
            questionLabel.setWrapText(true);

            HBox answerBox = new HBox();
            answerBox.setSpacing(100);
            faceless = new Button("Freedom"){
                @Override
                public String toString() {
                    return "faceless";
                }
            }; faceless.setOnAction(this::HandleAnswerButton); faceless.setPrefSize(200,75); faceless.setFont(Font.font(14)); faceless.setWrapText(true);
            council = new Button("Knowledge")
            {
                @Override
                public String toString() {
                    return "council";
                }
            }; council.setOnAction(this::HandleAnswerButton); council.setPrefSize(200,75); council.setFont(Font.font(14)); council.setWrapText(true);
            warband = new Button("Talent")
            {
                @Override
                public String toString() {
                    return "warband";
                }
            }; warband.setOnAction(this::HandleAnswerButton); warband.setPrefSize(200,75); warband.setFont(Font.font(14)); warband.setWrapText(true);
            united = new Button("Hope")
            {
                @Override
                public String toString() {
                    return "united";
                }
            }; united.setOnAction(this::HandleAnswerButton); united.setPrefSize(200,75); united.setFont(Font.font(14)); united.setWrapText(true);
            answerBox.getChildren().addAll(faceless,council,warband,united);
            answerBox.alignmentProperty().set(Pos.CENTER);
            bp.setTop(questionLabel);
            bp.setCenter(answerBox);
            Scene scene = new Scene(bp,1280,800);
            mainStage.setScene(scene);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    protected void HandleAnswerButton(ActionEvent event) {
        questionNum++;
        String answerHouse = event.getSource().toString();
        if (questionNum == 8)
        {
            tieBreaker1 = answerHouse;
        }
        else if (questionNum == 1)
        {
            tieBreaker2 = answerHouse;
        }
        else if (questionNum == 3)
        {
            tieBreaker3 = answerHouse;
        }
        switch (answerHouse) {
            case "faceless":
                facelessPts++;
                break;
            case "council":
                councilPts++;
                break;
            case "warband":
                warbandPts++;
                break;
            case "united":
                unitedPts++;
                break;
        }
        switch (questionNum) {
            case 1:
                questionLabel.setText("Question 2\n" +
                        "Select a response:\n" +
                        "Pick an animal.");
                faceless.setText("Octopus");
                council.setText("Owl");
                warband.setText("Leopard");
                united.setText("Elephant");
                break;
            case 2:
                questionLabel.setText("Question 3\n" +
                        "Complete the phrase:\n" +
                        "Wars are won...");
                faceless.setText("By the unpredictable");
                council.setText("In the planning room");
                warband.setText("In the heat of battle");
                united.setText("With unbreaking resolve");
                break;
            case 3:
                questionLabel.setText("Question 4\n" +
                        "Complete the phrase:\n" +
                        "The perfect team would never...");
                faceless.setText("Tell me what to do");
                council.setText("Lose focus");
                warband.setText("Feed my opponent");
                united.setText("Give up");
                break;
            case 4:
                questionLabel.setText("Question 5\n" +
                        "Select a response:\n" +
                        "The enemy team is winning on all fronts. What do you do?");
                faceless.setText("This is right where I want them– I'll explain later");
                council.setText("Outmaneuver them to steal some objectives");
                warband.setText("Go pentakill them, like I always do");
                united.setText("Rally my team for a final stand");
                break;
            case 5:
                questionLabel.setText("Question 6\n" +
                        "Select a response:\n" +
                        "What's your favorite time of day?");
                faceless.setText("Night");
                council.setText("Dawn");
                warband.setText("Dusk");
                united.setText("Day");
                break;
            case 6:
                questionLabel.setText("Question 7\n" +
                        "Select a response:\n" +
                        "Which of these sounds like you?");
                faceless.setText("\"Trust me. I'm not trolling\"");
                council.setText("\"Can we please group\"");
                warband.setText("\"ez\"");
                united.setText("\"WINNABLE\"");
                break;
            case 7:
                questionLabel.setText("Question 8\n" +
                        "Complete the phrase:\n" +
                        "I want to be seen as a(n)");
                faceless.setText("Crafty wildcard");
                council.setText("Brilliant tactician");
                warband.setText("Elite fighter");
                united.setText("Selfless leader");
                break;
            case 8:


                try {
                    Stage popup = new PopupWindow("Congrats! Your House is: The " + calculateHouse(facelessPts, councilPts, warbandPts,unitedPts));
                    popup.show();
                    popup.centerOnScreen();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    break;

                }
        }
    }
        private String calculateHouse ( int fPts, int cPnts, int wPts, int uPts)
        {
            class Node
            {
                int pts;
                String house;
                Node(int pts1, String house1)
                {
                    this.pts = pts1; this.house = house1;
                }
            }
            Node fNode = new Node(fPts,"faceless");
            Node cNode = new Node(cPnts, "council" );
            Node wNode = new Node(wPts, "warband");
            Node uNode = new Node (uPts, "united");
            ArrayList<Node> houses = new ArrayList<Node>();
            houses.add(fNode); houses.add(cNode); houses.add(wNode); houses.add(uNode);
            for (int i = 0; i < 3; i++)
            {
                for (int j = i+1; j < 4; j++)
                {
                    if (houses.get(i).pts > houses.get(j).pts)
                    {
                        Node temp = houses.get(j);
                        houses.set(j, houses.get(i));
                        houses.set(i, temp);
                    }
                }
            }
            if (houses.get(3).pts > houses.get(2).pts)
            {
                return houses.get(3).house + " with " + houses.get(3).pts + "\n" +
                        "The Rest of Your Scores were: \n" +
                        "The " + houses.get(2).house + " with " + houses.get(2).pts + " points\n" +
                        "The " + houses.get(1).house + " with " + houses.get(1).pts + " points\n" +
                        "The " + houses.get(0).house + " with " + houses.get(0).pts + " points\n";
            }
            String rtrnString = "";
            for (int i = 0; i < houses.size(); i++)
            {
                if (houses.get(i).pts < houses.get(houses.size() - 1).pts)
                {
                    rtrnString  = "\nThe " + houses.get(0).house + " with " + houses.get(0).pts + " points" + rtrnString;
                    houses.remove(i);
                    i--;
                }
            }

            for (int i = 0; i < houses.size(); i++)
            {
                if (houses.get(i).house.equals(tieBreaker1))
                {
                    for (int j = 0; j <houses.size();j++)
                    {
                        if (j != i)
                        {
                            rtrnString = "\nThe " + houses.get(j).house + " with " + houses.get(j).pts + " points" + rtrnString;
                        }
                    }

                    return houses.get(i).house + " with " + (houses.get(i).pts + 1)+
                            " points\nThe Rest of Your Scores were: " +rtrnString ;
                }
            }
            for (int i = 0; i < houses.size(); i++)
            {
                if (houses.get(i).house.equals(tieBreaker2))
                {
                    for (int j = 0; j <houses.size();j++)
                    {
                        if (j != i)
                        {
                            rtrnString = "\nThe " + houses.get(j).house + " with " + houses.get(j).pts + " points" + rtrnString;
                        }
                    }

                    return houses.get(i).house + " with " + (houses.get(i).pts + 1)+
                            " points\nThe Rest of Your Scores were: " +rtrnString ;
                }
            }
            for (int i = 0; i < houses.size(); i++)
            {
                if (houses.get(i).house.equals(tieBreaker3))
                {
                    for (int j = 0; j <houses.size();j++)
                    {
                        if (j != i)
                        {
                            rtrnString = "\nThe " + houses.get(j).house + " with " + houses.get(j).pts + " points" + rtrnString;
                        }
                    }

                    return houses.get(i).house + " with " + (houses.get(i).pts + 1)+
                            " points\nThe Rest of Your Scores were: " +rtrnString ;
                }
            }
            return "Coder Messed Up Somewhere????";
        }
}
