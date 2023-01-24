package com.service.api.note.facade.impl;

import com.service.api.note.entity.Note;
import com.service.api.note.repository.NoteRepository;
import com.service.api.note.service.NoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Override
    public List<Note> getUserNotes(String username) {
        return noteRepository.findByUsername(username);
    }

    @Override
    public void saveNote(Note note) {
        noteRepository.save(note);
        log.info("Note {} was saved", note.getId());
    }

    @Override
    public Note findNoteByIdAndUsername(UUID noteId, String ownerUsername) {
        return noteRepository.findByIdAndUsername(noteId, ownerUsername)
                .orElseThrow(() -> new NoSuchElementException(String.format("Note %s doesnt exist", noteId)));
    }

    @Override
    public void delete(UUID noteId, String username) {
        var note = noteRepository.findById(noteId)
                .orElseThrow(() -> new NoSuchElementException(String.format("Note %s doesnt exist", noteId)));
        if (!note.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException(String.format("User %s has not access to %s", username, noteId));
        }
        noteRepository.delete(note);
    }
}