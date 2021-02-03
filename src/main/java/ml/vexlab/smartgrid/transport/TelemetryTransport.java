package ml.vexlab.smartgrid.transport;

import java.util.List;
import ml.vexlab.smartgrid.service.TelemetryService;
import ml.vexlab.smartgrid.transport.dto.GenericDataDTO;
import ml.vexlab.smartgrid.transport.dto.TelemetryDTO;
import ml.vexlab.smartgrid.transport.dto.TelemetryRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/telemetry")
@CrossOrigin()
public class TelemetryTransport {

  @Autowired private TelemetryService telemetryService;

  @PostMapping("")
  public List<TelemetryDTO> getTelemetry(@RequestBody TelemetryRequestDTO telemetryRequestDTO) {
    return telemetryService.getTelemetry(telemetryRequestDTO);
  }

  @PutMapping
  public GenericDataDTO add(@RequestBody TelemetryDTO telemetryDTO) {
    return telemetryService.create(telemetryDTO);
  }

  @PutMapping("/data")
  public GenericDataDTO addList(@RequestBody List<TelemetryDTO> telemetryDTOs) {
    return telemetryService.create(telemetryDTOs);
  }

  @DeleteMapping("")
  public GenericDataDTO delete(@RequestBody TelemetryDTO telemetryDTO) {
    return telemetryService.delete(telemetryDTO);
  }
}
