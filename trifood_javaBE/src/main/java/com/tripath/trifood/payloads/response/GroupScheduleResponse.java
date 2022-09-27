package com.tripath.trifood.payloads.response;

import com.tripath.trifood.payloads.Dto.GroupScheduleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class GroupScheduleResponse {
    private List<GroupScheduleDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    public boolean lastPage;
}
