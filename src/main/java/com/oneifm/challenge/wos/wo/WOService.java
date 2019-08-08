package com.oneifm.challenge.wos.wo;

import com.oneifm.challenge.wos.wo.exceptions.InvalidInputException;
import com.oneifm.challenge.wos.wo.model.CleaningJob;
import com.oneifm.challenge.wos.wo.model.CleaningTeam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WOService {

    private CleaningTeam getCleaningTeam(Integer roomCapacity, Integer seniorCleanerCapacity, Integer juniorCleanerCapacity) {
        CleaningTeam[] cleaningTeams = new CleaningTeam[roomCapacity + 1];
        for(int i = 1; i <= roomCapacity ; i++){

            Integer capacityToFillWithOneSenior = i - seniorCleanerCapacity;
            Integer capacityToFillWithOneJunior = i - juniorCleanerCapacity;

            if (i < seniorCleanerCapacity)
                cleaningTeams[i] = new CleaningTeam(1, 0);
            else if (i < seniorCleanerCapacity + juniorCleanerCapacity)
                cleaningTeams[i] = new CleaningTeam(1, 1);
            else if (roomCapacity % seniorCleanerCapacity == 0)
                cleaningTeams[i] = new CleaningTeam(roomCapacity / seniorCleanerCapacity, 0);
            else if (capacityToFillWithOneSenior % juniorCleanerCapacity == 0)
                cleaningTeams[i] = new CleaningTeam(1, capacityToFillWithOneSenior / juniorCleanerCapacity);
            else if (capacityToFillWithOneSenior % juniorCleanerCapacity > capacityToFillWithOneSenior % seniorCleanerCapacity)
                cleaningTeams[i] = new CleaningTeam(0, 1).plus(cleaningTeams[capacityToFillWithOneJunior]);
            else
                cleaningTeams[i] = new CleaningTeam(1, 0).plus(cleaningTeams[capacityToFillWithOneSenior]);
        }

        return cleaningTeams[roomCapacity];
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
