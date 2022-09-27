package com.tripath.trifood.payloads.response;

import com.tripath.trifood.payloads.Dto.EGroupDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class EGroupResponse {
    private List<EGroupDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    public boolean lastPage;
}
