package pl.edu.agh.to.mosti.comparator.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Section {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String pageUrl;
    private String alias;
    private String selector;
    // For the first iteration we're supporting only EMAIL notification type.
    // This field holds e-mail address to sent notification to.
    private String contactInfo;

    protected Section() {

    }

    public Section(String pageUrl, String alias, String selector, String contactInfo) {
        this.pageUrl = pageUrl;
        this.alias = alias;
        this.selector = selector;
        this.contactInfo = contactInfo;
    }

    public long getId() {
        return id;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return String.format(
                "Section[id=%d, alias='%s', selector='%s', contactInfo='%s']",
                id, alias, selector, contactInfo);
    }
}
