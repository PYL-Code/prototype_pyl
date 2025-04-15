package edu.du.prototype_pyl.service;

import edu.du.prototype_pyl.entity.Application;
import edu.du.prototype_pyl.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public List<Application> findByCampaignId(Long campaignId) {
        return applicationRepository.findByCampaignId(campaignId);
    }

    public List<Application> findByReviewerId(Long ReviewerId) {
        return applicationRepository.findByReviewerId(ReviewerId);
    }

    public void save(Application application) {
        applicationRepository.save(application);
    }

    public void deleteById(Long Id){
        applicationRepository.deleteById(Id);
    }

    public void deleteByCampaignId(Long campaignId){
        applicationRepository.deleteByCampaignId(campaignId);
    }
}
