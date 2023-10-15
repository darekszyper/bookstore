package com.ai.bookstore.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateBookRequest {
    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Price is required")
    private Double price;

    @NotNull(message = "Quantity available is required")
    private Integer quantityAvailable;

    @NotNull(message = "Author ID is required")
    private Long authorId;

    @NotNull(message = "Genre ID is required")
    private Long genreId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    // Getters and setters
}