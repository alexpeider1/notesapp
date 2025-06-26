package com.example.notesapp.model;

import jakarta.persistence.*; //für @Entity, @Id... JPA DB

@Entity //Klasse für Datenbanktabelle
public class Note {
    @Id //Primärschlüssel
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto generierung mit increment
    private Long id;

    private String title; //Titel der Notiz
    private String content; //Content der Notiz

    public Note() {} //leerer Konstruktor

    /*
    //Konstruktor zum testen
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }
     */

    //Get und set für DB-requests und push
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

