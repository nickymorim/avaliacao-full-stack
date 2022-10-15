package br.com.teste.service;

import br.com.teste.api.dto.SchedulingRequest;
import br.com.teste.api.dto.SchedulingResponse;

import java.util.List;

public interface SchedulingService {

    SchedulingResponse saveScheduleTransfer(SchedulingRequest schedulingRequest);

    List<SchedulingResponse> findAllSchedules();
}
