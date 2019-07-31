package com.oneifm.challenge.wos.wo.model;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CleaningTeam {

    private Integer seniorCleaners;
    private Integer juniorCleaners;

    public CleaningTeam() {
        this.seniorCleaners = 0;
        this.juniorCleaners = 0;
    }

    public CleaningTeam(Integer seniorCleaners, Integer juniorCleaners) {
        this.seniorCleaners = seniorCleaners;
        this.juniorCleaners = juniorCleaners;
    }

    public Integer getSeniorCleaners() {
        return seniorCleaners;
    }

    public void setSeniorCleaners(Integer seniorCleaners) {
        this.seniorCleaners = seniorCleaners;
    }

    public Integer getJuniorCleaners() {
        return juniorCleaners;
    }

    public void setJuniorCleaners(Integer juniorCleaners) {
        this.juniorCleaners = juniorCleaners;
    }
}
