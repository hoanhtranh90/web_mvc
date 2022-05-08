package com.cnpm.webadmin.until;

import org.springframework.data.domain.Sort;

public class ApplicationUtils {
    public static Sort getSort(String sortByProperties, String sortBy) {
        Sort sort = null;
        if (Sort.Direction.ASC.toString().equals(sortBy)) {
            sort = Sort.by(sortByProperties.split(StringUtils.COMMA)).ascending();
        } else {
            sort = Sort.by(sortByProperties.split(StringUtils.COMMA)).descending();
        }
        return sort;
    }

}
