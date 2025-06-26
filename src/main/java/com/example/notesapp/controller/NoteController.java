package com.example.notesapp.controller;

import com.example.notesapp.model.Note;
import com.example.notesapp.repository.NoteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //WebController
@RequestMapping("/api/notes") //Alle URLs starten mit /api/notes
public class NoteController {
    private final NoteRepository noteRepository;

    //Konstruktor für Repository-Übergabe
    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping //GET /api/notes --> alle Notizen laden
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @PostMapping //POST /api/notes --> neue Notiz speichern
    public Note createNote(@RequestBody Note note) {
        return noteRepository.save(note);
    }

    @GetMapping("/{id}") //GET /api/notes/{id} --> einzelne Notiz nach ID abrufen
    public Optional<Note> getNoteById(@PathVariable Long id) {
        return noteRepository.findById(id);
    }

    @PutMapping("/{id}") //PUT /api/notes/{id} --> einzelne Noitz ändern (updaten)
    public Note updateNote(@PathVariable Long id, @RequestBody Note noteDetails) {
        Note note = noteRepository.findById(id).orElseThrow();
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());
        return noteRepository.save(note);
    }

    @DeleteMapping("/{id}") //DELETE /api/notes/{id} --> einzelne Notiz löschen
    public void deleteNote(@PathVariable Long id) {
        noteRepository.deleteById(id);
    }
}

