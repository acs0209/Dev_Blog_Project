package sgdevcamp.blog.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Question question;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    private SiteUser siteUser;

    private LocalDateTime modifyDate;

    @OneToMany(mappedBy = "answer" , cascade = CascadeType.REMOVE)
    private List<Comment> commentList;

}
