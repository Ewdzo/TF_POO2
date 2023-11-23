package gui.views;

import org.hibernate.Hibernate;

import entities.Media;
import helper.HibernateController;
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
  Media media;

  public SelectedPage(Media media) {
    this.media = media;

    HBox navBar = createNavBar();
    VBox mainContent = createMainContent();
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

  private VBox createMainContent() {
    VBox mainContent = new VBox();
    mainContent.setAlignment(Pos.TOP_CENTER);
    mainContent.setSpacing(20);
    mainContent.setPadding(new Insets(10));

    Image backgroundImage = new Image(getClass().getResourceAsStream("../assets/backgroundSelected.jpg"));
    BackgroundSize bgSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
    BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgSize);
    mainContent.setBackground(new Background(bgImage));

    Button backButton = new Button();
    Image backIcon = new Image(getClass().getResourceAsStream("../assets/iconBack.png"));
    ImageView backIconView = new ImageView(backIcon);
    backIconView.setFitHeight(30);
    backIconView.setPreserveRatio(true);
    backButton.setGraphic(backIconView);

    backButton.setStyle("-fx-background-color: transparent;");
    backButton.setPadding(new Insets(5, 5, 5, 5));

    DropShadow dropShadow = new DropShadow();
    dropShadow.setColor(Color.WHITE);
    dropShadow.setRadius(10);
    dropShadow.setSpread(0.5);

    backButton.setOnMouseEntered(e -> backIconView.setEffect(dropShadow));
    backButton.setOnMouseExited(e -> backIconView.setEffect(null));

    backButton.setOnAction(e -> handleBackAction(backButton));

    Image capaFilme = new Image(getClass().getResourceAsStream(media.getPhoto()));
    ImageView movieImage = new ImageView(capaFilme);
    movieImage.setFitHeight(300);
    movieImage.setFitWidth(200);
    movieImage.setPreserveRatio(true);

    VBox movieDetails = new VBox();
    movieDetails.setAlignment(Pos.CENTER_LEFT);
    movieDetails.setSpacing(10);

    Label movieTitle = new Label(media.getTitle());
    movieTitle.setFont(new Font("Arial", 30));
    movieTitle.setTextFill(Color.WHITE);

    Label notaDescription = new Label("Nota: " + media.getGrade());
    notaDescription.setFont(new Font("Arial", 15));
    notaDescription.setWrapText(true);
    notaDescription.setTextFill(Color.WHITE);
    notaDescription.setMaxWidth(500);

    HBox ratingBox = createRatingBox();

    Label castDescription = new Label("Elenco: " + media.getCast());
    castDescription.setFont(new Font("Arial", 15));
    castDescription.setWrapText(true);
    castDescription.setTextFill(Color.WHITE);
    castDescription.setMaxWidth(500);

    Label movieDescription = new Label("Spoiler do filme: " + media.getDescription());
    movieDescription.setFont(new Font("Arial", 15));
    movieDescription.setWrapText(true);
    movieDescription.setTextFill(Color.WHITE);
    movieDescription.setMaxWidth(500);

    Label diretorDescription = new Label("Diretor: " + media.getDirector().getName());
    diretorDescription.setFont(new Font("Arial", 15));
    diretorDescription.setWrapText(true);
    diretorDescription.setTextFill(Color.WHITE);
    diretorDescription.setMaxWidth(500);

    movieDetails.getChildren().addAll(movieTitle, notaDescription, ratingBox, diretorDescription, castDescription,
        movieDescription);

    HBox movieContent = new HBox(movieImage, movieDetails);
    movieContent.setAlignment(Pos.CENTER);
    movieContent.setSpacing(80);
    movieContent.setPadding(new Insets(50, 0, 0, 0));

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
    ratingBox.setAlignment(Pos.CENTER_LEFT);
    ratingBox.setSpacing(10);
    DropShadow glowEffect = createGlowEffect();

    for (int i = 0; i < 5; i++) {
      Image star = new Image(getClass().getResourceAsStream("../assets/iconStarLivre.png"));
      ImageView starView = new ImageView(star);
      starView.setFitHeight(30);
      starView.setFitWidth(30);
      starView.setPreserveRatio(true);
      int finalI = i;

      starView.setOnMouseEntered(e -> {
        for (int j = 0; j <= finalI; j++) {
          ((ImageView) ratingBox.getChildren().get(j)).setEffect(glowEffect);
        }
      });

      starView.setOnMouseExited(e -> {
        for (Node node : ratingBox.getChildren()) {
          ((ImageView) node).setEffect(null);
        }
      });

      starView.setOnMouseClicked(e -> handleRating(ratingBox, starView, finalI + 1));

      ratingBox.getChildren().add(starView);
    }

    return ratingBox;
  }

  private void handleRating(HBox ratingBox, ImageView view, int rating) {
    HibernateController.reviewMovie(rating * 2, getAccessibleHelp());

    for (int i = 0; i < rating; i++) {
      ImageView starView = (ImageView) ratingBox.getChildren().get(i);
      starView.setImage(new Image(getClass().getResourceAsStream("../assets/iconStarSelected.png")));
    }

    for (int i = rating; i < 5; i++) {
      ImageView starView = (ImageView) ratingBox.getChildren().get(i);
      starView.setImage(new Image(getClass().getResourceAsStream("../assets/iconStarLivre.png")));
    }
  }


  private void handleBackAction(Button button) {
    Stage stage = (Stage) button.getScene().getWindow();
    HomePage HomePage = new HomePage();
    Scene homeScene = new Scene(HomePage, stage.getWidth(), stage.getHeight());
    stage.setScene(homeScene);
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
}
