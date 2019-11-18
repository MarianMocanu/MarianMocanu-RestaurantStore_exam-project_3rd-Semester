package dk.kea.stud.fourplayers.restaurantstore.repository;

import dk.kea.stud.fourplayers.restaurantstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}