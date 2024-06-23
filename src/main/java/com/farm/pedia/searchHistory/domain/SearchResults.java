package com.farm.pedia.searchHistory.domain;

import java.util.List;

public class SearchResults {
    private List<String> names;

    public SearchResults(List<String> names) {
        this.names = names;
    }

    @Override
    public String toString() {
        return names.toString();
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
