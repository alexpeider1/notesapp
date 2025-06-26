package com.example.notesapp.repository;

import com.example.notesapp.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

//Schnittstelle zu DB mit automatischen Funktionen wie findAll(), save()... also kein SQL nötig
public interface NoteRepository extends JpaRepository<Note, Long> {

}