/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication120;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author sraa
 */
class SessionManager {
    private static SessionManager instance;
    private Map<Integer, LinkedList<Book>> userBookListMap;

    private SessionManager() {
        userBookListMap = new HashMap<>();
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public Map<Integer, LinkedList<Book>> getUserBookListMap() {
        return userBookListMap;
    }
}

