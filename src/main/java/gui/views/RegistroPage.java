package gui.views;

import entities.User;
import helper.HibernateController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RegistroPage extends BorderPane {
  TextField usuario;
  PasswordField senha;

  public RegistroPage() {

    // Barra de navegação
    HBox navBar = createNavBar();

    // Conteúdo principal
    VBox mainContent = createMainContent();

    // Configuração dos componentes no layout
    this.setTop(navBar);
    this.setCenter(mainContent);
  }

  private HBox createNavBar() {
    HBox navBar = new HBox();
    navBar.setPadding(new Insets(15, 12, 15, 12));
    navBar.setStyle("-fx-background-color: #E50914;");

    Image netflixLogo = new Image(getClass().getResourceAsStream("../assets/iconNetflix.png"));
    ImageView logoView = new ImageView(netflixLogo);
    logoView.setFitHeight(30);
    logoView.setPreserveRatio(true);
    Button homeButton = new Button("", logoView);

    Button loginButton = new Button("Login");

    String buttonStyle = "-fx-font-size: 14px; -fx-background-color: #000000; -fx-text-fill: #FFFFFF;";
    String buttonHoverStyle = "-fx-font-size: 14px; -fx-background-color: #555555; -fx-text-fill: #FFFFFF;";

    homeButton.setStyle(buttonStyle);
    homeButton.setOnMouseEntered(e -> homeButton.setStyle(buttonHoverStyle));
    homeButton.setOnMouseExited(e -> homeButton.setStyle(buttonStyle));

    loginButton.setStyle(buttonStyle);
    loginButton.setOnMouseEntered(e -> loginButton.setStyle(buttonHoverStyle));
    loginButton.setOnMouseExited(e -> loginButton.setStyle(buttonStyle));
    loginButton.setOnAction(e -> switchToLoginPage(loginButton));

    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    navBar.getChildren().addAll(homeButton, spacer, loginButton);
    return navBar;
  }

  private VBox createMainContent() {
    VBox mainContent = new VBox();
    mainContent.setAlignment(Pos.CENTER_RIGHT);
    mainContent.setPadding(new Insets(50, 50, 0, 50));
    mainContent.setSpacing(50);

    Image backgroundImage = new Image(getClass().getResourceAsStream("../assets/backgroundRegistro.png"));
    BackgroundSize bgSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
    BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgSize);
    mainContent.setBackground(new Background(bgImage));

    Label loginLabel = new Label("Registrar-se");
    loginLabel.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 40));
    loginLabel.setStyle("-fx-text-fill: #FFFFFF;");

    usuario = new TextField();
    usuario.setPromptText("E-mail");
    usuario.setMinHeight(40);
    usuario.setMaxWidth(300);

    senha = new PasswordField();
    senha.setPromptText("Senha");
    senha.setMinHeight(40);
    senha.setMaxWidth(300);

    Button loginButton = new Button("Registrar");
    loginButton.setStyle("-fx-font-size: 16px; -fx-background-color: #E50914; -fx-text-fill: #FFFFFF;");
    loginButton.setMaxHeight(40);
    loginButton.setMaxWidth(300);
    loginButton.setOnAction(e -> register(loginButton));

    mainContent.getChildren().addAll(loginLabel, usuario, senha, loginButton);

    return mainContent;
  }

  private void switchToLoginPage(Button button) {
    Stage stage = (Stage) button.getScene().getWindow();
    LoginPage loginPage = new LoginPage();
    Scene loginScene = new Scene(loginPage, stage.getWidth(), stage.getHeight());
    stage.setScene(loginScene);
  }

  private void register(Button button) {
    User user = new User(usuario.getText(), senha.getText(), "user", "path");
    HibernateController.registerUser(user);

    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Cadastro confirmado!");
    alert.setContentText("Cadastro confirmado, o usuário " + user.email + " pode se conectar agora.");
    alert.show();
  }
}
