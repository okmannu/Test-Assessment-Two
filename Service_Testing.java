import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class LeadServiceTest {
    private LeadRepository leadRepository;
    private LeadService leadService;
    public void getLeadsByMobileNumber_FoundLeads_Success() {
        String mobileNumber = "8877887788";
        Lead lead1 = new Lead();
        Lead lead2 = new Lead();
        List<Lead> leads = new ArrayList<>();
        leads.add(lead1);
        leads.add(lead2);

        Mockito.when(leadRepository.findByMobileNumber(mobileNumber)).thenReturn(leads);

        List<Lead> retrievedLeads = leadService.getLeadsByMobileNumber(mobileNumber);

        assertEquals(2, retrievedLeads.size());
    }

    public void getLeadsByMobileNumber_NoLeadsFound_EmptyList() {
        String mobileNumber = "1234567890";

        Mockito.when(leadRepository.findByMobileNumber(mobileNumber)).thenReturn(new ArrayList<>());

        List<Lead> retrievedLeads = leadService.getLeadsByMobileNumber(mobileNumber);

        assertEquals(0, retrievedLeads.size());
    }
}
