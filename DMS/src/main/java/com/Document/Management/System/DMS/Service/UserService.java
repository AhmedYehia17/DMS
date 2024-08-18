package com.Document.Management.System.DMS.Service;

import com.Document.Management.System.DMS.DTO.UserRegisterationDto;
import com.Document.Management.System.DMS.Model.User;
import com.Document.Management.System.DMS.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User register(UserRegisterationDto userDTO){
//        user.setPassword(encoder.encode(user.getPassword()));
//       return userRepo.save(user);
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUserName(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setNationalId(userDTO.getNationalId());
        user.setPassword(encoder.encode(userDTO.getPassword()));

        return userRepo.save(user);

    }

    public String verify(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUserName());
        }else{
            return "Failed";
        }
    }
}
