package com.korobko.models;

import javax.xml.bind.annotation.*;
import java.util.Objects;

/**
 * @author Vova Korobko
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Paper {

    @XmlAttribute(required = true)
    @XmlID
    private long id;

    @XmlAttribute(required = true)
    private String title;

    @XmlElement(required = true)
    private PeriodicalType type;

    @XmlAttribute(required = true)
    private boolean isMonthly;

    @XmlElement(required = true)
    private Characteristics characteristics;

    public Paper() {
    }

    public Paper(long id, String title, PeriodicalType type, boolean isMonthly, Characteristics characteristics) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.isMonthly = isMonthly;
        this.characteristics = characteristics;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PeriodicalType getType() {
        return type;
    }

    public void setType(PeriodicalType type) {
        this.type = type;
    }

    public boolean isMonthly() {
        return isMonthly;
    }

    public void setMonthly(boolean monthly) {
        isMonthly = monthly;
    }


    public Characteristics getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Characteristics characteristics) {
        this.characteristics = characteristics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paper paper = (Paper) o;
        return id == paper.id &&
                isMonthly == paper.isMonthly &&
                Objects.equals(title, paper.title) &&
                type == paper.type &&
                Objects.equals(characteristics, paper.characteristics);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, type, isMonthly, characteristics);
    }
}
