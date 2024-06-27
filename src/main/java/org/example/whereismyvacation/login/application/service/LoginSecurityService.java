package org.example.whereismyvacation.login.application.service;


import org.example.whereismyvacation.login.repository.LoginRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginSecurityService implements UserDetailsService {
	private final LoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
		return null;
	}

}
