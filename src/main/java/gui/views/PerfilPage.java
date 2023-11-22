package gui.views;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PerfilPage extends VBox {
  public PerfilPage() {
    Label nomeUsuario = new Label("Nome do Usuário");
    ImageView fotoPerfil = new ImageView(); // Adicione a foto do perfil
    // Adicione outras informações do perfil

    this.getChildren().addAll(nomeUsuario, fotoPerfil);
  }
}