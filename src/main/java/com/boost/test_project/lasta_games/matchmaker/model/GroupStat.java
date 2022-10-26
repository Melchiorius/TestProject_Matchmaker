package com.boost.test_project.lasta_games.matchmaker.model;

public class GroupStat {
    private int groupSize;
    private double sumOfSkills;
    private double sumOfLatency;

    private double skillSD;
    private double latencySD;


    public GroupStat(Group group, User newUser){
        groupSize = group.getCurrentSize()+(newUser==null?0:1);
        calcSkillStat(group,newUser);
        calcLatencyStat(group,newUser);
    }

    private void calcSkillStat(Group group, User newUser){
        sumOfSkills = group.getSumOfSkill();
        if(newUser != null) sumOfSkills += newUser.getSkill();

        double averageSkill = sumOfSkills / groupSize;

        double sumOfSquaresOfTheDifference_UserSkill_AverageSkill = group.getUsers().stream().reduce(0.0,(x,element)-> {
            double difference_UserSkill_AverageSkill = element.getSkill() - averageSkill;
            return x + difference_UserSkill_AverageSkill*difference_UserSkill_AverageSkill;
        }, (x, element)->x+element);

        if(newUser != null) {
            double difference_newUserSkill_AverageSkill = newUser.getSkill() - averageSkill;
            sumOfSquaresOfTheDifference_UserSkill_AverageSkill += difference_newUserSkill_AverageSkill * difference_newUserSkill_AverageSkill;
        }

        double averageOfSquaresOfTheDifference_UserSkill_AverageSkill = sumOfSquaresOfTheDifference_UserSkill_AverageSkill / groupSize;
        skillSD = Math.sqrt(averageOfSquaresOfTheDifference_UserSkill_AverageSkill);
    }

    private void calcLatencyStat(Group group, User newUser){
        sumOfLatency = group.getSumOfLatency();
        if(newUser != null) sumOfLatency += newUser.getLatency();

        double averageLatency = sumOfLatency / groupSize;

        double sumOfSquaresOfTheDifference_UserLatency_AverageLatency = group.getUsers().stream().reduce(0.0,(x,element)-> {
            double difference_UserLatency_AverageLatency = element.getLatency() - averageLatency;
            return x + difference_UserLatency_AverageLatency*difference_UserLatency_AverageLatency;
        }, (x, element)->x+element);

        if(newUser != null) {
            double difference_newUserLatency_AverageLatency = newUser.getLatency() - averageLatency;
            sumOfSquaresOfTheDifference_UserLatency_AverageLatency += difference_newUserLatency_AverageLatency * difference_newUserLatency_AverageLatency;
        }

        double averageOfSquaresOfTheDifference_UserLatency_AverageLatency = sumOfSquaresOfTheDifference_UserLatency_AverageLatency / groupSize;
        latencySD = Math.sqrt(averageOfSquaresOfTheDifference_UserLatency_AverageLatency);
    }

    public double getSumOfSkills() {
        return sumOfSkills;
    }

    public double getSumOfLatency() {
        return sumOfLatency;
    }

    public double getSkillSD() {
        return skillSD;
    }

    public double getLatencySD() {
        return latencySD;
    }
}
