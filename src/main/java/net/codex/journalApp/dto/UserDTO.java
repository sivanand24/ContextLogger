package net.codex.journalApp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotEmpty
    private String userName;
    private String email;
    private boolean sentimentAnalysis;
    @NotEmpty
    private String password;
}
