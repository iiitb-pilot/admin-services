package io.mosip.admin.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class SearchFieldDtos {
	
	private String id;
    private List<String> fields;
    private String source;
    private String process;
    private Boolean bypassCache;

}
