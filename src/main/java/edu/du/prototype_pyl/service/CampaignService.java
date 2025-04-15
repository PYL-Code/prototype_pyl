package edu.du.prototype_pyl.service;

import edu.du.prototype_pyl.entity.Campaign;
import edu.du.prototype_pyl.repository.CampaignRepository;
import org.springframework.stereotype.Service;

@Service
public class CampaignService {
    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public Campaign createCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }
}
