package com.tekup.school.springSecurity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.tekup.school.dto.UserRegistrationDto;
import com.tekup.school.entities.Teacher;
import com.tekup.school.repository.TeacherRepository;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	private TeacherRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImp(TeacherRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public Teacher save(UserRegistrationDto registrationDto) {
		Teacher user = new Teacher();
		user.setFirstName(registrationDto.getFirstName());
		user.setLastName(registrationDto.getLastName());
		user.setRole("TEACHER");
		user.setEmail(registrationDto.getEmail());
		user.setPwd(passwordEncoder.encode(registrationDto.getPassword()));
		
		return userRepository.save(user);
	}
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Teacher user = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Email " + username + " not found"));
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPwd(), getAuthorities(user));		
	}
	
public static Collection<? extends GrantedAuthority> getAuthorities(Teacher user){
		
		String[] userRoles = { user.getRole()};
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		return authorities;
	}
	
}