package com.ai.bookstore.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class GenreRequest {
    @NotBlank(message = "Genre name is required")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
