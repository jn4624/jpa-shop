package jpashop.web;

import jpashop.domain.Item;
import jpashop.domain.item.Book;
import jpashop.service.ItemService;
import jpashop.web.form.BookForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book item = (Book) itemService.findItem(itemId);

        BookForm bookForm = new BookForm();
        bookForm.setId(item.getId());
        bookForm.setName(item.getName());
        bookForm.setPrice(item.getPrice());
        bookForm.setStockQuantity(item.getStockQuantity());
        bookForm.setAuthor(item.getAuthor());
        bookForm.setIsbn(item.getIsbn());

        model.addAttribute("itemForm", bookForm);

        return "/items/updateItemForm";
    }

    @PostMapping("/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") Long itemId, @ModelAttribute("itemForm") BookForm itemForm) {
        Book book = new Book();
        book.setId(itemForm.getId());
        book.setName(itemForm.getName());
        book.setPrice(itemForm.getPrice());
        book.setStockQuantity(itemForm.getStockQuantity());
        book.setAuthor(itemForm.getAuthor());
        book.setIsbn(itemForm.getIsbn());

        itemService.saveItem(book);

        return "redirect:/items";
    }
}
