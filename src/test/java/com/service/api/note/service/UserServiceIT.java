package com.service.api.note.service;

import com.service.api.note.config.AbstractIntegrationTest;
import com.service.api.note.entity.Gender;
import com.service.api.note.entity.Note;
import com.service.api.note.entity.Role;
import com.service.api.note.entity.Status;
import com.service.api.note.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceIT extends AbstractIntegrationTest {

    @Autowired
    private UserService sut;
    @Autowired
    private EntityManager entityManager;


    @Test
    public void shouldDeleteUserWithNote() {
        User persistUser = createUser();
        persistUser.setNotes(createNotes(1));
        entityManager.persist(persistUser);
        entityManager.flush();

        sut.delete(persistUser.getId());
        boolean isPresent = entityManager.contains(persistUser);

        assertFalse(isPresent);
    }

    @Test
    public void shouldSaveUser() {
        User user = createUser();

        sut.saveUser(user);
        var persistUser = entityManager.find(User.class, user.getId());

        assertEquals(user, persistUser);
    }

    @Test
    public void shouldThrowExceptionAccessDeniedWhenAnotherSaveYourProfile() {
        User user1 = createUser();
        User user2 = createUser();
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.flush();
        User user1Updated = createUser();
        user1Updated.setId(user1.getId());
        String expectedMessage = "User was tried updated another user";

        Exception actualException = assertThrows(AccessDeniedException.class,
                () -> sut.saveYourself(user1Updated, user2.getUsername()));

        assertTrue(actualException.getMessage().contains(expectedMessage));
    }

    private User createUser() {
        return User.builder()
                .role(Role.USER)
                .status(Status.ACTIVE)
                .gender(Gender.FEMALE)
                .username(UUID.randomUUID().toString())
                .password(UUID.randomUUID().toString())
                .lastName(UUID.randomUUID().toString())
                .firstName(UUID.randomUUID().toString())
                .dateOfBirth(new Date(946677601L))
                .build();
    }

    private List<Note> createNotes(int number) {
        List<Note> notes = new ArrayList<>(number);
        for (int i = 0; i < number; i++) {
            notes.add(createNote());
        }
        return notes;
    }

    private Note createNote() {
        return Note.builder()
                .title(UUID.randomUUID().toString())
                .text(UUID.randomUUID().toString())
                .build();
    }
}