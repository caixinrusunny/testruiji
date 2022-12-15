package com.MyApp.bean;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Book {
    private int id;
    private String name;
    private String type;
    private String description;
}
