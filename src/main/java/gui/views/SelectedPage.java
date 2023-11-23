package gui.views;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SelectedPage extends BorderPane {

  public SelectedPage() {

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
    Image netflixLogo = new Image(getClass().getResourceAsStream("../assets/iconNetflix.png"));
    ImageView logoView = new ImageView(netflixLogo);
    logoView.setFitHeight(30); // Ajuste conforme necessário
    logoView.setPreserveRatio(true);
    Button homeButton = new Button("", logoView); // Botão com a logo

    // Botão Login
    Button filmButton = new Button("Filmes/Series");

    Button categoryButton = new Button("Categorias");

    // Estilização dos botões
    String buttonStyle = "-fx-font-size: 14px; -fx-background-color: #000000; -fx-text-fill: #FFFFFF;";
    String buttonHoverStyle = "-fx-font-size: 14px; -fx-background-color: #555555; -fx-text-fill: #FFFFFF;";

    homeButton.setStyle(buttonStyle);
    homeButton.setOnMouseEntered(e -> homeButton.setStyle(buttonHoverStyle));
    homeButton.setOnMouseExited(e -> homeButton.setStyle(buttonStyle));

    filmButton.setStyle(buttonStyle);
    filmButton.setOnMouseEntered(e -> filmButton.setStyle(buttonHoverStyle));
    filmButton.setOnMouseExited(e -> filmButton.setStyle(buttonStyle));
    filmButton.setOnAction(e -> switchToFilmPage(filmButton));

    categoryButton.setStyle(buttonStyle);
    categoryButton.setOnMouseEntered(e -> categoryButton.setStyle(buttonHoverStyle));
    categoryButton.setOnMouseExited(e -> categoryButton.setStyle(buttonStyle));
    categoryButton.setOnAction(e -> switchToCategoryPage(categoryButton));

    // Espaço flexível entre os botões
    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    // Espaçador após o botão Filmes/Séries
    Region spacerRightFilmButton = new Region();
    spacerRightFilmButton.setMinWidth(10);

    navBar.getChildren().addAll(homeButton, spacer, filmButton, spacerRightFilmButton, categoryButton);
    return navBar;
  }

  private VBox createMainContent() {
    VBox mainContent = new VBox();
    mainContent.setAlignment(Pos.TOP_CENTER);
    mainContent.setSpacing(20);
    mainContent.setPadding(new Insets(10));

    // Carregar a imagem de fundo
    Image backgroundImage = new Image(getClass().getResourceAsStream("../assets/backgroundHome.jpg"));
    BackgroundSize bgSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
    BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgSize);
    mainContent.setBackground(new Background(bgImage));

    // Botão Voltar
    Button backButton = new Button();
    Image backIcon = new Image(getClass().getResourceAsStream("../assets/iconBack.svg"));
    backButton.setGraphic(new ImageView(backIcon));
    backButton.setOnAction(e -> handleBackAction());

    // Imagem do filme
    Image capaFilme = new Image(getClass().getResourceAsStream("../assets/capaFilme1.jpeg"));
    ImageView movieImage = new ImageView(capaFilme);
    movieImage.setFitHeight(300);
    movieImage.setFitWidth(200);
    // margin de 20 a esquerda
    movieImage.setTranslateX(100);
    movieImage.setPreserveRatio(true);

    // Título do filme
    Label movieTitle = new Label("Título do Filme");
    movieTitle.setFont(new Font("Arial", 30));
    movieTitle.setTextFill(Color.WHITE);

    // HBox para capa do filme e título
    HBox movieHeader = new HBox(movieImage, movieTitle);
    movieHeader.setAlignment(Pos.CENTER_LEFT);
    movieHeader.setSpacing(80);

    // Sistema de avaliação
    HBox ratingBox = createRatingBox();

    // Descrição do elenco
    Label castDescription = new Label("Elenco: Nome dos atores...");
    castDescription.setWrapText(true);
    castDescription.setTextFill(Color.WHITE);

    // Descrição do filme
    Label movieDescription = new Label("Descrição do filme...");
    movieDescription.setWrapText(true);
    movieDescription.setTextFill(Color.WHITE);

    // Adicionar todos os elementos ao VBox
    mainContent.getChildren().addAll(backButton, movieHeader, ratingBox, castDescription, movieDescription);

    return mainContent;
  }

  private HBox createRatingBox() {
    HBox ratingBox = new HBox();
    ratingBox.setAlignment(Pos.CENTER);
    ratingBox.setSpacing(10);

    for (int i = 1; i <= 5; i++) {
      Image star = new Image(getClass().getResourceAsStream("../assets/iconStar.svg"));
      ImageView starView = new ImageView(star);
      starView.setFitHeight(30);
      starView.setFitWidth(30);
      starView.setPreserveRatio(true);
      int finalI = i;
      starView.setOnMouseClicked(e -> handleRating(finalI));
      ratingBox.getChildren().add(starView);
    }

    return ratingBox;
  }

  private void handleRating(int rating) {
    System.out.println("Avaliação: " + rating + " estrelas");
    // Aqui você pode adicionar a lógica para lidar com a avaliação do filme
  }

  private void handleBackAction() {
    // Aqui você pode adicionar a lógica para voltar para a tela anterior
  }

  private void switchToFilmPage(Button button) {
    Stage stage = (Stage) button.getScene().getWindow();
    FilmeSeriePage FilmeSeriePage = new FilmeSeriePage();
    Scene FilmScene = new Scene(FilmeSeriePage, stage.getWidth(), stage.getHeight());
    stage.setScene(FilmScene);
  }

  private void switchToCategoryPage(Button button) {
    Stage stage = (Stage) button.getScene().getWindow();
    CategoriaPage CategoriaPage = new CategoriaPage();
    Scene CategoryScene = new Scene(CategoriaPage, stage.getWidth(), stage.getHeight());
    stage.setScene(CategoryScene);
  }
}
