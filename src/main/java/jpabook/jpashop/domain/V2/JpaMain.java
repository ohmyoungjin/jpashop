package jpabook.jpashop.domain.V2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        //트랜잭션 시작
        tx.begin();
        try {
            //주문자
            Member member = new Member();
            member.setName("maeng2");
            member.setCity("seoul");
            member.setStreet("samil");
            member.setZipcode("141-1");
            em.persist(member);

            //아이템
            Item item = new Item();
            item.setName("apple");
            item.setPrice(1000);
            item.setStockQuantity(500);
            em.persist(item);

            //주문서
            Order order = new Order();
            order.setMember(member);
            order.setOrderDate(LocalDateTime.now());
            order.setStatus(OrderStatus.ORDER);
            em.persist(order);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setItem(item);
            orderItem.setCount(2);
            orderItem.setOrderPrice(5000);
            em.persist(orderItem);


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            //code 작성 이후 manager 및 factory 닫아주면 됨
            // jdbc 쓰는 것과 비슷함 resultset, connection 닫는 느낌?
            em.close();
        }
        emf.close();
    }
}
