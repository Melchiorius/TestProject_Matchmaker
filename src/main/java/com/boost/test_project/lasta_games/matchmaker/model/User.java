package com.boost.test_project.lasta_games.matchmaker.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class User {
    private String name;
    private double skill;
    private double latency;
    private final long time;

    List<Group> groups;

    public User() {
        time = System.currentTimeMillis();
    }

    public User(String name, double skill, double latency) {
        this.name = name;
        this.skill = skill;
        this.latency = latency;
        time = System.currentTimeMillis();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSkill(double skill) {
        this.skill = skill;
    }

    public void setLatency(double latency) {
        this.latency = latency;
    }

    public String getName() {
        return name;
    }

    public double getSkill() {
        return skill;
    }

    public double getLatency() {
        return latency;
    }

    public long getTime() {
        return time;
    }

    public void addGroup(Group group){
        if(groups == null){
            groups = new ArrayList<>();
        }
        if(!groups.contains(group))
            groups.add(group);
    }

    public Set<Group> removeFromAllOtherGroups(Group group){
        Set<Group> emptyGroups = groups.stream().filter(element -> {
            if(element!=group)
                return element.removeUser(this);
            return false;
        }).collect(Collectors.toSet());
        groups.clear();
        groups.add(group);
        return emptyGroups;
    }
}
