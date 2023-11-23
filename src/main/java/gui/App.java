package gui;

import gui.views.HomePage;
import gui.views.LoginPage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class App extends Application {

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
		navBar.setStyle("-fx-background-color: #E50914;");

		Image netflixLogo = new Image(getClass().getResourceAsStream("assets/iconNetflix.png"));
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
		homeButton.setOnAction(e -> switchToHomePage(homeButton));

		loginButton.setStyle(buttonStyle);
		loginButton.setOnMouseEntered(e -> loginButton.setStyle(buttonHoverStyle));
		loginButton.setOnMouseExited(e -> loginButton.setStyle(buttonStyle));
		loginButton.setOnAction(e -> switchToLoginPage(loginButton));

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

		Image backgroundImage = new Image(getClass().getResourceAsStream("assets/Netflix-Background.jpg"));
		BackgroundSize bgSize = new BackgroundSize(100, 100, true, true, true, true);
		BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgSize);

		mainContent.setBackground(new Background(bgImage));

		Label titleLabel = new Label("Filmes, Séries e Muito Mais, Sem Limites");
		titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		titleLabel.setStyle("-fx-text-fill: #FFFFFF;");

		Button startButton = new Button("Vamos Lá >");
		startButton.setOnAction(e -> switchToLoginPage(startButton));
		startButton.setStyle("-fx-font-size: 16px; -fx-background-color: #E50914; -fx-text-fill: #FFFFFF;");
		startButton.setOnMouseEntered(
				e -> startButton.setStyle("-fx-font-size: 16px; -fx-background-color: #B20710; -fx-text-fill: #FFFFFF;"));
		startButton.setOnMouseExited(
				e -> startButton.setStyle("-fx-font-size: 16px; -fx-background-color: #E50914; -fx-text-fill: #FFFFFF;"));

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
		// Example.populate();
		launch(args);
	};

}
