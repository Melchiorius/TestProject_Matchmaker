package com.boost.test_project.lasta_games.matchmaker.model;

import java.util.*;

public class Group {

        private List<User> users;
        private final int id;
        private final int maxGroupSize;
        private final double maxSkillSD;
        private final double maxLatencySD;


        private double sumOfSkill;
        private double sumOfLatency;

        public Group(int id,int maxGroupSize,double maxSkillSD,double maxLatencySD){
            this.id = id;
            this.maxGroupSize = maxGroupSize;
            this.maxSkillSD = maxSkillSD;
            this.maxLatencySD = maxLatencySD;
        }

        public double getAverageSkill(){
            return sumOfSkill / users.size();
        }

        public double getAverageLatency(){
            return sumOfLatency / users.size();
        }

        public int getCurrentSize(){
            return users.size();
        }

        public List<User> getUsers() {
            return users;
        }

        public double getSumOfSkill() {
            return sumOfSkill;
        }

        public double getSumOfLatency() {
            return sumOfLatency;
        }

        public boolean addUser(User newUser){
            if(users == null){
                users = new ArrayList<>();
                users.add(newUser);
                newUser.addGroup(this);

                sumOfSkill = newUser.getSkill();
                sumOfLatency = newUser.getLatency();
                return true;
            }

            GroupStat stat = new GroupStat(this,newUser);
            if(stat.getSkillSD()>maxSkillSD || stat.getLatencySD()>maxLatencySD) {
                return false;
            }

            if(!users.contains(newUser) && users.size() < maxGroupSize) {
                users.add(newUser);
                newUser.addGroup(this);

                sumOfSkill = stat.getSumOfSkills();
                sumOfLatency = stat.getSumOfLatency();
                return true;
            }
            return false;
        }



        public boolean groupIsFilled(){
            return users.size() >= maxGroupSize;
        }

        public int getId() {
            return id;
        }

        private double getMinSkill(){
            return  users.stream().reduce(-1.0,(x,y) -> {
                if(x > y.getSkill() || x<0){
                    return y.getSkill();
                }
                return x;
            }, (x,y)->x);
        }

        private double getMaxSkill(){
            return  users.stream().reduce(-1.0,(x,y) -> {
                if(x < y.getSkill()){
                    return y.getSkill();
                }
                return x;
            }, (x,y)->x);
        }

        private double getMinLatency(){
            return  users.stream().reduce(-1.0,(x,y) -> {
                if(x > y.getLatency() || x<0){
                    return y.getLatency();
                }
                return x;
            }, (x,y)->x);
        }

        private double getMaxLatency(){
            return  users.stream().reduce(-1.0,(x,y) -> {
                if(x < y.getLatency()){
                    return y.getLatency();
                }
                return x;
            }, (x,y)->x);
        }

        private long getMaxSpend(long currentTime){
            long time = users.stream().reduce(-1L,(x,y) -> {
                if(x > y.getTime() || x<0){
                    return y.getTime();
                }
                return x;
            }, (x,y)->x);
            time = currentTime - time;
            time /= 1000;
            return time;
        }

        private long getMinSpend(long currentTime){
            long time = users.stream().reduce(-1L,(x,y) -> {
                if(x < y.getTime()){
                    return y.getTime();
                }
                return x;
            }, (x,y)->x);
            time = currentTime - time;
            time /= 1000;
            return time;
        }

        private double getAverageSpend(long currentTime){
            double time = users.stream().reduce(0.0,(x,y) -> {
                x += currentTime - y.getTime();
                return x;
            }, (x,y)->x);
            time /= 1000;
            return time;
        }

        public boolean removeUser(User user){
            if(users != null){
                if(users.contains(user)) {
                    users.remove(user);
                    if(users.size()>0){
                        sumOfSkill -= user.getSkill();
                        sumOfLatency -= user.getLatency();
                        return false;
                    }
                    return true;
                }
            }
            return false;
        }

        public Set<Group> finish(){
            System.out.println(id);
            System.out.println(getMinSkill()+"/"+getMaxSkill()+"/"+getAverageSkill()+" skill in group");
            System.out.println(getMinLatency()+"/"+getMaxLatency()+"/"+getAverageLatency()+" latency in group");
            long time = System.currentTimeMillis();
            System.out.println(getMinSpend(time)+"/"+getMaxSpend(time)+"/"+getAverageSpend(time)+" time spent in queue");

            Set<Group> emptyGroups = new HashSet<>();
            for(User user : users) {
                Set<Group> emptyGroupsSet = user.removeFromAllOtherGroups(this);
                emptyGroups.addAll(emptyGroupsSet);
                System.out.println(user.getName());
            }
            return emptyGroups;
        }
}
