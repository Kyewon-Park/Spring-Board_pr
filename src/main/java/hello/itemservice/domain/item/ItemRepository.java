package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>(); //concurrentHashMap
    private static long sequence = 1L; //atomic long?

    //저장
    public Item save(Item item){
        item.setId(sequence++);
        store.put(item.getId(), item);
        return item;
    }

    //조회
    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    //
    public void update(Long itemId, Item updateParam){
        Item foundItem = findById(itemId);
        foundItem.setPrice(updateParam.getPrice());
        foundItem.setItemName(updateParam.getItemName());
        foundItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore(){
        store.clear();
    }
}
