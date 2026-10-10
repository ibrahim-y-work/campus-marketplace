package com.campusmarketplace.marketplace.service;

import com.campusmarketplace.marketplace.dto.ItemRegistrationRequest;
import com.campusmarketplace.marketplace.entity.Item;
import com.campusmarketplace.marketplace.entity.User;
import com.campusmarketplace.marketplace.repository.ItemRepository;
import com.campusmarketplace.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ItemService{
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Autowired
    public ItemService(ItemRepository itemrepository, UserRepository userRepository) {
        this.itemRepository = itemrepository;
        this.userRepository = userRepository;
    }


    private Item convertDTOToItem(ItemRegistrationRequest item){
        User owner=userRepository.findById(item.getOwnerId()).orElseThrow(()->
                new RuntimeException("The owner doesn't exist"));

        Item newItem=new Item();

        newItem.setName(item.getName());
        newItem.setDescription(item.getDescription());
        newItem.setCategory(item.getCategory());
        newItem.setCondition(item.getCondition());
        newItem.setItemStatus(item.getStatus());
        newItem.setOwner(owner);

        return newItem;

    }
    @Transactional
    public Item addItem(ItemRegistrationRequest request){
        Item item=convertDTOToItem(request);

        return itemRepository.save(item);
    }

    @Transactional
    public void deleteItemById(UUID id){
        if(!itemRepository.existsById(id)){
            throw new RuntimeException("Item doesn't exist");
        }
        itemRepository.deleteById(id);
    }
    @Transactional
    public Item updateItem(UUID id,ItemRegistrationRequest request){
        Item item=itemRepository.findById(id).orElseThrow(()->
                new RuntimeException("Item doesn't exist"));

        if(!request.getOwnerId().equals(item.getOwner().getId()))
            throw new RuntimeException("Changing the item owner is not allowed");

        item.setName(request.getName());
        item.setDescription(request.getDescription());
        item.setItemStatus(request.getStatus());
        item.setCondition(request.getCondition());
        item.setCategory(request.getCategory());


        return itemRepository.save(item);
    }

    @Transactional(readOnly = true)
    public Item getItemById(UUID id){
        return itemRepository.findById(id).orElseThrow(()->
                new RuntimeException("Item doesn't exist"));
    }

}
