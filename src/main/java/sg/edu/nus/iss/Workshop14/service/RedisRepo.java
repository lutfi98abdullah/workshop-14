package sg.edu.nus.iss.Workshop14.service;

import sg.edu.nus.iss.Workshop14.model.Contact;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface RedisRepo {
    public void save(final Contact ctc);
    public Contact findById(final String contactId);
    public List<Contact> findAll(int StartIndex);
}
