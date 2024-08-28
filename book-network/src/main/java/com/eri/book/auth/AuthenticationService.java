package com.eri.book.auth;

import com.eri.book.email.EmailService;
import com.eri.book.email.EmailTemplateName;
import com.eri.book.role.RoleRepository;
import com.eri.book.user.Token;
import com.eri.book.user.TokenRepository;
import com.eri.book.user.User;
import com.eri.book.user.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.persistence.Lob;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    @Value("${application.mailing.frontend.activation-url}")

    private String activationUrl;

    public void register(RegistrationRequest request) throws MessagingException {
        var userRole =roleRepository.findByName("USER")
                // todo- better exception handinling
                .orElseThrow(()-> new IllegalStateException("Role USER is not initialized"));
 var user= User.builder()
         .firstname(request.getFirstname())
         .lastname(request.getLastname())
         .email(request.getEmail())
         .password(passwordEncoder.encode(request.getPassword()))
         .accountLocked(false)
         .enabled(false)
         .roles(List.of(userRole))
         .build();
 userRepository.save(user);
 sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);
        emailService.sendEmail(
                user.getEmail(),
                user.fullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Account activation"
        );
    }

    private String generateAndSaveActivationToken(User user) {
        //generate a token
        String generatedToken= generateActivationCode(6);
        var token= Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
               tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String characters="0123456789";
        StringBuilder codeBuilder= new StringBuilder();
        SecureRandom secureRandom= new SecureRandom();
        for(int i=0; i<length;i++){
            int randomIndex= secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }
}