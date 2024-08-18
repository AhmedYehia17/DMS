package com.Document.Management.System.DMS.Repository;

import com.Document.Management.System.DMS.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

   User findByUserName(String Username);
}
