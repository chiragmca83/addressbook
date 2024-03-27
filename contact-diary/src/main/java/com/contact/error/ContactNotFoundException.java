package com.contact.error;

public class ContactNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContactNotFoundException(Long id) {
        super("Book id not found : " + id);
    }

}
