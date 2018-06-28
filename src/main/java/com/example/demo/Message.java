package com.example.demo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Message {
    public long getId() {
        return id;
    }

    public Message() {
        recipie = new HashSet<>();
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getMessageContainer() {
        return messageContainer;
    }

    public void setMessageContainer(String messageContainer) {
        this.messageContainer = messageContainer;
    }

    public Set<Recipient> getRecipie() {
        return recipie;
    }

    public void setRecipie(Set<Recipient> recipie) {
        this.recipie = recipie;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String  messageContainer;

    @ManyToMany()
    private Set<Recipient> recipie;

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    private int likeCount=0;
}
