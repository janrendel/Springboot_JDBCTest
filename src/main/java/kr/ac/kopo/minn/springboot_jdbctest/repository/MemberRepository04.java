package kr.ac.kopo.minn.springboot_jdbctest.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import kr.ac.kopo.minn.springboot_jdbctest.domain.Member;
import org.hibernate.sql.Insert;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

//EntityManager를 사용할 때 JpaRepository를 상속 받지 않는다.
//@PersistenveContext를 사용하여 EntityManager의 참조값을 주입 받는다.

@Repository
public class MemberRepository04 {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Member> selectMethod() {
        String jpql = "select entity from Member entity";
        Query query  = entityManager.createQuery(jpql);
        List<Member> memberlist = query.getResultList();
        return memberlist;
    }

    @Transactional
    public void insertMethod(@ModelAttribute("member") Member member) {
        String jpql = "insert into Member (name,age, email) values (:e_name, :age, :e_email)";
        Query query  = entityManager.createQuery(jpql);
        query.setParameter("e_name", member.getName());
        query.setParameter("age", member.getAge());
        query.setParameter("e_email", member.getEmail());
        query.executeUpdate();

    }

    public Member selectMethodById(int id) {
        String jpql = "select entity from Member entity where id = :e_id";
        Query query  = entityManager.createQuery(jpql);
        query.setParameter("e_id", id);
        Member member = (Member) query.getSingleResult();
        return member;
    }

    @Transactional
    public void updateMethod(@ModelAttribute("member") Member member) {
        String jpql = "update Member set name=:e_name,age=:e_age, email=:e_email where id=:e_id";
        Query query  = entityManager.createQuery(jpql);
        query.setParameter("e_id", member.getId());
        query.setParameter("e_age", member.getAge());
        query.setParameter("e_email", member.getEmail());

    }

    @Transactional
    public void deleteMethodById(int id) {
        String jpql = "delete from Member where id=:e_id";

    }
}