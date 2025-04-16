package edu.du.prototype_pyl.controller;

import edu.du.prototype_pyl.entity.Application;
import edu.du.prototype_pyl.entity.Campaign;
import edu.du.prototype_pyl.service.ApplicationService;
import edu.du.prototype_pyl.service.CampaignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider")
public class ProviderRestController {

    private final ApplicationService applicationService;

    private final CampaignService campaignService;

    public ProviderRestController(ApplicationService applicationService, CampaignService campaignService) {
        this.applicationService = applicationService;
        this.campaignService = campaignService;
    }

    //서비스 상세 페이지
    @GetMapping("/campaign/{id}")
    public ResponseEntity<Campaign> findById(@PathVariable Long id) {
        Campaign campaign = campaignService.findById(id);
        return new ResponseEntity<>(campaign, HttpStatus.OK);
    }

    //서비스 제공자가 신청 목록 확인
    @GetMapping("/application/{id}")
    public ResponseEntity<List<Application>> getApplications(@PathVariable long id) {
        List<Application> application = applicationService.findByCampaignId(id);
        return ResponseEntity.ok(application);
    }

    //체험단이 체험할 서비스 업로드
    @PostMapping("/upload")
    public ResponseEntity<String> uploadCampaign(Campaign campaign) {
        campaignService.createCampaign(campaign);
        return ResponseEntity.ok("업로드 되었습니다.");
    }

    //서비스 삭제 (기간 만료/제공 취소 등)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCampaign(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
        applicationService.deleteByCampaignId(id);
        return ResponseEntity.ok("삭제되었습니다.");
    }


}
