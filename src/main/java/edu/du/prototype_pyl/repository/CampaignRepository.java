package edu.du.prototype_pyl.repository;

import edu.du.prototype_pyl.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    //서비스 상세정보 불러오기
    public Optional<Campaign> findById(Long Id);

    //기간 만료/제공 취소로 인한 서비스 삭제
    public void deleteById(Long Id);
}
