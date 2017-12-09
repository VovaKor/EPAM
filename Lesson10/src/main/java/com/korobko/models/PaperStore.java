package com.korobko.models;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Vova Korobko
 */
@XmlRootElement(name = "paper_store")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaperStore {
    @XmlElement(name = "paper", required = true)
    private Set<Paper> papers = new HashSet<>();

    public Set<Paper> getPapers() {
        return papers;
    }

    public void setPapers(Set<Paper> papers) {
        this.papers = papers;
    }
}
