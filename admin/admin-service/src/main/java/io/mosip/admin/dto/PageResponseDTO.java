package io.mosip.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDTO<T> {
    private long fromRecord;
    private long toRecord;
    private long totalRecord;
    @Valid
    private List<T> data;
}
