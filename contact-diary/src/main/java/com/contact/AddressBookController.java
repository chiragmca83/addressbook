package com.contact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.contact.error.ContactNotFoundException;

@RestController
@RequestMapping("/addressBooks")
public class AddressBookController {

    @Autowired
    private AddressBookRepository AddressBookrepository;

    @GetMapping //("/addressBooks")
    List<AddressBook> findAll() {
        return AddressBookrepository.findAll();
    }

    @PostMapping //("/addressBooks")
    @ResponseStatus(HttpStatus.CREATED)
    AddressBook newBook(@RequestBody AddressBook newBook) {
        return AddressBookrepository.save(newBook);
    }

    @GetMapping("/{id}")
    AddressBook findOne(@PathVariable Long id) {
        return AddressBookrepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(id));
    }

    @PutMapping("/{id}")
    AddressBook saveOrUpdate(@RequestBody AddressBook addressBook, @PathVariable Long id) {

        return AddressBookrepository.findById(id)
                .map(x -> {
                    x.setFirstName(addressBook.getFirstName());
                    x.setMobile(addressBook.getMobile());
                    return AddressBookrepository.save(x);
                })
                .orElseGet(() -> {
                    addressBook.setId(id);
                    return AddressBookrepository.save(addressBook);
                });
    }

    @DeleteMapping("/{id}")
    void deleteBook(@PathVariable Long id) {
        AddressBookrepository.deleteById(id);
    }

}
