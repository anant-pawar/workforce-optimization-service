package com.oneifm.challenge.wos.wo.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CleaningTeam {
    private final static Logger LOGGER = Logger.getLogger(CleaningTeam.class.getName());

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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        CleaningTeam cleaningTeam = (CleaningTeam) obj;

        return cleaningTeam.seniorCleaners == this.seniorCleaners && cleaningTeam.juniorCleaners == this.juniorCleaners;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seniorCleaners, juniorCleaners);
    }

    @Override
    public String toString() {

        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException jsonProcessingException) {
            LOGGER.log(Level.WARNING, jsonProcessingException.getMessage());
        }

        return super.toString();
    }

    public CleaningTeam plus(CleaningTeam cleaningTeam){
        this.seniorCleaners += cleaningTeam.seniorCleaners;
        this.juniorCleaners += cleaningTeam.juniorCleaners;
        return this;
    }

    public Integer getSeniorCleaners() {
        return seniorCleaners;
    }

    public Integer getJuniorCleaners() {
        return juniorCleaners;
    }

}
