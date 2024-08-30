package com.eri.book.book;

import com.eri.book.common.BaseEntity;
import com.eri.book.feedback.Feedback;
import com.eri.book.history.BookTransactionHistory;
import com.eri.book.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@EntityListeners(AuditingEntityListener.class)
public class Book extends BaseEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String bookCover;
    private boolean archived;
    private boolean sharable;
    //to create a many to one relationship between book and user
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "book")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "book")
    private List<BookTransactionHistory> histories;

//    @CreatedDate
//    @Column(nullable = false, updatable = false)
//    private LocalDate createdAt;
//    @LastModifiedDate
//    @Column(insertable = false)
//    private LocalDate lastModifiedDate;
//    @CreatedBy
//    @Column(nullable = false, updatable = false)
//    private Integer createdBy;
//    @LastModifiedBy
//    @Column(insertable = false)
//    private Integer lastModifiedBy;
}
