package nl.topicus.overheid.javafactorybot.test.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Article {
    // Attributes
    private String title;
    private String content;
    private Date creationDate;

    // Relations
    private User author;
    private List<Comment> comments;
}
