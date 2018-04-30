package gsshop.greenhouse.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestUserRepository extends JpaRepository<TestUser, Long>, TestUserRepositoryCustom {

}
