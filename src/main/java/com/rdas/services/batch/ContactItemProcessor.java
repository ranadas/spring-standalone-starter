package com.rdas.services.batch;

import org.springframework.batch.item.ItemProcessor;

/**
 * Created by rdas on 08/04/2015.
 */
public class ContactItemProcessor implements ItemProcessor<Contact, Contact> {

    public Contact process(final Contact contact) throws Exception {
        final String name = contact.getName().toUpperCase();
        final String email = contact.getEmail().toUpperCase();
        final String status = contact.getStatus().toUpperCase();

        final Contact transformedContact = new Contact(name, email, status);

        return transformedContact;
    }

}