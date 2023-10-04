package com.example.libraryweb.data.modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EbookDto {
    public String title;
    public Long authorId;

    public Long genreId;
    public Double size;
    public String format;
}
