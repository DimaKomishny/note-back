package com.service.api.note.service;

import com.service.api.note.entity.Note;

import java.util.List;
import java.util.UUID;

public interface NoteService {

    List<Note> getUserNotes(String username);
    void saveNote(Note note);
    Note findNoteByIdAndUsername(UUID noteId, String ownerUsername);
    void delete(UUID noteId, String username);
}