package edu.du.prototype_pyl.repository;

import edu.du.prototype_pyl.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    //마이페이지 만들 경우
    public List<Application> findByReviewerId(Long reviewerId);

    //서비스 제공자가 신청 목록 확인할 경우
    public List<Application> findByCampaignId(Long campaignId);

    //서비스 이용자가 신청 취소/삭제할 경우
    public void deleteById(Long Id);

    //서비스 기간 만료/제공 취소 등 서비스가 삭제될 경우 신청 목록도 함께 삭제
    public void deleteByCampaignId(Long campaignId);
}
