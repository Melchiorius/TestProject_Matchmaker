package com.boost.test_project.lasta_games.matchmaker.service;

import com.boost.test_project.lasta_games.matchmaker.model.Group;
import com.boost.test_project.lasta_games.matchmaker.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    private List<Group> groups;
    private int last_id;
    @Value("${groups.groupSize}")
    private int maxGroupSize;

    @Value("${groups.maxSkillSD}")
    private double maxSkillSD;
    @Value("${groups.maxLatencySD}")
    private double maxLatencySD;

    @Override
    public void addUser(User user) {
        if(groups == null){
            groups = new ArrayList<>();
            createNewGroup(user);
            return;
        }
        List<Group> suitableGroups = groups.stream().filter(element -> { return element.addUser(user);}).collect(Collectors.toList());
        createNewGroup(user);
        List<Group> filledGroups = suitableGroups.stream().filter(element -> {return element.groupIsFilled();}).collect(Collectors.toList());
        if(filledGroups != null && filledGroups.size()>0){
            Group preparedGroup = filledGroups.get(0);
            groups.removeAll(preparedGroup.finish());
            groups.remove(preparedGroup);
        }
    }

    private Group createNewGroup(User user){
        Group group = new Group(last_id++,maxGroupSize,maxSkillSD,maxLatencySD);
        group.addUser(user);
        groups.add(group);
        return group;
    }

    public List<Group> getAllGroups(){
        return groups;
    }

    /*
    @Autowired
    private UserDAO userDAO;
    @Override
    @Transactional
    public List<User> getAllVehicles() {
        return userDAO.getAllUser();
    }
    @Override
    @Transactional
    public void addUser(User user) {
        userDAO.add(user);
    }
    @Override
    @Transactional
    public User getVehicle(int id) {
        return userDAO.getUser(id);
    }
    @Override
    @Transactional
    public void deleteVehicle(int id) {
        userDAO.deleteUser(id);
    }
    */
}
