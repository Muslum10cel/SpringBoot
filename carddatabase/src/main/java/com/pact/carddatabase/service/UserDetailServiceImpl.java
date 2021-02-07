package com.pact.carddatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pact.carddatabase.domain.User;
import com.pact.carddatabase.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		UserDetails userDeteails = new org.springframework.security.core.userdetails.User(username, user.getPassword(),
				true, true, true, true, AuthorityUtils.createAuthorityList(user.getRole()));
		return userDeteails;
	}

}
