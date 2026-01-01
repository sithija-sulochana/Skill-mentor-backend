package com.example.springpractice.services;


import com.example.springpractice.dtos.SessionDTO;
import com.example.springpractice.entites.Session;

import java.util.List;

public interface SessionService {

    Session createNewSession(SessionDTO sessionDTO);
    List<Session> getAllSessions();
    Session getSessionById(Long id);
    Session updateSessionById(Long id, SessionDTO updatedSessionDTO);
    void deleteSession(Long id);
}
