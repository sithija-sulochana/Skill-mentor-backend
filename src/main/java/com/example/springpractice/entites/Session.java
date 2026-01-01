package com.example.springpractice.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "session")
@Data
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIgnore
    private Student student;

    @ManyToOne
    @JoinColumn(name = "mentor_id", nullable = false)
    @JsonIgnore
    private Mentor mentor;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    @JsonIgnore
    private Subject subject;


    @Column(name = "session_at", nullable = false)
    private Date sessionAt;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Column(name = "session_status", length = 50)
    private String sessionStatus;

    @Column(name = "meeting_link")
    private String meetingLink;

    @Column(name = "session_notes", columnDefinition = "TEXT")
    private String sessionNotes;

    @Column(name = "student_review", columnDefinition = "TEXT")
    private String studentReview;

    @Column(name = "student_rating")
    private Integer studentRating;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToMany(mappedBy = "session")
//    @JsonIgnore
    private List<Comment> comments;
    @Column(name = "comment_count")
//    @Transient
    private Integer commentCount=0;


//    public Integer getCommentCount() {
//
//        if (comments != null) {
//            return comments.size();
//        }
//        return 0;
//    }
    @OneToOne(mappedBy = "session")
    private Payment payment;

}