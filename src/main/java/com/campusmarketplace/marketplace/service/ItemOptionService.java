package com.campusmarketplace.marketplace.service;

import com.campusmarketplace.marketplace.dto.ItemOptionRegistrationRequest;
import com.campusmarketplace.marketplace.entity.Item;
import com.campusmarketplace.marketplace.entity.ItemOption;
import com.campusmarketplace.marketplace.repository.ItemOptionRepository;
import com.campusmarketplace.marketplace.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ItemOptionService {
    private final ItemOptionRepository optionRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public ItemOptionService(ItemOptionRepository optionRepository, ItemRepository itemRepository) {
        this.optionRepository = optionRepository;
        this.itemRepository = itemRepository;
    }

    private ItemOption convertDTOToItemOption(ItemOptionRegistrationRequest request){
        Item item=itemRepository.findById(request.getItemId())
                .orElseThrow(()->
                        new RuntimeException("Item of the Item Option doesn't exist"));
        ItemOption option=new ItemOption();

        option.setItem(item);
        option.setPrice(request.getPrice());
        option.setItemOptionStatus(request.getStatus());
        option.setOptionType(request.getOptionType());

        return option;
    }

    @Transactional
    public ItemOption addItemOption(ItemOptionRegistrationRequest request){
        ItemOption option=convertDTOToItemOption(request);

        return optionRepository.save(option);
    }

    @Transactional
    public void deleteItemOptionById(UUID id){
        if(!optionRepository.existsById(id))
            throw new RuntimeException("Item Option doesn't exist");

        optionRepository.deleteById(id);
    }

    @Transactional
    public ItemOption updateItemOption(UUID id,ItemOptionRegistrationRequest request){
        ItemOption itemOption=optionRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Item Option doesn't exist")
        );
        
        if(!request.getItemId().equals(itemOption.getItem().getId())) {
            throw new RuntimeException("Changing the parent item of an option is not allowed");
        }

        itemOption.setItemOptionStatus(request.getStatus());
        itemOption.setOptionType(request.getOptionType());
        itemOption.setPrice(request.getPrice());

        return optionRepository.save(itemOption);

    }

    @Transactional(readOnly = true)
    public ItemOption getItemOptionById(UUID id){
        return optionRepository.findById(id).orElseThrow(()->
                new RuntimeException("Item Option doesn't exist"));
    }
}
