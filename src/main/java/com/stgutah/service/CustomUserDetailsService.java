package com.stgutah.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.stgutah.dao.ModelDao;
import com.stgutah.model.User;

/**
 * A custom service for retrieving users from a custom datasource, such as a
 * database.
 * <p>
 * This custom service must implement Spring's {@link UserDetailsService}
 */
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService
{
	protected static Logger logger = Logger.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private ModelDao modelDao;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException
	{

		// Declare a null Spring User
		UserDetails userDetails = null;

		try
		{

			// Search database for a user that matches the specified username
			// You can provide a custom DAO to access your persistence layer
			// Or use JDBC to access your database
			// DbUser is our custom domain user. This is not the same as Spring's User
			User user = modelDao.getUserByUsername(username);

			// Populate the Spring User object with details from the dbUser
			// Here we just pass the username, password, and access level
			// getAuthorities() will translate the access level to the correct role
			// type

			Collection<GrantedAuthority> authorities = getAuthorities(user.getUserType().getDescription());
			
			userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(),
					user.getPasswordMd5().toLowerCase(), true, true, true, true, authorities);

		}
		catch (Exception e)
		{
			logger.error("Error in retrieving user");
			throw new UsernameNotFoundException("Error in retrieving user");
		}

		// Return user to Spring for processing.
		// Take note we're not the one evaluating whether this user is authenticated
		// or valid
		// We just retrieve a user that matches the specified username
		return userDetails;
	}

	/**
	 * Retrieves the correct ROLE type depending on the access level, where access
	 * level is an Integer. Basically, this interprets the access value whether
	 * it's for a regular user or admin.
	 * 
	 * @param access
	 *          an integer value representing the access of the user
	 * @return collection of granted authorities
	 */
	public Collection<GrantedAuthority> getAuthorities(String access)
	{
		// Create a list of grants for this user
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

		// All users are granted with ROLE_USER access
		// Therefore this user gets a ROLE_USER by default
		logger.debug("Grant ROLE_USER to this user");
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));

		// Check if this user has admin access
		// We interpret Integer(1) as an admin user
		if (access.equalsIgnoreCase("ADMINISTRATOR"))
		{
			// User has admin access
			logger.debug("Grant ROLE_ADMIN to this user");
			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		// Return list of granted authorities
		return authList;
	}
}
