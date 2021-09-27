package com.localpayment.application.services;

import com.localpayment.application.postActions.AddUser;
import com.localpayment.domain.entities.Person;
import com.localpayment.domain.entities.User;
import com.localpayment.domain.repositories.PersonRepository;
import com.localpayment.domain.repositories.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
public class UserService{
	@Autowired
    private UserRepository userRepository;
	@Autowired
	private PersonRepository personRepository;

    public UserService(UserRepository userRepository,PersonRepository personRepository) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
    }

	public User findById(String strId) {
		Integer id = Integer.parseInt(strId);
		try {
			return this.userRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}

	}
	
	public User add(AddUser data){
		Integer id = Integer.parseInt(data.getPersonId());
		try {
			Person person = personRepository.findById(id).get();
			User user = new User(null, data.getName(), person ,  data.getPassword());
			return userRepository.save(user);
		} catch (NoSuchElementException e) {
			return null;
		}


	}

	public String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts
				.builder()
				.setId("jwt")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

	public Boolean checkCredentials(String userName, String password) {
		User user = userRepository.findByName(userName);
		if (user != null && user.getPassword().equals(password)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}


}
