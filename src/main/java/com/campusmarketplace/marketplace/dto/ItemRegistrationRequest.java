package com.campusmarketplace.marketplace.dto;

import com.campusmarketplace.marketplace.enums.Category;
import com.campusmarketplace.marketplace.enums.Condition;
import com.campusmarketplace.marketplace.enums.ItemStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class ItemRegistrationRequest {

    @NotNull(message = "Owner ID is required")
    private UUID ownerId;

    @NotBlank(message = "Name is required and cannot be empty")
    private String name;

    @NotBlank(message = "Description is required and cannot be empty")
    private String description;

    @NotNull(message = "Status is required")
    private ItemStatus status;

    @NotNull(message = "Condition is required")
    private Condition condition;

    @NotNull(message = "Category is required")
    private Category category;

    // Constructors
    public ItemRegistrationRequest() {
    }

    public ItemRegistrationRequest(UUID ownerId, String name, String description, ItemStatus status, Condition condition, Category category) {
        this.ownerId = ownerId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.condition = condition;
        this.category = category;
    }

    // Getters and Setters
    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}