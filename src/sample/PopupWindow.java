package sample;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;

public class PopupWindow extends Stage {
    public PopupWindow(String house) throws FileNotFoundException {
        // Vbox To Hold Content
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(50);
        vbox.setStyle("-fx-background-color: #DCDCDC; -fx-border-width: 2px; -fx-border-color: #33383E");
        DropShadow shadow = new DropShadow();
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setRadius(10);
        vbox.setEffect(shadow);

        // Text Content
        Label content = new Label(house);
        content.setFont(new Font("Yu Gothic UI", 16));
        content.setStyle("-fx-font-weight: bold; -fx-text-alignment: center;");
        content.setWrapText(true);

        // Create Button
        Button okay = new Button("Close Quiz");
        okay.setOnAction(this::HandleCloseButton);
        okay.setPrefSize(80, 40);
        okay.setEffect(shadow);
        okay.setStyle("-fx-background-radius: 30; -fx-background-color: #8D9AA6");

        // Add Content to VBox
        vbox.getChildren().addAll(content, okay);

        // Create Scene
        Scene scene = new Scene(vbox, 450, 450);
        setScene(scene);

        // Customize Window Settings
        initModality(Modality.APPLICATION_MODAL);
        initStyle(StageStyle.UNDECORATED);
        setResizable(false);
    }
    protected void HandleCloseButton(ActionEvent event)
    {
        System.exit(0);
    }
}