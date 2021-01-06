/**
 * code adapted from github://Ayuget/Redface
 */

package fr.homnomnom;

import java.util.Date;

public class Post {
    private static final String MODERATORS_USERNAME = "Modération";

    private final long id;

    private String author;

    private int authorId;

    private String avatarUrl;

    private Date postDate;

    private Date lastEditionDate;

    private boolean isDeleted;

    private int quoteCount;

    private int topicPagesCount = -1;

    /**
     * Content of the post, straight in HTML.
     */
    private String htmlContent;

    public Post(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Date getLastEditionDate() {
        return lastEditionDate;
    }

    public void setLastEditionDate(Date lastEditionDate) {
        this.lastEditionDate = lastEditionDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public int getQuoteCount() {
        return quoteCount;
    }

    public void setQuoteCount(int quoteCount) {
        this.quoteCount = quoteCount;
    }

    public int getTopicPagesCount() {
        return topicPagesCount;
    }

    public void setTopicPagesCount(int topicPagesCount) {
        this.topicPagesCount = topicPagesCount;
    }

    public boolean isFromModerators() {
        return MODERATORS_USERNAME.equals(author);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Post{");
        sb.append("id=").append(id);
        sb.append(", author='").append(author).append('\'');
        sb.append(", authorId='").append(authorId).append('\'');
        sb.append(", postDate=").append(postDate);
        sb.append('}');
        return sb.toString();
    }
}
