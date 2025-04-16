package edu.du.prototype_pyl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.du.prototype_pyl.controller.ProviderRestController;
import edu.du.prototype_pyl.entity.Application;
import edu.du.prototype_pyl.entity.Application.ApplicationStatus;
import edu.du.prototype_pyl.entity.Campaign;
import edu.du.prototype_pyl.service.ApplicationService;
import edu.du.prototype_pyl.service.CampaignService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProviderRestController.class)
class ProviderRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ApplicationService applicationService;

    @MockitoBean
    private CampaignService campaignService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetApplicationsByCampaignId() throws Exception {
        Application application = Application.builder()
                .id(1L)
                .campaignId(10L)
                .reviewerId(1L)
                .reviewerNickname("testuser")
                .phone("010-0000-0000")
                .status(ApplicationStatus.신청대기)
                .text("신청합니다")
                .build();

        Mockito.when(applicationService.findByCampaignId(10L))
                .thenReturn(List.of(application));

        mockMvc.perform(get("/provider/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].reviewerNickname").value("testuser"));
    }

    @Test
    void testUploadCampaign() throws Exception {
        Campaign campaign = Campaign.builder()
                .id(1L)
                .title("새 캠페인")
                .description("설명입니다.")
                .category("뷰티")
                .productName("샘플제품")
                .applicationDeadline(LocalDate.now().plusDays(5))
                .reviewDeadline(LocalDate.now().plusDays(10))
                .quota(10)
                .recruitActive(true)
                .reviewActive(false)
                .build();

        mockMvc.perform(post("/provider/upload")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(campaign)))
                .andExpect(status().isOk())
                .andExpect(content().string("업로드 되었습니다."));
    }

    @Test
    void testDeleteCampaign() throws Exception {
        mockMvc.perform(delete("/provider/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("삭제되었습니다."));
    }
}
