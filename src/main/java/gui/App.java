package gui;

import java.io.IOException;
import java.util.GregorianCalendar;

import gui.views.HomePage;
import gui.views.LoginPage;
import helper.Example;
import helper.HibernateController;
import helper.HibernateManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class App extends Application{

	@Override
  	public void start(Stage primaryStage) {
		primaryStage.setTitle("Sistema de Compartilhamento de Filmes e Séries");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("assets/iconNetflix.png")));

		// Layout principal
		BorderPane root = new BorderPane();

		// Barra de navegação
		HBox navBar = createNavBar();

		// Conteúdo principal
		VBox mainContent = createMainContent();

		// Configuração dos componentes no layout
		root.setTop(navBar);
		root.setCenter(mainContent);

		// Configuração da cena
		Scene scene = new Scene(root, 1250, 650);
		primaryStage.setScene(scene);
		primaryStage.setMinWidth(1000);
		primaryStage.setMinHeight(700);
		primaryStage.show();
  	}

	private HBox createNavBar() {
		HBox navBar = new HBox();
		navBar.setPadding(new Insets(15, 12, 15, 12));
		navBar.setStyle("-fx-background-color: #E50914;"); // Cor de fundo da barra de navegação

		// Botão Home com logo da Netflix
		Image netflixLogo = new Image(getClass().getResourceAsStream("assets/iconNetflix.png")); // Substitua com o caminho correto da imagem
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
		homeButton.setOnAction(e -> switchToHomePage(homeButton));

		loginButton.setStyle(buttonStyle);
		loginButton.setOnMouseEntered(e -> loginButton.setStyle(buttonHoverStyle));
		loginButton.setOnMouseExited(e -> loginButton.setStyle(buttonStyle));
		loginButton.setOnAction(e -> switchToLoginPage(loginButton));

		// Espaço flexível entre os botões
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);

		navBar.getChildren().addAll(homeButton, spacer, loginButton);
		return navBar;
	};

	private VBox createMainContent() {
		VBox mainContent = new VBox();
		mainContent.setSpacing(10);
		mainContent.setPadding(new Insets(15, 12, 15, 12));
		mainContent.setAlignment(Pos.CENTER);

		// Carregar a imagem de fundo
		Image backgroundImage = new Image(getClass().getResourceAsStream("assets/Netflix-Background.jpg")); // Substitua com o caminho correto da imagem
		BackgroundSize bgSize = new BackgroundSize(100, 100, true, true, true, true);
		BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
			BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgSize);

		// Definir a imagem de fundo no VBox
		mainContent.setBackground(new Background(bgImage));

		// Texto centralizado
		Label titleLabel = new Label("Filmes, Séries e Muito Mais, Sem Limites");
		titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40)); // Ajuste o tamanho e a fonte conforme necessário
		titleLabel.setStyle("-fx-text-fill: #FFFFFF;"); // Cor do texto

		// Botão "Vamos Lá"
		Button startButton = new Button("Vamos Lá >");
		startButton.setOnAction(e -> switchToLoginPage(startButton));
		startButton.setStyle("-fx-font-size: 16px; -fx-background-color: #E50914; -fx-text-fill: #FFFFFF;");
		startButton.setOnMouseEntered(
			e -> startButton.setStyle("-fx-font-size: 16px; -fx-background-color: #B20710; -fx-text-fill: #FFFFFF;"));
		startButton.setOnMouseExited(
			e -> startButton.setStyle("-fx-font-size: 16px; -fx-background-color: #E50914; -fx-text-fill: #FFFFFF;"));

		// Adicionar elementos ao VBox
		mainContent.getChildren().addAll(titleLabel, startButton);

		return mainContent;
	};

	private void switchToHomePage(Button button) {
		Stage stage = (Stage) button.getScene().getWindow();
		HomePage HomePage = new HomePage();
		Scene homeScene = new Scene(HomePage, stage.getWidth(), stage.getHeight());
		stage.setScene(homeScene);
	};

  	private void switchToLoginPage(Button button) {
		Stage stage = (Stage) button.getScene().getWindow();
		LoginPage loginPage = new LoginPage();
		Scene loginScene = new Scene(loginPage, stage.getWidth(), stage.getHeight());
		stage.setScene(loginScene);
  	};

	public static void main(String[] args) {		
		launch(args);
	};

}
