package com.fajarmf.demoaja;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SlugAndTitle {
    String title;
    String slug;

    SlugAndTitle(String title, String slug){
        this.title = title;
        this.slug = slug;
    }

}
