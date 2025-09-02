package pengu.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pengu.Pengu;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Pengu pengu;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image penguImage = new Image(this.getClass().getResourceAsStream("/images/DaPengu.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Pengu instance
     */
    public void setPengu(Pengu d) {
        pengu = d;
    }

    /**
     * Creates one dialog box showing Pengu's startup message.
     */
    @FXML
    public void start() {
        dialogContainer.getChildren().addAll(
                DialogBox.getPenguDialog(pengu.getStartupMessage(), penguImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Pengu's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = pengu.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPenguDialog(response, penguImage)
        );
        userInput.clear();

        if (!pengu.isRunning()) {
            Platform.exit();
        }
    }
}
