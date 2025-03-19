package com.in28minutes.springboot.tutorial.basics.application.configuration.model;
import org.springframework.data.domain.Page;

public class Pagination {

    private boolean previousPage;
    private boolean nextPage;

    public Pagination(Page<Playlist> page) {
        this.previousPage = page.hasPrevious();
        this.nextPage = page.hasNext();
    }

    public boolean isPreviousPage() {
        return previousPage;
    }

    public boolean isNextPage() {
        return nextPage;
    }
}
