package com.cybertek.pojo;

/*
 "links": [
                {
                    "rel": "self",
                    "href": "http://44.195.43.243:1000/ords/hr/regions/1"
                }
 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Link {
    private String rel;
    private String href;

}
