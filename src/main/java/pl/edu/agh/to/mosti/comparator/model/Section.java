package pl.edu.agh.to.mosti.comparator.model;

public class Section {
    private long id;
    private String pageUrl;
    private String alias;
    private String selector;

    // For the first iteration we're supporting only EMAIL notification type.
    // This field holds e-mail address to sent notification to.
    private String contactInfo;

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
}
