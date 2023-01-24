package com.service.api.note.facade;

import com.service.api.note.dto.NoteDto;

import java.util.List;

public interface NoteFacade {

    List<NoteDto> getUserNotes(String username);
    void saveNote(NoteDto noteDto, String username);
    NoteDto getNote(String noteId, String userName);
    void delete(String noteId, String username);
}
