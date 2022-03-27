package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository=new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){

        Item item=new Item("itemA",10000,10);

        Item savedItem=itemRepository.save(item);

        Item findItem = itemRepository.findById(item.getId());

        Assertions.assertThat(findItem).isEqualTo(savedItem);


    }

    @Test
    void findAll(){
        Item item1=new Item("item1",10000,10);
        Item item2=new Item("item2",20000,20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> byAll = itemRepository.findByAll();

        Assertions.assertThat(byAll.size()).isEqualTo(2);
        Assertions.assertThat(byAll).contains(item1,item2);

    }

    @Test
    void updateItem(){
        Item item=new Item("item1",10000,10);

        Item savedItem=itemRepository.save(item);
        Long itemId = savedItem.getId();

        Item updateParam = new Item("item2", 20000, 30);
        itemRepository.update(itemId,updateParam);

        Item byId = itemRepository.findById(itemId);

        Assertions.assertThat(byId.getItemName()).isEqualTo(updateParam.getItemName());
        Assertions.assertThat(byId.getPrice()).isEqualTo(updateParam.getPrice());
        Assertions.assertThat(byId.getQuantity()).isEqualTo(updateParam.getQuantity());


    }
}