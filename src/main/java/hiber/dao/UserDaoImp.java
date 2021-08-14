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
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<String> userCar(String model, Integer series) {
        String hql = "select u.firstName from User u where u.car.model = :model and u.car.series = :series";
        List<String> result = sessionFactory.getCurrentSession().createQuery(hql)
                .setParameter("model",model).setParameter("series",series).getResultList();
        return result;
    }


}
