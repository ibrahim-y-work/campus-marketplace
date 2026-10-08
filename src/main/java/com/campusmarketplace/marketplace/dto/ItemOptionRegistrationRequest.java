package com.campusmarketplace.marketplace.dto;


import com.campusmarketplace.marketplace.enums.ItemOptionStatus;
import com.campusmarketplace.marketplace.enums.OptionType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public class ItemOptionRegistrationRequest {
    @NotNull(message = "Item ID is required")
    private UUID itemId;

    @NotNull(message = "Option Type is required")
    private OptionType optionType;

    @NotNull(message = "Item Option Status is required")
    private ItemOptionStatus status;

    @NotNull(message = "Price is required")
    private BigDecimal price;

    // Constructors
    public ItemOptionRegistrationRequest() {
    }

    public ItemOptionRegistrationRequest(UUID itemId, OptionType optionType, ItemOptionStatus status, BigDecimal price) {
        this.itemId = itemId;
        this.optionType = optionType;
        this.status = status;
        this.price = price;
    }

    // Getters and Setters
    public UUID getItemId() {
        return itemId;
    }

    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }

    public OptionType getOptionType() {
        return optionType;
    }

    public void setOptionType(OptionType optionType) {
        this.optionType = optionType;
    }

    public ItemOptionStatus getStatus() {
        return status;
    }

    public void setStatus(ItemOptionStatus status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}