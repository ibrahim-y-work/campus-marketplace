package com.campusmarketplace.marketplace.restcontroller;

import com.campusmarketplace.marketplace.dto.ItemOptionRegistrationRequest;
import com.campusmarketplace.marketplace.entity.ItemOption;
import com.campusmarketplace.marketplace.service.ItemOptionService;
import com.campusmarketplace.marketplace.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/item-options")
public class ItemOptionRestController {
    private final ItemOptionService itemOptionService;

    @Autowired
    public ItemOptionRestController(ItemOptionService itemOptionService) {
        this.itemOptionService = itemOptionService;
    }

    // add
    @PostMapping
    public ResponseEntity<ItemOption> addItemOption(@Valid @RequestBody ItemOptionRegistrationRequest request){
        ItemOption theItemOption=itemOptionService.addItemOption(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(theItemOption);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<ItemOption> updateItemOption(@PathVariable UUID id, @Valid @RequestBody ItemOptionRegistrationRequest request){
        ItemOption theItemOption=itemOptionService.updateItemOption(id,request);
        return ResponseEntity.status(HttpStatus.OK).body(theItemOption);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemOption(@PathVariable UUID id){
        itemOptionService.deleteItemOptionById(id);
        return ResponseEntity.noContent().build();
    }
    // read
    @GetMapping("/{id}")
    public ResponseEntity<ItemOption> getItemOption(@PathVariable UUID id){
        ItemOption itemOption=itemOptionService.getItemOptionById(id);
        return ResponseEntity.status(HttpStatus.OK).body(itemOption);
    }

}
