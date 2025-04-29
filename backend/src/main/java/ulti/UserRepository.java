package ulti;

import org.springframework.data.jpa.repository.JpaRepository;

import ulti.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
