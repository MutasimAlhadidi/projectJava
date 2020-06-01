/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import library.controller.db.DbConnection;
import library.model.Librarian;
import library.model.Student;

/**
 * FXML Controller class
 *
 * @author Mutasim
 */
public class LibrariansController implements Initializable {

    @FXML
    private Label home;
    @FXML
    private Label books;
    @FXML
    private Label students;
    @FXML
    private Label librarians;
    @FXML
    private Label metaphors;
    @FXML
    private TableView<Librarian> tableLibrarians;
    @FXML
    private TableColumn<Librarian, Integer> id;
    @FXML
    private TableColumn<Librarian, String> name;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Statement st = DbConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM librarians;");
            while (rs.next()) {
                this.tableLibrarians.getItems().add(new Librarian(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.id.setCellValueFactory(new PropertyValueFactory("id"));
        this.name.setCellValueFactory(new PropertyValueFactory("name"));
    }

    @FXML
    private void home(MouseEvent event) {
        try {
            System.out.println();
            Parent parent = FXMLLoader.load(getClass().getResource("/library/view/Home.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void books(MouseEvent event) {
        try {
            System.out.println();
            Parent parent = FXMLLoader.load(getClass().getResource("/library/view/Books.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void students(MouseEvent event) {
        try {
            System.out.println();
            Parent parent = FXMLLoader.load(getClass().getResource("/library/view/Students.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void librarians(MouseEvent event) {
        try {
            System.out.println();
            Parent parent = FXMLLoader.load(getClass().getResource("/library/view/Librarians.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void metaphors(MouseEvent event) {
        try {
            System.out.println();
            Parent parent = FXMLLoader.load(getClass().getResource("/library/view/Metaphors.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
