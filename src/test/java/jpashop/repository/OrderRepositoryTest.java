package jpashop.repository;

import jakarta.persistence.EntityManager;
import jpashop.domain.*;
import jpashop.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderRepositoryTest {
    @Autowired
    EntityManager entityManager;

    @Autowired
    OrderRepository orderRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void testOrder() {
        // given
        Member member = createMember();
        Book book = createBook();
        Delivery delivery = createDelivery(member);
        OrderItem orderItem = createOrderItem(book, 3);
        Order order = Order.createOrder(member, delivery, orderItem);

        // when
        orderRepository.save(order);

        // then
        Order findOrder = orderRepository.findOne(order.getId());

        assertThat(book.getStockQuantity()).isEqualTo(7);
        assertThat(findOrder).isEqualTo(order);
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("memberA");
        member.setAddress(new Address("city", "street", "zipcode"));

        entityManager.persist(member);

        return member;
    }

    private Book createBook() {
        Book book = new Book();
        book.setName("bookA");
        book.setPrice(13500);
        book.setStockQuantity(10);

        entityManager.persist(book);

        return book;
    }

    private Delivery createDelivery(Member member) {
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        return delivery;
    }

    private OrderItem createOrderItem(Item item, int orderCount) {
        return OrderItem.createOrderItem(item, item.getPrice(), orderCount);
    }
}