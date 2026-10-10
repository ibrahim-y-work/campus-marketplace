package com.campusmarketplace.marketplace.restcontroller;

import com.campusmarketplace.marketplace.dto.ItemRegistrationRequest;
import com.campusmarketplace.marketplace.entity.Item;
import com.campusmarketplace.marketplace.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/items")
public class ItemRestController {
    private final ItemService itemService;

    @Autowired
    public ItemRestController(ItemService itemService) {
        this.itemService = itemService;
    }


    // add
    @PostMapping
    public ResponseEntity<Item> addItem(@Valid @RequestBody ItemRegistrationRequest request){
        Item theItem=itemService.addItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(theItem);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable UUID id, @Valid @RequestBody ItemRegistrationRequest request){
        Item theItem=itemService.updateItem(id,request);
        return ResponseEntity.status(HttpStatus.OK).body(theItem);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id){
        itemService.deleteItemById(id);
        return ResponseEntity.noContent().build();
    }
    // read
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable UUID id){
        Item item=itemService.getItemById(id);
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

}
