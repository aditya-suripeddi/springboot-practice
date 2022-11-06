package com.practice.client.service;

import com.practice.client.entity.PasswordResetToken;
import com.practice.client.entity.VerificationToken;
import com.practice.client.modal.UserModal;
import com.practice.client.repository.PassworResetTokenRespository;
import com.practice.client.repository.UserRepository;
import com.practice.client.entity.User;
import com.practice.client.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PassworResetTokenRespository passwordResetTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserModal userModal) {
        User user = new User();
        user.setEmail(userModal.getEmail());
        user.setFirstName(userModal.getFirstName());
        user.setLastName(userModal.getLastName());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(userModal.getPassword()));
        userRepository.save(user);
        return user;
    }

    public void saveVerificationToken(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }

    public String validateVerificationToken(String token) {
        VerificationToken verificationToken =
                verificationTokenRepository.findByToken(token);

        if( verificationToken == null ) {
            return "invalid" ;
        }

        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        long expirationTimeInstant = verificationToken.getExpirationTime().getTime();
        long currentTimeInstant = calendar.getTime().getTime();

        if( currentTimeInstant  >= expirationTimeInstant )
        {
            return "expired";
        }

        // when token passed is valid and within expiration time
        // mark user enabled and update in database
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken
                = verificationTokenRepository.findByToken(oldToken);

        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(verificationToken);
        return verificationToken ;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(user, token);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    public String validatePasswordResetToken(String token) {
        PasswordResetToken passwordResetToken =
                passwordResetTokenRepository.findByToken(token);

        if( passwordResetToken == null ) {
            return "invalid" ;
        }

        User user = passwordResetToken.getUser();
        Calendar calendar = Calendar.getInstance();
        long expirationTimeInstant = passwordResetToken.getExpirationTime().getTime();
        long currentTimeInstant = calendar.getTime().getTime();

        if( currentTimeInstant  >= expirationTimeInstant )
        {
            return "expired";
        }

        // when token passed is valid and within expiration time
        // mark user enabled and update in database
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

    public Optional<User> getUserByPasswordResetToken(String token) {
        return Optional.ofNullable( passwordResetTokenRepository.findByToken(token).getUser() );
    }

    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }
}
