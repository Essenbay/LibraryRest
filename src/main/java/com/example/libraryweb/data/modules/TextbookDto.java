package com.example.libraryweb.data.modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextbookDto {
    public String title;
    public Long authorId;

    public Long genreId;
    public Boolean available;
    public int edition;
}
