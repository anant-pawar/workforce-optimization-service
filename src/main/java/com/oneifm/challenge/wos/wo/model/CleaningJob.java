package com.oneifm.challenge.wos.wo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CleaningJob {
    @JsonProperty("rooms")
    private List<Integer> roomCapacities;
    @JsonProperty("senior")
    private Integer seniorCleanerCapacity;
    @JsonProperty("junior")
    private Integer juniorCleanerCapacity;

    public CleaningJob(){}

    public CleaningJob(List<Integer> roomCapacities, Integer seniorCleanerCapacity,Integer juniorCleanerCapacity){
        this.roomCapacities = roomCapacities;
        this.seniorCleanerCapacity = seniorCleanerCapacity;
        this.juniorCleanerCapacity = juniorCleanerCapacity;
    }

    public List<Integer> getRoomCapacities() {
        return roomCapacities;
    }

    public Integer getSeniorCleanerCapacity() {
        return seniorCleanerCapacity;
    }

    public Integer getJuniorCleanerCapacity() {
        return juniorCleanerCapacity;
    }

}
