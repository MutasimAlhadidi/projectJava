/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.model;

import java.sql.Date;

/**
 *
 * @author Mutasim
 */
public class Metaphor {

    int id;
    String librarian_name;
    String student_name;
    String book_name;
    Date date_of_metaphor;

    public Metaphor(int id, String librarian_name, String student_name, String book_name, Date date_of_metaphor) {
        this.id = id;
        this.librarian_name = librarian_name;
        this.student_name = student_name;
        this.book_name = book_name;
        this.date_of_metaphor = date_of_metaphor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibrarian_name() {
        return librarian_name;
    }

    public void setLibrarian_name(String librarian_name) {
        this.librarian_name = librarian_name;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public Date getDate_of_metaphor() {
        return date_of_metaphor;
    }

    public void setDate_of_metaphor(Date date_of_metaphor) {
        this.date_of_metaphor = date_of_metaphor;
    }
}
