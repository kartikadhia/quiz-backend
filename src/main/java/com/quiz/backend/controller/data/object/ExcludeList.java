package com.quiz.backend.controller.data.object;

import java.util.ArrayList;
import java.util.List;

public class ExcludeList {
    List<Integer> excludeList = new ArrayList<>();

    public List<Integer> getExcludeList() {
        return excludeList;
    }

    public void setExcludeList(List<Integer> excludeList) {
        this.excludeList = excludeList;
    }
}
