package com.tripath.trifood.api.trifood.response;

import com.tripath.trifood.api.trifood.dto.PaymentManagerDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class StudentPaymentResponse {
    private List<PaymentManagerDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    public boolean lastPage;
}
