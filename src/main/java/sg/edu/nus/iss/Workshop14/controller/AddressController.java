package sg.edu.nus.iss.Workshop14.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.Workshop14.model.Contact;
import sg.edu.nus.iss.Workshop14.service.ContactsRepo;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.List;

import javax.servlet.http.HttpServletResponse;


@Controller
public class AddressController {
    private Logger logger = Logger.getLogger(AddressController.class.getName());
    
    @Autowired
    ContactsRepo service;
    
    @GetMapping("/")
    public String contactForm(Model model){
        logger.log(Level.INFO, "Show the contact form");
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @GetMapping("/getContact/{contactId}")
    public String getContact(Model model, @PathVariable(value="contactId") 
            String contactId)
    {
        logger.info("contactId > " + contactId);
        Contact ctc = service.findById(contactId);
        logger.info("getId > " + ctc.getId());
        logger.info("getEmail > " + ctc.getEmail());
        
        model.addAttribute("contact", ctc);
        return "showContact";
    }

    @GetMapping("/contact")
    public String getAllContact(Model model, @RequestParam(name="startIndex") String startIdx){
        List<Contact> resultFromSvc = service.findAll(Integer.parseInt(startIdx));
        logger.info("resultFromSvc >> " + resultFromSvc);
        model.addAttribute("contacts", resultFromSvc);
        return "listContact";
    }

    @PostMapping("/contact")
    public String contactSubmit(@ModelAttribute Contact contact, 
        Model model,
        HttpServletResponse httpResponse){
        logger.log(Level.INFO, "Id : " + contact.getId());
        logger.log(Level.INFO, "Name : " + contact.getName());
        logger.log(Level.INFO, "Email : " + contact.getEmail());
        logger.log(Level.INFO, "Phone Number : " + contact.getPhoneNumber());
        
        httpResponse.setStatus(HttpStatus.CREATED.value());
        return "showContact";
        
    }

}