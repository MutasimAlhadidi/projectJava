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
import java.time.LocalDate;
import java.util.AbstractList;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import library.controller.db.DbConnection;
import library.model.Book;
import library.model.Librarian;
import library.model.Student;

/**
 * FXML Controller class
 *
 * @author Mutasim
 */
public class EditMetaphorsController implements Initializable {

    @FXML
    private Button add;
    @FXML
    private Button reset;
    @FXML
    private Button close;
    @FXML
    private Label label;
    @FXML
    private ComboBox<String> librarian;
    private List<Librarian> listLibrarian;
    @FXML
    private ComboBox<String> student;
    private List<Student> listStudent;
    @FXML
    private ComboBox<String> book;
    private List<Book> listBooks;
    @FXML
    private DatePicker date;
    private int id;

    public EditMetaphorsController() {
        this.listLibrarian = new ArrayList();
        this.listStudent = new ArrayList();
        this.listBooks = new ArrayList();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Statement st = DbConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM librarians;");
            while (rs.next()) {
                this.librarian.getItems().add(rs.getString("name"));
                this.listLibrarian.add(new Librarian(rs.getInt("id"), rs.getString("name")));
            }

            rs = st.executeQuery("SELECT * FROM students;");
            while (rs.next()) {
                this.student.getItems().add(rs.getString("name"));
                this.listStudent.add(new Student(rs.getInt("id"), rs.getString("name")));
            }

            rs = st.executeQuery("SELECT * FROM books;");
            while (rs.next()) {
                this.book.getItems().add(rs.getString("name") + " - " + rs.getString("author"));
                this.listBooks.add(new Book(rs.getInt("id"), rs.getString("name"), rs.getString("author")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditMetaphorsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void add(ActionEvent event) {
        String sql;
        int librarian_id = 0, student_id = 0, book_id = 0;
        for (Librarian librarian1 : listLibrarian) {
            if ((librarian1.getName().equals(librarian.getValue()))) {
                librarian_id = librarian1.getId();
                break;
            }
        }

        for (Student student1 : listStudent) {
            if ((student1.getName().equals(student.getValue()))) {
                student_id = student1.getId();
                break;
            }
        }

        for (Book book1 : listBooks) {
            if (((book1.getName() + " - " + book1.getAuthor()).equals(book.getValue()))) {
                book_id = book1.getId();
                break;
            }
        }
        //sql = "Insert int
        if (this.id == 0) {
            sql = "Insert into metaphors(librarian_id, student_id, book_id, date_of_metaphor) VALUES(" + librarian_id + ", " + student_id + ", " + book_id + ", '" + this.date.getValue() + "')";
        } else {
            sql = "UPDATE metaphors SET librarian_id = " + librarian_id + ", student_id = " + student_id + ", book_id = " + book_id + ", date_of_metaphor = '" + this.date.getValue() + "' WHERE id = " + this.id;
        }

        try {
            Statement st = DbConnection.getConnection().createStatement();
            int i = st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EditMetaphorsController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/library/view/Metaphors.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EditMetaphorsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reset(ActionEvent event) {
        if (this.id == 0) {
            this.librarian.setValue("-- select --");
            this.student.setValue("-- select --");
            this.book.setValue("-- select --");
            this.date.setValue(null);
        } else {
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("delete");
            alert.setContentText("Are you sure you want to delete this metaphor?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                try {
                    Statement st = DbConnection.getConnection().createStatement();
                    int i = st.executeUpdate("DELETE FROM metaphors WHERE id = " + this.id);
                    Parent parent = FXMLLoader.load(getClass().getResource("/library/view/Metaphors.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(EditMetaphorsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(EditMetaphorsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @FXML
    private void close(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/library/view/Metaphors.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EditMetaphorsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdd(String add) {
        this.add.setText(add);
    }

    public void setReset(String reset) {
        this.reset.setText(reset);
    }

    public void setLabel(String label) {
        this.label.setText(label);
    }

    public void setLibrarian(String librarian) {
        this.librarian.setValue(librarian);
    }

    public void setStudent(String student) {
        this.student.setValue(student);
    }

    public void setBook(String book) {
        this.book.setValue(book);
    }

    public void setDate(Date date) {
        this.date.setValue(date.toLocalDate());
    }

}
