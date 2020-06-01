/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import library.controller.db.DbConnection;
import library.model.Book;
import library.model.Metaphor;

/**
 * FXML Controller class
 *
 * @author Mutasim
 */
public class MetaphorsController implements Initializable {

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
    private TableView<Metaphor> tableMetaphors;
    @FXML
    private TableColumn<Metaphor, Integer> id;
    @FXML
    private TableColumn<Metaphor, String> librarian;
    @FXML
    private TableColumn<Metaphor, String> student;
    @FXML
    private TableColumn<Metaphor, String> book;
    @FXML
    private TableColumn<Metaphor, Date> date_of_metaphor;
    @FXML
    private Button add;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Statement st = DbConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT metaphors.id AS id, librarians.name AS librarian_name, students.name AS student_name, books.name AS book_name, books.author AS book_author, metaphors.date_of_metaphor AS date_of_metaphor FROM metaphors JOIN librarians ON metaphors.librarian_id = librarians.id JOIN students ON metaphors.student_id = students.id JOIN books ON metaphors.book_id = books.id;");
            while (rs.next()) {
                this.tableMetaphors.getItems().add(new Metaphor(
                        rs.getInt("id"),
                        rs.getString("librarian_name"),
                        rs.getString("student_name"),
                        rs.getString("book_name") + " - " + rs.getString("book_author"),
                        rs.getDate("date_of_metaphor")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.id.setCellValueFactory(new PropertyValueFactory("id"));
        this.librarian.setCellValueFactory(new PropertyValueFactory("librarian_name"));
        this.student.setCellValueFactory(new PropertyValueFactory("student_name"));
        this.book.setCellValueFactory(new PropertyValueFactory("book_name"));
        this.date_of_metaphor.setCellValueFactory(new PropertyValueFactory("date_of_metaphor"));
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

    @FXML
    private void add(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/view/EditMetaphors.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Add book");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MetaphorsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void select(MouseEvent event) {
          Metaphor metaphor = this.tableMetaphors.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/view/EditMetaphors.fxml"));
            Parent parent = loader.load();
            EditMetaphorsController ebc = loader.getController();
            ebc.setLabel("Edit metaphor");
            ebc.setAdd("Update");
            ebc.setReset("Delete");
            ebc.setId(metaphor.getId());
            ebc.setLibrarian(metaphor.getLibrarian_name());
            ebc.setStudent(metaphor.getStudent_name());
            ebc.setBook(metaphor.getBook_name());
            ebc.setDate(metaphor.getDate_of_metaphor());
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Edit metaphor");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
