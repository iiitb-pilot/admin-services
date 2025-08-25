package io.mosip.admin.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LostRidDetailsDto {
	
	Map<String, String> lostRidDataMap = new HashMap<>();
	

}
