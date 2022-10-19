package com.tripath.trifood.api.trifood.response;

import com.tripath.trifood.api.trifood.dto.EScheduleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class EScheduleResponse {
    private List<EScheduleDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    public boolean lastPage;
}
