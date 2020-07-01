package vn.vnu.edu.mapping.common.utilities;

import vn.vnu.edu.mapping.rest.model.PageBase;

public class PageUtil {
    private static final Integer MAX_SIZE = 100;
    private static final Integer DEFAULT_SIZE = 10;
    private static final Integer DEFAULT_PAGE = 1;

    public static PageBase validate(Integer page, Integer size) {
        if (page == null) {
            page = DEFAULT_PAGE;
        }
        if (size == null) {
            size = DEFAULT_SIZE;
        }
        return new PageBase(page, size);
    }
}
