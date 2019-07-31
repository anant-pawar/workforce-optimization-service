package com.oneifm.challenge.wos.wo;

import com.oneifm.challenge.wos.wo.model.CleaningJob;
import com.oneifm.challenge.wos.wo.model.CleaningTeam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WOService {

    private CleaningTeam getCleaningTeam(Integer remainingRoomCapacity, Integer seniorCleanerCapacity, Integer juniorCleanerCapacity) {
        CleaningTeam cleaningTeam = new CleaningTeam();

        // allocate minimum one senior cleaner
        remainingRoomCapacity -= seniorCleanerCapacity;
        cleaningTeam.setSeniorCleaners(1);

        while (remainingRoomCapacity > 0) {

            if (remainingRoomCapacity <= juniorCleanerCapacity) {
                cleaningTeam.setJuniorCleaners(cleaningTeam.getJuniorCleaners() + 1);
                remainingRoomCapacity -= juniorCleanerCapacity;
                break;
            }

            if (remainingRoomCapacity <= seniorCleanerCapacity) {
                cleaningTeam.setSeniorCleaners(cleaningTeam.getSeniorCleaners() + 1);
                remainingRoomCapacity -= juniorCleanerCapacity;
                break;
            }

            Integer juniorCapacityMod = remainingRoomCapacity % juniorCleanerCapacity;
            Integer seniorCapacityDiv = remainingRoomCapacity / seniorCleanerCapacity;
            Integer juniorCapacityDiv = remainingRoomCapacity / juniorCleanerCapacity;

            if (juniorCapacityMod == 0 || juniorCapacityDiv == seniorCapacityDiv) {
                remainingRoomCapacity -= juniorCleanerCapacity * juniorCapacityDiv;
                cleaningTeam.setJuniorCleaners(cleaningTeam.getJuniorCleaners() + juniorCapacityDiv);
            } else {
                remainingRoomCapacity -= seniorCleanerCapacity * seniorCapacityDiv;
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
