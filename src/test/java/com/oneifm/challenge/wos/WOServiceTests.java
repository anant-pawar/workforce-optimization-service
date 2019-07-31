package com.oneifm.challenge.wos;

import com.oneifm.challenge.wos.wo.WOService;
import com.oneifm.challenge.wos.wo.model.CleaningJob;
import com.oneifm.challenge.wos.wo.model.CleaningTeam;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class WOServiceTests {
    private WOService woService;
    private CleaningJob cleaningJob;
    private List<CleaningTeam> expectedCleaningTeams;

    @Before
    public void initialize() {
        woService = new WOService();
    }

    public WOServiceTests(CleaningJob cleaningJob, List<CleaningTeam> expectedCleaningTeams) {
        this.cleaningJob = cleaningJob;
        this.expectedCleaningTeams = expectedCleaningTeams;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {
                        new CleaningJob(Arrays.asList(35, 21, 17, 28), 10, 6),
                        Arrays.asList(
                                new CleaningTeam(3, 1),
                                new CleaningTeam(1, 2),
                                new CleaningTeam(2, 0),
                                new CleaningTeam(1, 3)
                        )
                },
                {
                        new CleaningJob(Arrays.asList(24, 28), 11, 6),
                        Arrays.asList(
                                new CleaningTeam(2, 1),
                                new CleaningTeam(2, 1)
                        )
                }
        });
    }

    @Test
    public void testGetCleaningTeamService() {
        Assert.assertEquals(this.expectedCleaningTeams, woService.getCleaningTeams(cleaningJob));
    }


}
