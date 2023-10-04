package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("Liza", "Black", "liza@mail.ru", new Car("Kia Elan", 521)));
        userService.add(new User("Kate", "Green", "katya2@mail.ru", new Car("Kia Pride", 105)));
        userService.add(new User("Masha", "Pink", "masha@mail.ru", new Car("Kia Kue", 340)));
        userService.add(new User("Polina", "White", "polya@mail.ru", new Car("Kia GT Concept", 759)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        System.out.println(userService.getUser("Kia Kue", 340));

        context.close();
    }
}
