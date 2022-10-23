package ru.service.service1.impl.controller;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
public class Example {

    @XmlElement
    private String name;

    @XmlElement
    private int age;

}
