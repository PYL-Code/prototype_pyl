package edu.du.prototype_pyl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.du.prototype_pyl.controller.ReviewerRestController;
import edu.du.prototype_pyl.entity.Application;
import edu.du.prototype_pyl.entity.Application.ApplicationStatus;
import edu.du.prototype_pyl.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewerRestController.class)
public class ReviewerRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ApplicationService applicationService;

    @Autowired
    private ObjectMapper objectMapper;

    private Application mockApp;

    @BeforeEach
    public void setup() {
        mockApp = Application.builder()
                .id(1L)
                .campaignId(100L)
                .reviewerId(1L)
                .reviewerNickname("tester")
                .phone("010-1234-5678")
                .snsUrl("https://insta.com/tester")
                .text("열심히 할게요!")
                .status(ApplicationStatus.신청대기)
                .build();
    }

    @Test
    public void testGetMyApplications() throws Exception {
        List<Application> mockList = Arrays.asList(mockApp);
        Mockito.when(applicationService.findByReviewerId(1L)).thenReturn(mockList);

        mockMvc.perform(get("/reviewer/myapplication/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].reviewerNickname").value("tester"));
    }

    @Test
    public void testDeleteApplication() throws Exception {
        mockMvc.perform(delete("/reviewer/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("삭제 완료되었습니다."));

        Mockito.verify(applicationService).deleteById(1L);
    }

    @Test
    public void testUploadApplication() throws Exception {
        Mockito.doNothing().when(applicationService).save(any(Application.class));

        mockMvc.perform(post("/reviewer/upload/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockApp)))
                .andExpect(status().isOk())
                .andExpect(content().string("신청 완료되었습니다."));
    }
}
