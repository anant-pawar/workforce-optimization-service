package com.oneifm.challenge.wos.wo;

import com.oneifm.challenge.wos.wo.model.CleaningJob;
import com.oneifm.challenge.wos.wo.model.CleaningTeam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WOService {

    private CleaningTeam getCleaningTeam(Integer roomCapacity, Integer seniorCleanerCapacity, Integer juniorCleanerCapacity) {
        CleaningTeam cleaningTeam = new CleaningTeam();

        roomCapacity -= seniorCleanerCapacity;
        cleaningTeam.setSeniorCleaners(1);

        while (roomCapacity > 0) {

            if (roomCapacity <= juniorCleanerCapacity) {
                cleaningTeam.setJuniorCleaners(cleaningTeam.getJuniorCleaners() + 1);
                roomCapacity -= juniorCleanerCapacity;
                break;
            }

            if (roomCapacity <= seniorCleanerCapacity) {
                cleaningTeam.setSeniorCleaners(cleaningTeam.getSeniorCleaners() + 1);
                roomCapacity -= juniorCleanerCapacity;
                break;
            }

            Integer juniorCapacityMod = roomCapacity % juniorCleanerCapacity;
            Integer seniorCapacityDiv = roomCapacity / seniorCleanerCapacity;
            Integer juniorCapacityDiv = roomCapacity / juniorCleanerCapacity;

            if (juniorCapacityMod == 0 || juniorCapacityDiv == seniorCapacityDiv) {
                roomCapacity -= juniorCleanerCapacity * juniorCapacityDiv;
                cleaningTeam.setJuniorCleaners(cleaningTeam.getJuniorCleaners() + juniorCapacityDiv);
            } else {
                roomCapacity -= seniorCleanerCapacity * seniorCapacityDiv;
                cleaningTeam.setSeniorCleaners(cleaningTeam.getSeniorCleaners() + seniorCapacityDiv);
            }

        }

        return cleaningTeam;
    }

    public List<CleaningTeam> getCleaningTeams(CleaningJob cleaningJob) {
        List<CleaningTeam> cleaningTeams = new ArrayList<>();

        for (Integer roomCapacity : cleaningJob.getRoomCapacities())
            cleaningTeams.add(getCleaningTeam(roomCapacity, cleaningJob.getSeniorCleanerCapacity(), cleaningJob.getJuniorCleanerCapacity()));

        return cleaningTeams;
    }

}
