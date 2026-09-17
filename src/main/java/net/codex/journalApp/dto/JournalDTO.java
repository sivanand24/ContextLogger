package net.codex.journalApp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.codex.journalApp.enums.Sentiment;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JournalDTO {
    @NotEmpty
    private String title;
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;

}
