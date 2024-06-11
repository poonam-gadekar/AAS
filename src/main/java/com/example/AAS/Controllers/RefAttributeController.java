package com.example.AAS.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AAS.dto.AtrributeDto;
import com.example.AAS.dto.AttributeCurrentResultDto;
import com.example.AAS.service.RefAttributeService;

@RestController
@RequestMapping("/refAttribute")
public class RefAttributeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RefAttributeController.class);

	@Autowired
	private RefAttributeService refAttributeService;

	@PostMapping("/createAttribute")
	public ResponseEntity<AtrributeDto> createAttribute(@RequestBody AtrributeDto refAtrributeDto) {
		AtrributeDto refAtrributeDto1 = refAttributeService.createAttributr(refAtrributeDto);
		LOGGER.info("attribute record created successfully");
		return new ResponseEntity<>(refAtrributeDto1, HttpStatus.CREATED);

	}

	@GetMapping("/{attribute_id}")
	public ResponseEntity<AtrributeDto> getAttribute(@PathVariable Long attribute_id) {
		AtrributeDto refAtrributeDto1 = refAttributeService.getAttribute(attribute_id);
		return new ResponseEntity<>(refAtrributeDto1, HttpStatus.OK);
	}

	@GetMapping("/rule/{attributeId}")
	public ResponseEntity<AtrributeDto> getRuleSetAndCategoryValues(@PathVariable Long attributeId) {

		return new ResponseEntity<>(refAttributeService.getRuleSetAndCategoryValues(attributeId), HttpStatus.OK);

	}

	@GetMapping("/getAllAttributes")
	public ResponseEntity<List<AtrributeDto>> getAllAttributes() {
		List<AtrributeDto> AtrributeDtos = refAttributeService.getAllAttributes();
		return new ResponseEntity<>(AtrributeDtos, HttpStatus.OK);

	}

	@GetMapping("/getAttributesByCodeOrDescription/{searchParameter}")
	public ResponseEntity<List<AtrributeDto>> getAttributesByCodeOrDescription(@PathVariable String searchParameter) {
		List<AtrributeDto> AtrributeDtos = refAttributeService.getAttributesByCodeOrDescription(searchParameter);
		return new ResponseEntity<>(AtrributeDtos, HttpStatus.OK);
	}

	@GetMapping("/{mappingId}/{dataFieldValue}")
	public ResponseEntity<AttributeCurrentResultDto> checkAttributeCurrentResult(@PathVariable int mappingId,
			@PathVariable String dataFieldValue) {

		return new ResponseEntity<>(refAttributeService.checkAttributeCurrentResult(mappingId, dataFieldValue),
				HttpStatus.OK);
	}

}
