package edu.du.prototype_pyl.service;

import edu.du.prototype_pyl.entity.Campaign;
import edu.du.prototype_pyl.repository.CampaignRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {
    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public List<Campaign> findAll() {
        return campaignRepository.findAll();
    }

    public Campaign findById(Long Id) {
        return campaignRepository.findById(Id).orElse(null);
    }

    public Campaign createCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }
}
