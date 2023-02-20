package pangyo.makeat.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import pangyo.makeat.dto.Users;

import java.util.List;

@Repository
public class UsersRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Users users){
        em.persist(users);
    }

    public Users findOne(Long id) {
        return em.find(Users.class, id);
    }

    public List<Users> findAll() {
        return em.createQuery("select u from Users u", Users.class).getResultList();
    }

    // userToken 받았을 때 resultList 다 가져옴,, 뭐 가져오는지 모르겟음 ㅋㅋ
    public List<Users> findByName(String userToken) {
        return em.createQuery("select u from Users u where u.userToken = :userToken", Users.class)
                .setParameter("userToken", userToken).getResultList();
    }
}
