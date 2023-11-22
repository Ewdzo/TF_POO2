package gui.views;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
    navBar.setStyle("-fx-background-color: #E50914;"); // Cor de fundo da barra de navegação

    // Botão Home com logo da Netflix
    Image netflixLogo = new Image("./assets/iconNetflix.png"); // Substitua com o caminho correto da imagem
    ImageView logoView = new ImageView(netflixLogo);
    logoView.setFitHeight(30); // Ajuste conforme necessário
    logoView.setPreserveRatio(true);
    Button homeButton = new Button("", logoView); // Botão com a logo

    // Botão Login
    Button loginButton = new Button("Login");

    // Estilização dos botões
    String buttonStyle = "-fx-font-size: 14px; -fx-background-color: #000000; -fx-text-fill: #FFFFFF;";
    String buttonHoverStyle = "-fx-font-size: 14px; -fx-background-color: #555555; -fx-text-fill: #FFFFFF;";

    homeButton.setStyle(buttonStyle);
    homeButton.setOnMouseEntered(e -> homeButton.setStyle(buttonHoverStyle));
    homeButton.setOnMouseExited(e -> homeButton.setStyle(buttonStyle));

    loginButton.setStyle(buttonStyle);
    loginButton.setOnMouseEntered(e -> loginButton.setStyle(buttonHoverStyle));
    loginButton.setOnMouseExited(e -> loginButton.setStyle(buttonStyle));
    loginButton.setOnAction(e -> switchToLoginPage(loginButton));

    // Espaço flexível entre os botões
    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    navBar.getChildren().addAll(homeButton, spacer, loginButton);
    return navBar;
  }

  private VBox createMainContent() {
    VBox mainContent = new VBox();
    mainContent.setAlignment(Pos.CENTER_RIGHT);
    mainContent.setPadding(new Insets(50, 50, 0, 50)); // Ajuste os espaços conforme necessário
    mainContent.setSpacing(50); // Espaçamento de 20px entre os elementos

    // Carregar a imagem de fundo
    Image backgroundImage = new Image("./assets/backgroundRegistro.png"); // Substitua com o caminho correto da imagem
    BackgroundSize bgSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
    BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgSize);
    mainContent.setBackground(new Background(bgImage));

    // Elementos de login
    Label loginLabel = new Label("Registrar-se");
    loginLabel.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 40));
    loginLabel.setStyle("-fx-text-fill: #FFFFFF;");

    TextField usuario = new TextField();
    usuario.setPromptText("E-mail");
    usuario.setMinHeight(40); // Altura mínima do campo de texto
    usuario.setMaxWidth(300); // Largura máxima do campo de texto

    PasswordField senha = new PasswordField();
    senha.setPromptText("Senha");
    senha.setMinHeight(40);
    senha.setMaxWidth(300); // Largura máxima do campo de senha

    Button loginButton = new Button("Registrar");
    loginButton.setStyle("-fx-font-size: 16px; -fx-background-color: #E50914; -fx-text-fill: #FFFFFF;");
    loginButton.setMaxHeight(40); // Altura máxima do botão
    loginButton.setMaxWidth(300); // Largura máxima do botão
    loginButton.setOnAction(e -> switchToLoginPage(loginButton));

    // Adicionar elementos ao VBox
    mainContent.getChildren().addAll(loginLabel, usuario, senha, loginButton);

    return mainContent;
  }

  private void switchToLoginPage(Button button) {
    Stage stage = (Stage) button.getScene().getWindow();
    LoginPage loginPage = new LoginPage();
    Scene loginScene = new Scene(loginPage, stage.getWidth(), stage.getHeight());
    stage.setScene(loginScene);
  }
}
