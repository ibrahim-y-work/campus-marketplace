package com.campusmarketplace.marketplace.repository;

import com.campusmarketplace.marketplace.entity.ItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemOptionRepository  extends JpaRepository<ItemOption, UUID> {
}
