package com.service.api.note.converter;

import com.service.api.note.dto.NoteDto;
import com.service.api.note.entity.Note;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NoteDtoMapper {

    public Note convertFromDto(NoteDto dto) {
        return Note.builder()
                .id(dto.getId() == null ? null : UUID.fromString(dto.getId()))
                .title(dto.getTitle())
                .text(dto.getText())
                .build();
    }

    public NoteDto convertToDto(Note note) {
        return NoteDto.builder()
                .id(note.getId().toString())
                .title(note.getTitle())
                .text(note.getText())
                .build();
    }
}
