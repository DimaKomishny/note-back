package com.service.api.note.repository;

import com.service.api.note.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note, UUID> {

    @Query("select n from Note n where n.user.username = :username")
    List<Note> findByUsername(@Param("username") String username);

    @Query("select n from Note n where n.id = :id and n.user.username = :username")
    Optional<Note> findByIdAndUsername(@Param("id")UUID id, @Param("username") String username);
}
