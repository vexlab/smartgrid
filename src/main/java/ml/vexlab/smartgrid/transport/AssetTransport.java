package ml.vexlab.smartgrid.transport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ml.vexlab.smartgrid.service.AssetService;
import ml.vexlab.smartgrid.transport.dto.AssetDTO;
import ml.vexlab.smartgrid.transport.dto.GenericDataDTO;

@RestController
@RequestMapping("/api/asset")
@CrossOrigin()

public class AssetTransport {

	@Autowired
	private AssetService assetService;

	@GetMapping("/all")
	public List<GenericDataDTO> getAll() {
		return assetService.getAll();
	}
	
	@GetMapping("/{assetId}")
	public AssetDTO get(@PathVariable String assetId) {
		return assetService.get(assetId);
	}
	
	@PostMapping
	public GenericDataDTO edit(@RequestBody AssetDTO assetDTO) {
		return assetService.create(assetDTO);
	}
	
	@PutMapping
	public GenericDataDTO create(@RequestBody AssetDTO assetDTO) {
		return assetService.create(assetDTO);
	}
	
	@DeleteMapping("/{assetId}")
	public GenericDataDTO delete(@PathVariable String assetId) {
		return assetService.delete(assetId);
	}	
}
