package io.mosip.admin.dto;

import java.util.List;

import lombok.Data;

@Data
public class LostRidExtnDto {
	private long fromRecord;

	private long toRecord;

	private long totalRecord;

	List<LostRidDto> data;
}