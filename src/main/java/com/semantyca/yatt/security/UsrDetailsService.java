package com.semantyca.yatt.security;

import com.semantyca.yatt.configuration.ApplicationContextKeeper;
import com.semantyca.yatt.dao.IUserDAO;
import com.semantyca.yatt.model.IUser;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsrDetailsService implements UserDetailsService {
    private static List<IUser> users = new ArrayList();

    public UsrDetailsService() {
        try {
            IUserDAO userDAO = ApplicationContextKeeper.getContext().getBean(IUserDAO.class);
            for (IUser user : userDAO.findAllUnrestricted(999, 0)) {
                users.add(user);
            }
        } catch (UnableToExecuteStatementException e){
            System.out.println(e);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<IUser> user = users.stream()
                .filter(u -> u.getLogin().equals(username))
                .findAny();
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return toUserDetails(user.get());
    }

    private UserDetails toUserDetails(IUser userObject) {
        return User.withUsername(userObject.getLogin())
                .password(userObject.getPwd())
                .roles(userObject.getRoles().stream().toArray(String[]::new))
                .build();
    }



}
