package com.koreait.cgvproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Entity
@SequenceGenerator(
        name="seq_Notification",
        sequenceName = "seq_Notification",
        initialValue = 1,
        allocationSize = 1
)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="Notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_Notification")
    private Long idx;

    private String title;
    private String content;
    private String category;
    private int hit;
    @CreatedDate
    private LocalDateTime regDate;





    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regdate) {
        this.regDate = regdate;
    }
}
