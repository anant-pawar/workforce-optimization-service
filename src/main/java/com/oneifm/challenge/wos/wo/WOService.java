package com.oneifm.challenge.wos.wo;

import com.oneifm.challenge.wos.wo.exceptions.InvalidInputException;
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
                cleaningTeam.addJuniorCleaner( 1);
                remainingRoomCapacity -= juniorCleanerCapacity;
                break;
            }

            if (remainingRoomCapacity <= seniorCleanerCapacity) {
                cleaningTeam.addSeniorCleaner( 1);
                remainingRoomCapacity -= juniorCleanerCapacity;
                break;
            }

            Integer juniorCapacityMod = remainingRoomCapacity % juniorCleanerCapacity;
            Integer seniorCapacityDiv = remainingRoomCapacity / seniorCleanerCapacity;
            Integer juniorCapacityDiv = remainingRoomCapacity / juniorCleanerCapacity;

            if (juniorCapacityMod == 0 || juniorCapacityDiv.equals(seniorCapacityDiv)) {
                remainingRoomCapacity -= juniorCleanerCapacity * juniorCapacityDiv;
                cleaningTeam.addJuniorCleaner(juniorCapacityDiv);
            } else {
                remainingRoomCapacity -= seniorCleanerCapacity * seniorCapacityDiv;
                cleaningTeam.addSeniorCleaner(seniorCapacityDiv);
            }

        }

        return cleaningTeam;
    }

    public List<CleaningTeam> getCleaningTeams(CleaningJob cleaningJob) throws InvalidInputException {
        if(cleaningJob.getRoomCapacities().size() > 100)
            throw new InvalidInputException(InvalidInputException.INVALID_ROOM_NUMBER);

        List<CleaningTeam> cleaningTeams = new ArrayList<>();

        for (Integer roomCapacity : cleaningJob.getRoomCapacities())
            cleaningTeams.add(getCleaningTeam(roomCapacity, cleaningJob.getSeniorCleanerCapacity(), cleaningJob.getJuniorCleanerCapacity()));

        return cleaningTeams;
    }

}
