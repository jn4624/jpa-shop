package jpashop.service;

import jpashop.domain.item.Book;
import jpashop.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class ItemServiceTest {
    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Test
    void 아이템_추가() {
        // given
        Book book = new Book();
        book.setName("bookA");

        // when
        itemService.saveItem(book);

        // then
        assertEquals(book, itemRepository.findOne(book.getId()));
    }
}