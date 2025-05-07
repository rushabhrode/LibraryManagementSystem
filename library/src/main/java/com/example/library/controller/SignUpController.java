package com.example.library.controller;

import com.example.library.model.User;
import com.example.library.model.UserEmailVerification;
import com.example.library.repository.UserRepository;
import com.example.library.repository.UserEmailVerificationRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/signup")
public class SignUpController {

    private final UserRepository userRepository;
    private final UserEmailVerificationRepository otpRepository;
    private final JavaMailSender mailSender;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${EMAIL_USERNAME}")
    private String emailFrom;

    @Autowired
    public SignUpController(UserRepository userRepository,
                            UserEmailVerificationRepository otpRepository,
                            JavaMailSender mailSender,
                            BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.otpRepository = otpRepository;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody User user, HttpServletResponse response) {
        if (!user.getPassword().matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&]).+$")) {
            return ResponseEntity.badRequest().body("Password must be alphanumeric with a special character.");
        }

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        int otpCode = new Random().nextInt(9000) + 1000;
        String hashedOtp = passwordEncoder.encode(String.valueOf(otpCode));

        if (!existingUser.isPresent()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            UserEmailVerification otp = new UserEmailVerification();
            otp.setUserId(user.getId());
            otp.setUserEmail(user.getEmail());
            otp.setOtpCode(hashedOtp);
            otp.setCreatedAt(LocalDateTime.now());
            otp.setExpiresAt(LocalDateTime.now().plusMinutes(1));
            otpRepository.save(otp);

            addOtpCookie(response, user.getId());
            sendEmail(user.getEmail(), otpCode);

            return ResponseEntity.ok("Verify Email: OTP sent to " + maskEmail(user.getEmail()));
        }

        if (existingUser.get().isEmailVerified()) {
            return ResponseEntity.ok("Account already exists. Go to login.");
        }

        // Resend OTP
        User existing = existingUser.get();
        otpRepository.findByUserId(existing.getId())
                .ifPresentOrElse(
                        otp -> {
                            otp.setOtpCode(hashedOtp);
                            otp.setCreatedAt(LocalDateTime.now());
                            otp.setExpiresAt(LocalDateTime.now().plusMinutes(1));
                            otpRepository.save(otp);
                        },
                        () -> {
                            UserEmailVerification otp = new UserEmailVerification();
                            otp.setUserId(existing.getId());
                            otp.setUserEmail(existing.getEmail());
                            otp.setOtpCode(hashedOtp);
                            otp.setCreatedAt(LocalDateTime.now());
                            otp.setExpiresAt(LocalDateTime.now().plusMinutes(1));
                            otpRepository.save(otp);
                        });

        addOtpCookie(response, existing.getId());
        sendEmail(existing.getEmail(), otpCode);
        return ResponseEntity.ok("Email exists but not verified. OTP sent again.");
    }

    @PostMapping("/verifyEmail")
    public ResponseEntity<?> verifyEmail(@RequestParam String otpCode, HttpServletRequest request) {
        String userId = getOtpCookie(request);
        if (userId == null || otpCode == null) {
            return ResponseEntity.badRequest().body("Please enter your OTP code.");
        }

        Optional<UserEmailVerification> otpData = otpRepository.findByUserId(userId);
        if (otpData.isEmpty()) {
            return ResponseEntity.badRequest().body("OTP not found.");
        }

        if (!passwordEncoder.matches(otpCode, otpData.get().getOtpCode())) {
            return ResponseEntity.badRequest().body("Invalid OTP Code.");
        }

        if (LocalDateTime.now().isAfter(otpData.get().getExpiresAt())) {
            return ResponseEntity.badRequest().body("OTP Code expired.");
        }

        userRepository.findById(userId).ifPresent(user -> {
            user.setEmailVerified(true);
            userRepository.save(user);
        });

        return ResponseEntity.ok("Email verified successfully.");
    }

    @PostMapping("/resendOtp")
    public ResponseEntity<?> resendOtp(HttpServletRequest request) {
        String userId = getOtpCookie(request);
        Optional<User> userOpt = userRepository.findById(userId);

        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found.");
        }

        User user = userOpt.get();
        if (user.isEmailVerified()) {
            return ResponseEntity.ok("Email already verified. Go to login.");
        }

        Optional<UserEmailVerification> otpData = otpRepository.findByUserId(userId);
        if (otpData.isPresent() && LocalDateTime.now().isBefore(otpData.get().getExpiresAt())) {
            return ResponseEntity.ok("OTP already sent. Please wait.");
        }

        int otpCode = new Random().nextInt(9000) + 1000;
        String hashedOtp = passwordEncoder.encode(String.valueOf(otpCode));

        UserEmailVerification otp = new UserEmailVerification();
        otp.setUserId(userId);
        otp.setUserEmail(user.getEmail());
        otp.setOtpCode(hashedOtp);
        otp.setCreatedAt(LocalDateTime.now());
        otp.setExpiresAt(LocalDateTime.now().plusMinutes(1));
        otpRepository.save(otp);

        sendEmail(user.getEmail(), otpCode);
        return ResponseEntity.ok("OTP resent to " + maskEmail(user.getEmail()));
    }

    // ----- Utilities -----

    private void sendEmail(String to, int otp) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(emailFrom);
            helper.setTo(to);
            helper.setSubject("Verify Email - Library Management System");
            helper.setText("<p>Your OTP Code is <strong>" + otp + "</strong>. This will expire in 60 seconds!</p>", true);
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }

    private String getOtpCookie(HttpServletRequest request) {
        if (request.getCookies() == null) return null;
        for (Cookie cookie : request.getCookies()) {
            if ("otp-cookie".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    private void addOtpCookie(HttpServletResponse response, String userId) {
        Cookie cookie = new Cookie("otp-cookie", userId);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24); // 24 hours
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        response.addCookie(cookie);
    }

    private String maskEmail(String email) {
        int atIndex = email.indexOf('@');
        if (atIndex <= 1) return email;

        String front = email.substring(0, atIndex);
        String domain = email.substring(atIndex);
        String masked = front.charAt(0) + "*".repeat(Math.max(0, front.length() - 3)) + front.substring(front.length() - 2);
        return masked + domain;
    }
}
