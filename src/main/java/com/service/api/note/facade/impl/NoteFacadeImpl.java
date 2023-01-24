package com.service.api.note.facade.impl;

import com.service.api.note.converter.NoteDtoMapper;
import com.service.api.note.dto.NoteDto;
import com.service.api.note.facade.NoteFacade;
import com.service.api.note.service.NoteService;
import com.service.api.note.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NoteFacadeImpl implements NoteFacade {

    private final NoteDtoMapper noteMapper;
    private final NoteService noteService;
    private final UserService userService;

    @Override
    public List<NoteDto> getUserNotes(String username) {
        return noteService.getUserNotes(username)
                .stream()
                .map(noteMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveNote(NoteDto noteDto, String username) {
        var note = noteMapper.convertFromDto(noteDto);
        var user = userService.findByUsername(username);
        note.setUser(user);
        noteService.saveNote(note);
    }

    @Override
    public NoteDto getNote(String noteId, String ownerUsername) {
        var note = noteService.findNoteByIdAndUsername(UUID.fromString(noteId), ownerUsername);
        return noteMapper.convertToDto(note);
    }

    @Override
    public void delete(String noteId, String username) {
        noteService.delete(UUID.fromString(noteId), username);
    }
}
