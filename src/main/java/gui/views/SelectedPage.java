package gui.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
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
    Image backIcon = new Image(getClass().getResourceAsStream("../assets/iconBack.png"));
    ImageView backIconView = new ImageView(backIcon);
    backIconView.setFitHeight(30);
    backIconView.setPreserveRatio(true);
    backButton.setGraphic(backIconView);

    // Estilização opcional
    backButton.setStyle("-fx-background-color: transparent;");
    backButton.setPadding(new Insets(5, 5, 5, 5));

    // Efeito de hover com DropShadow
    DropShadow dropShadow = new DropShadow();
    dropShadow.setColor(Color.WHITE);
    dropShadow.setRadius(10);
    dropShadow.setSpread(0.5);

    backButton.setOnMouseEntered(e -> backIconView.setEffect(dropShadow));
    backButton.setOnMouseExited(e -> backIconView.setEffect(null));

    // Ação do botão Voltar
    backButton.setOnAction(e -> handleBackAction(backButton));

    // Imagem do filme
    Image capaFilme = new Image(getClass().getResourceAsStream("../assets/capaFilme1.jpeg"));
    ImageView movieImage = new ImageView(capaFilme);
    movieImage.setFitHeight(300);
    movieImage.setFitWidth(200);
    movieImage.setPreserveRatio(true);

    // Título do filme e descrições
    VBox movieDetails = new VBox();
    movieDetails.setAlignment(Pos.CENTER_LEFT);
    movieDetails.setSpacing(10);

    Label movieTitle = new Label("Título do Filme");
    movieTitle.setFont(new Font("Arial", 30));
    movieTitle.setTextFill(Color.WHITE);

    Label notaDescription = new Label("Nota: ");
    notaDescription.setFont(new Font("Arial", 15));
    notaDescription.setWrapText(true);
    notaDescription.setTextFill(Color.WHITE);
    notaDescription.setMaxWidth(500);

    HBox ratingBox = createRatingBox();

    Label castDescription = new Label("Elenco: ");
    castDescription.setFont(new Font("Arial", 15));
    castDescription.setWrapText(true);
    castDescription.setTextFill(Color.WHITE);
    castDescription.setMaxWidth(500);

    Label movieDescription = new Label("Spoiler do filme: ");
    movieDescription.setFont(new Font("Arial", 15));
    movieDescription.setWrapText(true);
    movieDescription.setTextFill(Color.WHITE);
    movieDescription.setMaxWidth(500);

    Label diretorDescription = new Label("Diretor: ");
    diretorDescription.setFont(new Font("Arial", 15));
    diretorDescription.setWrapText(true);
    diretorDescription.setTextFill(Color.WHITE);
    diretorDescription.setMaxWidth(500);

    movieDetails.getChildren().addAll(movieTitle, notaDescription, ratingBox, diretorDescription, castDescription,
        movieDescription);

    // HBox para agrupar a imagem e os detalhes do filme
    HBox movieContent = new HBox(movieImage, movieDetails);
    movieContent.setAlignment(Pos.CENTER_LEFT);
    movieContent.setSpacing(80);
    movieContent.setPadding(new Insets(50, 0, 0, 200));

    // Adicionar todos os elementos ao VBox principal
    mainContent.getChildren().addAll(backButton, movieContent);

    return mainContent;
  }

  private DropShadow createGlowEffect() {
    DropShadow glow = new DropShadow();
    glow.setColor(Color.WHITE);
    glow.setRadius(10);
    glow.setSpread(0.5);
    return glow;
  }

  private HBox createRatingBox() {
    HBox ratingBox = new HBox();
    ratingBox.setAlignment(Pos.CENTER);
    ratingBox.setSpacing(10);
    DropShadow glowEffect = createGlowEffect();

    for (int i = 0; i < 5; i++) {
      Image star = new Image(getClass().getResourceAsStream("../assets/iconStar.png"));
      ImageView starView = new ImageView(star);
      starView.setFitHeight(30);
      starView.setFitWidth(30);
      starView.setPreserveRatio(true);
      int finalI = i;

      // Adiciona o efeito de brilho ao passar o mouse
      starView.setOnMouseEntered(e -> {
        for (int j = 0; j <= finalI; j++) {
          ((ImageView) ratingBox.getChildren().get(j)).setEffect(glowEffect);
        }
      });

      // Remove o efeito de brilho ao sair o mouse
      starView.setOnMouseExited(e -> {
        for (Node node : ratingBox.getChildren()) {
          ((ImageView) node).setEffect(null);
        }
      });

      starView.setOnMouseClicked(e -> handleRating(finalI + 1));
      ratingBox.getChildren().add(starView);
    }

    return ratingBox;
  }

  private void handleRating(int rating) {
    System.out.println("Avaliação: " + rating + " estrelas");
    // Aqui você pode adicionar a lógica para lidar com a avaliação do filme
  }

  private void handleBackAction(Button button) {
    Stage stage = (Stage) button.getScene().getWindow();
    HomePage HomePage = new HomePage();
    Scene homeScene = new Scene(HomePage, stage.getWidth(), stage.getHeight());
    stage.setScene(homeScene);
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
