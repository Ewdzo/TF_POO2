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
import javafx.scene.control.ComboBox;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CategoriaPage extends BorderPane {
  ArrayList<Movie> movies = HibernateController
      .getMovies(List.of("The Flash", "Spiderverse", "Dune", "Whiplash", "La La Land"));
  ArrayList<Series> seriesA = HibernateController
      .getSeries(List.of("Wandinha", "Breaking Bad", "Better Call Saul", "Peaky Blinders", "Atlanta"));

  public CategoriaPage() {

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

    filmButton.setStyle(buttonStyle);
    filmButton.setOnMouseEntered(e -> filmButton.setStyle(buttonHoverStyle));
    filmButton.setOnMouseExited(e -> filmButton.setStyle(buttonStyle));
    filmButton.setOnAction(e -> switchToFilmPage(filmButton));

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

    Image backgroundImage = new Image(getClass().getResourceAsStream("../assets/backgroundHome.png"));
    BackgroundSize bgSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
    BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgSize);
    contentBox.setBackground(new Background(bgImage));

    VBox filmes = createCategory("Filmes", movies);
    VBox series = createCategory("Series", seriesA);

    contentBox.getChildren().addAll(filmes, series);

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(contentBox);
    scrollPane.setFitToWidth(true);
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    contentBox.setPadding(new Insets(20, 0, 300, 0));
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
      Button movieButton = new Button();
      movieButton.setStyle("-fx-background-color: transparent; -fx-padding: 10;");

      VBox movieBox = new VBox();
      movieBox.setSpacing(5);
      movieBox.setAlignment(Pos.CENTER);

      Image movieImage = new Image(getClass().getResourceAsStream(media.getPhoto()));
      ImageView imageView = new ImageView(movieImage);
      imageView.setFitHeight(100);
      imageView.setFitWidth(100);
      imageView.setPreserveRatio(true);

      Label movieTitle = new Label(media.getTitle());
      movieTitle.setStyle("-fx-text-fill: #FFFFFF;");

      movieBox.getChildren().addAll(imageView, movieTitle);
      movieButton.setGraphic(movieBox);

      movieButton.setOnAction(e -> switchToSelectedPage(movieButton, media));

      movieList.getChildren().add(movieButton);
    }

    categoryBox.getChildren().addAll(categoryLabel, movieList);
    return categoryBox;
  }

  private void switchToFilmPage(Button button) {
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

  private void switchToSelectedPage(Button button, Media media) {
    Stage stage = (Stage) button.getScene().getWindow();
    SelectedPage SelectedPage = new SelectedPage(media);
    Scene SelectedScene = new Scene(SelectedPage, stage.getWidth(), stage.getHeight());
    stage.setScene(SelectedScene);
  }

}
