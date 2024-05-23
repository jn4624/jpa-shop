package jpashop.web;

import jpashop.domain.Item;
import jpashop.domain.item.Book;
import jpashop.service.ItemService;
import jpashop.web.form.BookForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("itemForm", new BookForm());
        return "/items/createItemForm";
    }

    @PostMapping("/new")
    public String create(BookForm itemForm) {
        Book book = new Book();
        book.setName(itemForm.getName());
        book.setPrice(itemForm.getPrice());
        book.setStockQuantity(itemForm.getStockQuantity());
        book.setAuthor(itemForm.getAuthor());
        book.setIsbn(itemForm.getIsbn());

        itemService.saveItem(book);

        return "redirect:/";
    }

    @GetMapping
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "/items/itemList";
    }
}
