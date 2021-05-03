package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item itema = new Item("itemA",10000,10);
        //when
        Item saveditem = itemRepository.save(itema);
        //then
        assertThat(itemRepository.findById(itema.getId())).isEqualTo(saveditem);
    }

    @Test
    void findAll() {
        //given
        Item itema = new Item("itemA",20000,10);
        Item itemb = new Item("itemB",10000,15);

        itemRepository.save(itema);
        itemRepository.save(itemb);

        //when
        List<Item> all = itemRepository.findAll();

        //then
        assertThat(all.size()).isEqualTo(2);
        assertThat(all).contains(itema, itemb);
    }

    @Test
    void update() {
        //given
        Item itema = new Item("itemA",20000,10);
        Item saved = itemRepository.save(itema);

        //when
        Item itemb = new Item("itemB",10000,15);
        itemRepository.update(saved.getId(),itemb);

        //then
        Item found = itemRepository.findById(itema.getId());
        assertThat(found.getItemName()).isEqualTo(itemb.getItemName());
    }
}