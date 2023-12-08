package com.example.users.repo;

import com.example.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    void deleteByEmail(String email);

    @Query("SELECT u FROM User u WHERE " +
            "(:email IS NULL OR :email='' OR LOWER(u.email) LIKE LOWER(CONCAT('%', :email,'%'))) AND" +
            " (:username IS NULL OR :username='' OR LOWER(u.username) LIKE LOWER(CONCAT('%', :username,'%')))"
    )
    Page<User> findByParams(@Param("email") String email,
                            @Param("username") String username,
                            Pageable pageable);
}
