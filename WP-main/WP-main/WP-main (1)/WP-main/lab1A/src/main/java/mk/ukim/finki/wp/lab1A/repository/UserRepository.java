package mk.ukim.finki.wp.lab1A.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab1A.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private final List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
    }
    @PostConstruct
    public void init(){
        users.add(new User("elena.atanasoska", "ea", "Elena", "Atanasoska"));
        users.add(new User("darko.sasanski", "ds", "Darko", "Sasanski"));
        users.add(new User("ana.todorovska", "at", "Ana", "Todorovska"));
    }
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst();
    }
    public User saveOrUpdate(User user) {
        users.removeIf(existingUser -> existingUser.getUsername().equals(user.getUsername()));
        users.add(user);
        return user;
    }
    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
}
