package com.pyomin.hood.common.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageRequest {

    @Builder.Default
    private final int page = 1;

    @Builder.Default
    private final int size = 10;
}
