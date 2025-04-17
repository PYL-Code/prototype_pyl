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

    //마이페이지------
    //체험단 서비스 체험자가 신청한 서비스 목록 확인
    @GetMapping("/myapplication/{id}")
    public ResponseEntity<List<Application>> getMyApplication(@PathVariable Long id) {
        List<Application> application = applicationService.findByReviewerId(id);
        return ResponseEntity.ok(application);
    }

    //체험단 서비스 체험자가 신청 내용 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable Long id) {
        applicationService.deleteById(id);
        return ResponseEntity.ok("삭제 완료되었습니다.");
    }

    //체험단 신청------
    //체험단 서비스 체험자가 서비스 신청
    @PostMapping("/upload")
    public ResponseEntity<String> uploadApplication(@RequestBody Application application) {
        applicationService.save(application);
        return ResponseEntity.ok("신청 완료되었습니다.");
    }
}
