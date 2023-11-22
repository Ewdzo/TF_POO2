package gui.views;
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
    navBar.setStyle("-fx-background-color: #E50914;"); // Cor de fundo da barra de navegação

    // Botão Home com logo da Netflix
    Image netflixLogo = new Image(getClass().getResourceAsStream("../assets/iconNetflix.png")); // Substitua com o caminho correto da imagem
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

  private ScrollPane createMainContent() {
    VBox contentBox = new VBox();
    contentBox.setAlignment(Pos.CENTER_LEFT);
    contentBox.setPadding(new Insets(50, 0, 0, 50)); // Ajuste os espaços conforme necessário
    contentBox.setSpacing(50); // Espaçamento entre os elementos

    // Carregar a imagem de fundo
    Image backgroundImage = new Image(getClass().getResourceAsStream("../assets/backgroundHome.jpg")); // Substitua com o caminho correto da imagem
    BackgroundSize bgSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
    BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgSize);
    contentBox.setBackground(new Background(bgImage));

    // ComboBox para seleção de categoria
    ComboBox<String> categorySelector = new ComboBox<>();
    categorySelector.getItems().addAll("Filmes", "Séries");

    // FlowPane para exibir os itens
    FlowPane itemPane = new FlowPane();
    itemPane.setHgap(30);
    itemPane.setVgap(50);
    itemPane.setAlignment(Pos.TOP_CENTER);
    Insets currentInsets = itemPane.getPadding();
    double bottomPadding = 600; // Defina o valor desejado para o padding inferior
    itemPane.setPadding(
        new Insets(currentInsets.getTop(), currentInsets.getRight(), bottomPadding, currentInsets.getLeft()));

    // Listener para mudanças na ComboBox
    categorySelector.valueProperty().addListener((obs, oldVal, newVal) -> {
      itemPane.getChildren().clear();
      populateItemPane(itemPane, newVal); // Método para popular o FlowPane com base na seleção
    });

    contentBox.getChildren().addAll(categorySelector, itemPane);

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

  private void populateItemPane(FlowPane pane, String category) {
    int numItems = 10; // Defina o número de itens aqui
    for (int i = 1; i <= numItems; i++) {
      Button itemButton = new Button(category + " " + i);
      itemButton.setStyle("-fx-background-color: #333333; -fx-text-fill: #FFFFFF;");
      pane.getChildren().add(itemButton);
    }
  }

  private VBox createCategory(String categoryName, int numberOfMovies) {
    VBox categoryBox = new VBox();
    categoryBox.setPadding(new Insets(10));
    categoryBox.setSpacing(10);

    Label categoryLabel = new Label(categoryName);
    categoryLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #FFFFFF;");

    HBox movieList = new HBox();
    movieList.setSpacing(10);

    for (int i = 1; i <= numberOfMovies; i++) {
      Button movieButton = new Button("Filme " + i);
      movieButton.setStyle("-fx-background-color: #333333; -fx-text-fill: #FFFFFF;");
      movieList.getChildren().add(movieButton);
    }

    categoryBox.getChildren().addAll(categoryLabel, movieList);
    return categoryBox;
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
