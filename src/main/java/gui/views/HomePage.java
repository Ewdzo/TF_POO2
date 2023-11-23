package gui.views;

import java.util.ArrayList;
import java.util.List;

import entities.Media;
import entities.Movie;
import entities.Series;
import helper.HibernateController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
import javafx.stage.Stage;

public class HomePage extends BorderPane {
  ArrayList<Movie> movies = HibernateController.getMovies(List.of("The Flash", "Spiderverse", "Dune", "Whiplash", "La La Land"));
  ArrayList<Series> series = HibernateController.getSeries(List.of("Wandinha", "Breaking Bad", "Better Call Saul", "Peaky Blinders", "Atlanta"));
  
  public HomePage() {

    // Barra de navegação
    HBox navBar = createNavBar();

    // Conteúdo principal
    ScrollPane mainContent = createMainContent();

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
    Button filmButton = new Button("Filmes/Series");
    Button categoryButton = new Button("Categorias");

    String buttonStyle = "-fx-font-size: 14px; -fx-background-color: #000000; -fx-text-fill: #FFFFFF;";
    String buttonHoverStyle = "-fx-font-size: 14px; -fx-background-color: #555555; -fx-text-fill: #FFFFFF;";

    homeButton.setStyle(buttonStyle);
    homeButton.setOnMouseEntered(e -> homeButton.setStyle(buttonHoverStyle));
    homeButton.setOnMouseExited(e -> homeButton.setStyle(buttonStyle));
    homeButton.setOnAction(e -> switchToSelectedPage(homeButton, movies.get(0)));

    filmButton.setStyle(buttonStyle);
    filmButton.setOnMouseEntered(e -> filmButton.setStyle(buttonHoverStyle));
    filmButton.setOnMouseExited(e -> filmButton.setStyle(buttonStyle));
    filmButton.setOnAction(e -> switchToHomePage(filmButton));

    categoryButton.setStyle(buttonStyle);
    categoryButton.setOnMouseEntered(e -> categoryButton.setStyle(buttonHoverStyle));
    categoryButton.setOnMouseExited(e -> categoryButton.setStyle(buttonStyle));
    categoryButton.setOnAction(e -> switchToCategoryPage(categoryButton));

    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    Region spacerRightFilmButton = new Region();
    spacerRightFilmButton.setMinWidth(10);

    navBar.getChildren().addAll(homeButton, spacer, filmButton, spacerRightFilmButton, categoryButton);
    return navBar;
  }

  private ScrollPane createMainContent() {
    VBox contentBox = new VBox();
    contentBox.setAlignment(Pos.CENTER_LEFT);
    contentBox.setPadding(new Insets(50, 0, 0, 50));
    contentBox.setSpacing(50);

    Image backgroundImage = new Image(getClass().getResourceAsStream("../assets/backgroundHome.jpg"));
    BackgroundSize bgSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
    BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgSize);
    contentBox.setBackground(new Background(bgImage));

    VBox filmesLancamentos = createCategory("Filmes Lançamentos", movies);
    VBox recomendados = createCategory("Recomendados", movies, series);
    VBox maisPopulares = createCategory("Mais Populares", series, movies);
    VBox maisAvaliados = createCategory("Mais Avaliados", movies);
    VBox maisVistos = createCategory("Mais Vistos", series);

    // Adicionar categorias ao VBox
    contentBox.getChildren().addAll(filmesLancamentos, recomendados, maisPopulares, maisAvaliados, maisVistos);

    // Criação do ScrollPane
    ScrollPane scrollPane = new ScrollPane(); // Altere o nome da variável para evitar conflito
    scrollPane.setContent(contentBox);
    scrollPane.setFitToWidth(true); // Ajusta a largura do conteúdo ao ScrollPane
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Esconde a barra de rolagem horizontal
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Mostra a barra de rolagem vertical conforme

    // Estilização do ScrollPane
    scrollPane.setStyle("-fx-color: black; -fx-border-width: 2;");
    return scrollPane;
  }

  private VBox createCategory(String categoryName, List<? extends Media> medias) {
    VBox categoryBox = new VBox();
    categoryBox.setPadding(new Insets(10));
    categoryBox.setSpacing(10);

    Label categoryLabel = new Label(categoryName);
    categoryLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #FFFFFF;");

    HBox movieList = new HBox();
    movieList.setSpacing(10);

    for (Media media : medias) {
      // Botão para cada filme
      Button movieButton = new Button();
      movieButton.setStyle("-fx-background-color: transparent; -fx-padding: 10;");

      VBox movieBox = new VBox();
      movieBox.setSpacing(5); // Espaçamento entre a imagem e o título
      movieBox.setAlignment(Pos.CENTER);

      // Imagem do filme
      Image movieImage = new Image(getClass().getResourceAsStream(media.getPhoto()));
      ImageView imageView = new ImageView(movieImage);
      imageView.setFitHeight(100); // Ajuste conforme necessário
      imageView.setFitWidth(100);
      imageView.setPreserveRatio(true);

      // Título do filme
      Label movieTitle = new Label(media.getTitle());
      movieTitle.setStyle("-fx-text-fill: #FFFFFF;");

      movieBox.getChildren().addAll(imageView, movieTitle);
      movieButton.setGraphic(movieBox); // Define o VBox como conteúdo gráfico do botão

      // Adiciona ação ao botão
      movieButton.setOnAction(e -> switchToSelectedPage(movieButton, media));

      movieList.getChildren().add(movieButton);
    }

    categoryBox.getChildren().addAll(categoryLabel, movieList);
    return categoryBox;
  }

  private VBox createCategory(String categoryName, List<? extends Media> medias1, List<? extends Media> medias2) {
    VBox categoryBox = new VBox();
    categoryBox.setPadding(new Insets(10));
    categoryBox.setSpacing(10);

    Label categoryLabel = new Label(categoryName);
    categoryLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #FFFFFF;");

    HBox movieList = new HBox();
    movieList.setSpacing(10);
    
    for (Media media : medias1) {
      VBox movieBox = new VBox();
      movieBox.setSpacing(5); // Espaçamento entre a imagem e o título
      movieBox.setAlignment(Pos.CENTER);
      movieBox.setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 1; -fx-padding: 10;");

      // Imagem do filme (substitua com o caminho correto da imagem)
      Image movieImage = new Image(getClass().getResourceAsStream(media.getPhoto()));
      ImageView imageView = new ImageView(movieImage);
      imageView.setFitHeight(100); // Ajuste conforme necessário
      imageView.setFitWidth(100);
      imageView.setPreserveRatio(true);

      // Título do filme
      Label movieTitle = new Label(media.getTitle());
      movieTitle.setStyle("-fx-text-fill: #FFFFFF;");

      movieBox.getChildren().addAll(imageView, movieTitle);
      movieList.getChildren().add(movieBox);
    }

    for (Media media : medias2) {
      VBox movieBox = new VBox();
      movieBox.setSpacing(5); // Espaçamento entre a imagem e o título
      movieBox.setAlignment(Pos.CENTER);
      movieBox.setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 1; -fx-padding: 10;");

      // Imagem do filme (substitua com o caminho correto da imagem)
      Image movieImage = new Image(getClass().getResourceAsStream(media.getPhoto()));
      ImageView imageView = new ImageView(movieImage);
      imageView.setFitHeight(100); // Ajuste conforme necessário
      imageView.setFitWidth(100);
      imageView.setPreserveRatio(true);

      // Título do filme
      Label movieTitle = new Label(media.getTitle());
      movieTitle.setStyle("-fx-text-fill: #FFFFFF;");

      movieBox.getChildren().addAll(imageView, movieTitle);
      movieList.getChildren().add(movieBox);
    }

    categoryBox.getChildren().addAll(categoryLabel, movieList);
    return categoryBox;
  }

  private void switchToSelectedPage(Button button, Media media) {
    Stage stage = (Stage) button.getScene().getWindow();
    SelectedPage SelectedPage = new SelectedPage(media);
    Scene SelectedScene = new Scene(SelectedPage, stage.getWidth(), stage.getHeight());
    stage.setScene(SelectedScene);
  }

  private void switchToHomePage(Button button) {
		Stage stage = (Stage) button.getScene().getWindow();
		HomePage HomePage = new HomePage();
		Scene homeScene = new Scene(HomePage, stage.getWidth(), stage.getHeight());
		stage.setScene(homeScene);
  }

  private void switchToCategoryPage(Button button) {
    Stage stage = (Stage) button.getScene().getWindow();
    CategoriaPage CategoriaPage = new CategoriaPage();
    Scene CategoryScene = new Scene(CategoriaPage, stage.getWidth(), stage.getHeight());
    stage.setScene(CategoryScene);
  }
}
