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
            Book book = new Book();
            book.setName("머큐리");
            book.setPrice(12000);
            book.setAuthor("몰루");
            book.setCreatedTime(LocalDateTime.now());
            book.setModifiedTime(LocalDateTime.now());
            em.persist(book);


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
