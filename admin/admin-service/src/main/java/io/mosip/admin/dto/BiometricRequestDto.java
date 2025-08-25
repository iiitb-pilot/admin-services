package io.mosip.admin.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class BiometricRequestDto {
	
	private String id;
    private String person;
    private List<String> modalities;
    private String source;
    private String process;
    private boolean bypassCache;

}
