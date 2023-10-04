package hiber.dao;


import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getListUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User ", User.class).getResultList();
    }

    @Override
    public User getUserByCar(String model, int series) {

        String hql = "select user from User user join fetch user.car where user.car.model =: model and user.car.series =: series";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("model", model);
        query.setParameter("series", series);
        return (User) query.getSingleResult();
    }

}
