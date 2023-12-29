import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

public class LeadControllerTest {
    private MockMvc mockMvc;
    private LeadService leadService;
    public void getLeadsByMobileNumber_FoundLeads_Success() throws Exception {
        String mobileNumber = "8877887788";
        Lead lead1 = new Lead();
        Lead lead2 = new Lead();
        List<Lead> leads = new ArrayList<>();
        leads.add(lead1);
        leads.add(lead2);

        Mockito.when(leadService.getLeadsByMobileNumber(mobileNumber)).thenReturn(leads);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/leads/byMobileNumber")
                .param("mobileNumber", mobileNumber)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("success"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.length()").value(2));
    }

    public void getLeadsByMobileNumber_NoLeadsFound_ErrorResponse() throws Exception {
        String mobileNumber = "1234567890";

        Mockito.when(leadService.getLeadsByMobileNumber(mobileNumber)).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/leads/byMobileNumber")
                .param("mobileNumber", mobileNumber)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("error"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorResponse.code").value("E10011"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorResponse.messages[0]").value("No Lead found with the Mobile Number"));
    }
}
