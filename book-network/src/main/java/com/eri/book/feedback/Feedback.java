package com.eri.book.feedback;

import com.eri.book.common.BaseEntity;
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

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@EntityListeners(AuditingEntityListener.class)
public class Feedback extends BaseEntity {
//    @Id
//    @GeneratedValue
//    private Integer id;
    private Double note; //1-5 stars
    private String comment;
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
