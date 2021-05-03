package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> all = itemRepository.findAll();
        model.addAttribute("items",all);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String items(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

//    @PostMapping("/add")
//    public String saveV1(@RequestParam String itemName,
//                       @RequestParam Integer price,
//                       @RequestParam Integer quantity,
//                       Model model
//                       ){
//        Item item = new Item(itemName, price, quantity);
//        itemRepository.save(item);
//        model.addAttribute("item",item);
//        return "basic/item";
//    }
//    @PostMapping("/add")
//    public String saveV2(@ModelAttribute("item") Item item){
//        itemRepository.save(item);
//        //model.addAttribute("item",item); 자동 추가. 생략 가능
//        return "basic/item";
//    }
//    @PostMapping("/add")
//    public String saveV3(Item item){
//        itemRepository.save(item);
//        return "basic/item";
//    }
    @PostMapping("/add")
    public String saveV4(Item item){
        itemRepository.save(item);
        return "redirect:/basic/items/"+item.getId();
    }

    //상품수정
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item found = itemRepository.findById(itemId);
        model.addAttribute("item", found);
        return "basic/editForm";
    }
    @PostMapping("/{itemId}/edit")
    public String editSaveForm(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId,item);
        return "redirect:/basic/items/"+item.getId();
    }

    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA",1000,10));
        itemRepository.save(new Item("itemB",3000,30));
    }
}
