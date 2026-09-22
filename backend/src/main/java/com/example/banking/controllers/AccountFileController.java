package com.example.banking.controllers;

import com.example.banking.models.BankAccount;
import com.example.banking.services.BankingService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@RestController
@RequestMapping("/api/accounts")
public class AccountFileController {
    private final BankingService banking;

    public AccountFileController(BankingService banking) {
        this.banking = banking;
    }

    @PostMapping("/{id}/deposits/upload")
    public BankAccount uploadDeposit(@PathVariable Long id,
            Authentication auth, @RequestParam("file") MultipartFile file)
            throws IOException {
        // Supplied: keep uploads small and require a non-empty TXT file.
        String name = file.getOriginalFilename();
        if (file.isEmpty() || file.getSize() > 1024 || name == null
                || !name.toLowerCase(Locale.ROOT).endsWith(".txt")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Choose a non-empty .txt file of at most 1024 bytes.");
        }

        try {
            // TODO: Read the text, convert it to BigDecimal, and make the deposit.
            throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED,
                    "Complete the TXT deposit ticket.");
        } catch (NumberFormatException exception) {
            // Supplied: show a helpful message when the text is not a number.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The file must contain one amount, such as 350.");
        }
    }
}
