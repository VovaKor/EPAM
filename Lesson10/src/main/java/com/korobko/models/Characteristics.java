package com.korobko.models;

import javax.xml.bind.annotation.*;
import java.util.Objects;

/**
 * @author Vova Korobko
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Characteristics {
    @XmlAttribute(required = true)
    private boolean isColored;
    @XmlAttribute(required = true)
    private int pageAmount;
    @XmlAttribute(required = true)
    private boolean isGlossy;
    @XmlAttribute
    private int subscriptionIndex;

    public Characteristics() {
    }

    public Characteristics(boolean isColored, int pageAmount, boolean isGlossy) {
        this.isColored = isColored;
        this.pageAmount = pageAmount;
        this.isGlossy = isGlossy;
    }

    public void setSubscriptionIndex(int subscriptionIndex) {
        this.subscriptionIndex = subscriptionIndex;
    }

    public boolean isColored() {
        return isColored;
    }

    public void setColored(boolean colored) {
        isColored = colored;
    }

    public int getPageAmount() {
        return pageAmount;
    }

    public void setPageAmount(int pageAmount) {
        this.pageAmount = pageAmount;
    }

    public boolean isGlossy() {
        return isGlossy;
    }

    public void setGlossy(boolean glossy) {
        isGlossy = glossy;
    }


    public int getSubscriptionIndex() {
        return subscriptionIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Characteristics that = (Characteristics) o;
        return isColored == that.isColored &&
                pageAmount == that.pageAmount &&
                isGlossy == that.isGlossy &&
                subscriptionIndex == that.subscriptionIndex;
    }

    @Override
    public int hashCode() {

        return Objects.hash(isColored, pageAmount, isGlossy, subscriptionIndex);
    }
}
