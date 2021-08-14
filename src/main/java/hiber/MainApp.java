package hiber;

import hiber.config.AppConfig;
import hiber.dao.UserDaoImp;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User(new Car("bmw", 5), "User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User(new Car("renault", 2), "User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User(new Car("audi",100),"User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User(new Car("opel", 1), "User4", "Lastname4", "user4@mail.ru"));
        userService.add(new User(new Car("opel", 1), "User5", "Lastname5", "user5@mail.ru"));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car model = " + user.getCar().getModel());
            System.out.println("Car series = " + user.getCar().getSeries());
            System.out.println();
        }

        List<String> resultUsers = userService.userCar("opel",2);
        for(String name : resultUsers){
            System.out.println(name);
        }

        context.close();
    }
}
