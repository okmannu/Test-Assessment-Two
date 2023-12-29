import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class LeadController {

    private final LeadService leadService;
    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    public ResponseEntity<Object> getLeadsByMobileNumber(@RequestParam String mobileNumber) {
        List<Lead> leads = leadService.getLeadsByMobileNumber(mobileNumber);

        if (leads.isEmpty()) {
            return new ResponseEntity<>("No leads found with the provided mobile number",
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(leads, HttpStatus.OK);
    }
}
