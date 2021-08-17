package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
        sessionFactory.getCurrentSession().save(user.getCar());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").getResultList();
    }

    public User getUserByCarModelAndSeries(String model, Integer series) {
        List<User> result = sessionFactory.getCurrentSession()
                .createQuery("select us from User us where us.car.model = :model and us.car.series = :series",User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .getResultList();
        return result.isEmpty() ? new User() : result.get(0);
    }


}
