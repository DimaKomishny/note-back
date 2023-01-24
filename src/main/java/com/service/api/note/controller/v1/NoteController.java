package com.service.api.note.controller.v1;

import com.service.api.note.dto.NoteDto;
import com.service.api.note.dto.ResponseMessageDto;
import com.service.api.note.facade.NoteFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/v1/note")
@AllArgsConstructor
public class NoteController {

    private final NoteFacade noteFacade;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('default')")
    public ResponseEntity<List<NoteDto>> getAllNotes(Principal principal) {
        var notes = noteFacade.getUserNotes(principal.getName());
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('default')")
    public ResponseEntity<ResponseMessageDto> saveNote(@RequestBody NoteDto noteDto, Principal principal) {
        noteFacade.saveNote(noteDto, principal.getName());
        return new ResponseEntity<>(new ResponseMessageDto("Note was saved"), HttpStatus.OK);
    }

    @GetMapping("/{noteId}")
    @PreAuthorize("hasAnyAuthority('default')")
    public ResponseEntity<NoteDto> getNote(Principal principal, @PathVariable("noteId") String noteId) {
        var note = noteFacade.getNote(noteId, principal.getName());
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @DeleteMapping("/{noteId}")
    @PreAuthorize("hasAnyAuthority('default')")
    public ResponseEntity<ResponseMessageDto> deleteNote(Principal principal, @PathVariable("noteId") String noteId) {
        noteFacade.delete(noteId, principal.getName());
        return new ResponseEntity<>(new ResponseMessageDto("Note was deleted"), HttpStatus.OK);
    }
}
