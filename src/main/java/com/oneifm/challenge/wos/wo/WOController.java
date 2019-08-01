package com.oneifm.challenge.wos.wo;

import com.oneifm.challenge.wos.wo.model.CleaningJob;
import com.oneifm.challenge.wos.wo.model.CleaningTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("wo")
public class WOController {
    @Autowired
    WOService woService;

    @PostMapping("team")
    public ResponseEntity<List<CleaningTeam>> getCleaningTeams(@RequestBody CleaningJob cleaningJob) {
        return new ResponseEntity<>(woService.getCleaningTeams(cleaningJob), HttpStatus.OK);
    }

}
