package com.tripath.trifood.api.trifood.response;

import com.tripath.trifood.api.trifood.dto.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class FoodResponse {
    private List<FoodDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    public boolean lastPage;
}
