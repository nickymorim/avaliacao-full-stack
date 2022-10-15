package br.com.teste.api;

import br.com.teste.api.dto.SchedulingRequest;
import br.com.teste.api.dto.SchedulingResponse;
import br.com.teste.service.SchedulingService;
import br.com.teste.service.impl.SchedulingServiceImpl;
import jdk.jfr.ContentType;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/bank/scheduling")
public class SchedulingApi {

    private final SchedulingService service;

    public SchedulingApi(SchedulingServiceImpl service) {
        this.service = service;
    }

    @PostMapping(value = "/")
    public SchedulingResponse scheduleBankTransfer(@RequestBody SchedulingRequest schedulingRequest) {
        return service.saveScheduleTransfer(schedulingRequest);
    }

    @GetMapping(value = "/")
    public List<SchedulingResponse> getSchedulingBankTransfers() {
        return service.findAllSchedules();
    }
}
