package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
public class MessageBoardController {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    RecipientRepository recipientRepository;

    @RequestMapping("/")
    public String homePage(Model model)
    {

        model.addAttribute("messagelist",messageRepository.findAll());
        model.addAttribute("recipilist",recipientRepository.findAll());
        return "index";
    }

    @RequestMapping("/addmessage")
    public String addPet(Model model)
    {
        model.addAttribute("aMessage", new Message());
        model.addAttribute("messageRecip",recipientRepository.findAll());
        return "compose";
    }

    @RequestMapping("/savemessage")
    public String savePet(@ModelAttribute("aMessage") Message message, Model model)
    {

        messageRepository.save(message);
        model.addAttribute("messageNow",recipientRepository.findAll());
        return "redirect:/";
    }

    @RequestMapping("/redirect/{id}")
    public String redirect(@PathVariable("id") long id, Model model){
        Message mess=messageRepository.findById(id).get();
        mess.setLikeCount(mess.getLikeCount()+1);
        messageRepository.save(mess);

        return "redirect:/";
    }


    @PostConstruct
    public void fillTables()
    {
        Recipient recipi = new Recipient();
        recipi.setRecipName("Zola man");
        recipientRepository.save(recipi);

         recipi = new Recipient();
        recipi.setRecipName("Rafel");
        recipientRepository.save(recipi);


         recipi = new Recipient();
        recipi.setRecipName("Mark");
        recipientRepository.save(recipi);

    }
}


