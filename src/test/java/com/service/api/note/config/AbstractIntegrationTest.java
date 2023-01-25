package com.service.api.note.config;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@Configuration
@Transactional
@SpringBootTest
public abstract class AbstractIntegrationTest {
}