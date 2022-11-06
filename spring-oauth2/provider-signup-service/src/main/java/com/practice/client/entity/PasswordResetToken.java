package com.practice.client.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class PasswordResetToken {

    // expiration time of 10 minutes
    private static final int EXPIRATION_TIME = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String token;
    private Date expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_USER_PASSWORD_TOKEN"))
    private User user;

    public PasswordResetToken(User user, String token) {
        super();
        this.user = user;
        this.token = token;
        this.expirationTime = calculateExpirationDate();
    }

    public PasswordResetToken(String token) {
        super();
        this.token =token;
        this.expirationTime = calculateExpirationDate();
    }


    private Date calculateExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        // get time at this moment
        calendar.setTimeInMillis(new Date().getTime());

        // add EXPIRATION_TIME minutes to current time
        calendar.add(Calendar.MINUTE, PasswordResetToken.EXPIRATION_TIME);

        return new Date(calendar.getTime().getTime());
    }

}
