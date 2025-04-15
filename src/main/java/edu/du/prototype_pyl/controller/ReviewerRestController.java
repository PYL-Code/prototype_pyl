package edu.du.prototype_pyl.controller;

import edu.du.prototype_pyl.entity.Application;
import edu.du.prototype_pyl.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviewer")
public class ReviewerRestController {

    private final ApplicationService applicationService;

    public ReviewerRestController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    //체험단 서비스 체험자가 신청한 서비스 목록 확인
    @GetMapping("/myapplication")
    public ResponseEntity<List<Application>> getMyApplication() {
        Long id = 1l;
        List<Application> application = applicationService.findByReviewerId(id);
        return ResponseEntity.ok(application);
    }

    //체험단 서비스 체험자가 서비스 신청
    @PostMapping("/upload/{id}")
    public ResponseEntity<String> uploadApplication(@PathVariable long id, Application application) {
        application.setId(id);
        applicationService.save(application);
        return ResponseEntity.ok("신청 완료 되었습니다.");
    }
}
