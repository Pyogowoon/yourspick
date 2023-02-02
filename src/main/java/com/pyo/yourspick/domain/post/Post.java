package com.pyo.yourspick.domain.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pyo.yourspick.domain.postcomment.PostComment;
import com.pyo.yourspick.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Post {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    private String title;

    private String content;

    private String entryTitle;

    private String entryContent;

    private String actor;

    private String job;

    private String height;

    private String weight;

    @JsonIgnoreProperties
    @JoinColumn(name="userId")
    @ManyToOne
    private User user;

    @OrderBy("id DESC")
    @JsonIgnoreProperties({"post"})
    @OneToMany(mappedBy = "post")
    private List<PostComment> postComment;

    private String postImageUrlLeft;

    private String postImageUrlRight;
    private String postVideoUrl;

    private String createDate;

    @PrePersist
    public void createDate(){
        this.createDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
