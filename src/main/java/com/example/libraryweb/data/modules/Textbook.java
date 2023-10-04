package com.example.libraryweb.data.modules;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "textbook")
public class Textbook extends Book implements Availability {
    @Column(nullable = false)
    private Integer edition;

    @Column(nullable = false)
    private Boolean available;

    @Override
    public boolean getAvailable() {
        return this.available;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public Integer getEdition() {
        return edition;
    }
}

