package com.ccsw.tutorial.prestamo.model;

import com.ccsw.tutorial.common.pagination.PageableRequest;

/**
 * @author Paula Crespo
 */
public class PrestamoSearchDto {

    private PageableRequest pageable;

    public PageableRequest getPageable() {
        return pageable;
    }

    private void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }
}
