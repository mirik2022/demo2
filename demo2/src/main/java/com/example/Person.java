package com.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="person")
public class Person {
    
    @Id
    private Long userId;
    private Long id;
    private String title;
    private String body;

    /*public Person() {}
    public Person(Long userId, Long id, String tile, String bdy) {
        this.userId = userId;
        this.id = id;
        this.title = tile;
        this.body = bdy;
    }*/

    /*public Long getuserId() {
        return userId;
    }

    public void setuserId(Long userId) {
        this.userId = userId;
    }

    public Long getid() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String t_itle) {
        this.title = t_itle;
    }

    public String getbody() {
        return body;
    }

   public void setbody(String b_ody) {
       this.body = b_ody;
    }*/

    public String toString() {
        return " Person {userId = " + String.valueOf(userId) + ", id = " + String.valueOf(id) + ", title = " + title + ", body = " + body + "}";
    }
}
