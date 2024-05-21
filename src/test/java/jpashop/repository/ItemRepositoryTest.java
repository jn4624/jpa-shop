package jpashop.repository;

import jpashop.domain.Item;
import jpashop.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void testItem() {
        // given
        Book book = new Book();
        book.setName("bookA");

        // when
        itemRepository.save(book);
        Item findItem = itemRepository.findOne(book.getId());

        // then
        assertThat(findItem.getId()).isEqualTo(book.getId());
        assertThat(findItem.getName()).isEqualTo(book.getName());
        assertThat(findItem).isEqualTo(book);
    }
}