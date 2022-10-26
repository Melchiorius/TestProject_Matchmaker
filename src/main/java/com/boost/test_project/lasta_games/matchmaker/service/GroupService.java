package com.boost.test_project.lasta_games.matchmaker.service;


import com.boost.test_project.lasta_games.matchmaker.model.Group;
import com.boost.test_project.lasta_games.matchmaker.model.User;

import java.util.List;

public interface GroupService {
    //public List<User> getAllUsers();
    public void addUser(User vehicle);
    //public User getUser(int id);
    //public void deleteUser(int id);

    public List<Group> getAllGroups();

}
