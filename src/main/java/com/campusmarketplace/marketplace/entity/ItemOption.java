package com.campusmarketplace.marketplace.entity;

import com.campusmarketplace.marketplace.enums.ItemOptionStatus;
import com.campusmarketplace.marketplace.enums.OptionType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="item_options")
public class ItemOption {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @Column(name="option_type")
    @Enumerated(EnumType.STRING)
    private OptionType optionType;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private ItemOptionStatus itemOptionStatus;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    public ItemOption(Item item, OptionType optionType, ItemOptionStatus itemOptionStatus, BigDecimal price, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.item = item;
        this.optionType = optionType;
        this.itemOptionStatus = itemOptionStatus;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ItemOption() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public OptionType getOptionType() {
        return optionType;
    }

    public void setOptionType(OptionType optionType) {
        this.optionType = optionType;
    }

    public ItemOptionStatus getItemOptionStatus() {
        return itemOptionStatus;
    }

    public void setItemOptionStatus(ItemOptionStatus itemOptionStatus) {
        this.itemOptionStatus = itemOptionStatus;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ItemOption{" +
                "id=" + id +
                ", item=" + item +
                ", optionType=" + optionType +
                ", itemOptionStatus=" + itemOptionStatus +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
