package dk.kea.stud.fourplayers.restaurantstore.repository;

import dk.kea.stud.fourplayers.restaurantstore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);

}