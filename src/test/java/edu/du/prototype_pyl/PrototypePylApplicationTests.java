package edu.du.prototype_pyl;

import edu.du.prototype_pyl.entity.Reviewer;
import edu.du.prototype_pyl.service.ApplicationService;
import edu.du.prototype_pyl.service.CampaignService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PrototypePylApplicationTests {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private CampaignService campaignService;

    @Test
    void contextLoads() {
        System.out.println(applicationService.findByReviewerId(1l));
    }

}
