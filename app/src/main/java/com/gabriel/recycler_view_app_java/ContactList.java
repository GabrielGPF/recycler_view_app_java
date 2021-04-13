package com.gabriel.recycler_view_app_java;

import java.util.ArrayList;

public class ContactList {
    private ArrayList<String[]> contactsData = new ArrayList<String[]>();

    private ContactList() {
    }

    private static ContactList instance = null;
    public static ContactList GetInstance() {
        if (instance == null) {
            instance = new ContactList();
        }
        return instance;
    }

    public ArrayList<String[]> getContactsData() {
        return contactsData;
    }
}
