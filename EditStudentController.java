/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import library.controller.db.DbConnection;

/**
 * FXML Controller class
 *
 * @author Mutasim
 */
public class EditStudentController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private Button add;
    @FXML
    private Button reset;
    @FXML
    private Button close;
    @FXML
    private Label label;
    private int id = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void add(ActionEvent event) {
        String sql;
        if (this.id == 0) {
            sql = "Insert into students(name) VALUES('" + this.name.getText() + "')";
        } else {
            sql = "UPDATE Students SET name = '" + this.name.getText() + "' WHERE id = " + this.id;
        }

        try {
            Statement st = DbConnection.getConnection().createStatement();
            int i = st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EditBookController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/library/view/Students.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EditStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reset(ActionEvent event) {
        if (this.id == 0) {
            this.name.setText("");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("delete");
            alert.setContentText("Are you sure you want to delete this student?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                try {
                    Statement st = DbConnection.getConnection().createStatement();
                    int i = st.executeUpdate("DELETE FROM students WHERE id = " + this.id);
                    Parent parent = FXMLLoader.load(getClass().getResource("/library/view/Students.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(EditBookController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @FXML
    private void close(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/library/view/Students.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setAdd(String add) {
        this.add.setText(add);
    }

    public void setClose(String close) {
        this.close.setText(close);
    }

    public void setLabel(String label) {
        this.label.setText(label);
    }

    public void setReset(String reset) {
        this.reset.setText(reset);
    }

}
