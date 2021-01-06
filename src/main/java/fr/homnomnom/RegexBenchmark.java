package fr.homnomnom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexBenchmark extends AbstractParserBenchmark {
 /**
     * Default number of posts per topic page. Used to initialize default capacity
     * for the list of posts (small performance improvement ?)
     */
    private static final int DEFAULT_POSTS_COUNT = 40;

    private static final Pattern DESCRIPTION_PATTERN = Pattern.compile(
            "(?:<meta name=\"Description\" content=\")(?:.*)(?:Pages : )(\\d+)(?:[^\"])"
    );

    private static final Pattern POST_PATTERN = Pattern.compile(
            "(<table\\s*cellspacing.*?class=\"([a-z]+)\">.*?" +
                    "<tr.*?class=\"message.*?" +
                    "<a.*?href=\"#t([0-9]+)\".*?" +
                    "<b.*?class=\"s2\">(?:<a.*?>)?(.*?)(?:</a>)?</b>.*?" +
                    "(?:(?:<div\\s*class=\"avatar_center\".*?><img src=\"(.*?)\"\\s*alt=\".*?\"\\s*/></div>)|</td>).*?" +
                    "<div.*?class=\"left\">Posté le ([0-9]+)-([0-9]+)-([0-9]+).*?([0-9]+):([0-9]+):([0-9]+).*?" +
                    "<div.*?id=\"para[0-9]+\">(.*?)<div style=\"clear: both;\">\\s*</div></p>" +
                    "(?:<div\\s*class=\"edited\">)?(?:<a.*?>Message cité ([0-9]+) fois</a>)?(?:<br\\s*/>Message édité par .*? le ([0-9]+)-([0-9]+)-([0-9]+).*?([0-9]+):([0-9]+):([0-9]+)</div>)?.*?" +
                    "</div></td></tr></table>)"
            , Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    private static final Pattern USER_PROFILE_ID_PATTERN = Pattern.compile(
            ".*<a href=\"/hfr/profil-([0-9]+)\\.htm\".*",
            Pattern.CASE_INSENSITIVE | Pattern.DOTALL
    );

    public List<Post> parse(String html) {
        List<Post> posts = new ArrayList<>(DEFAULT_POSTS_COUNT);

        // Description tag parsing to find the total number of pages. If
        int topicPagesCount = UNKNOWN_PAGES_COUNT;
        Matcher pagesMatcher = DESCRIPTION_PATTERN.matcher(html);

        if (pagesMatcher.find()) {
            topicPagesCount = Integer.valueOf(pagesMatcher.group(1));
        }

        Matcher m = POST_PATTERN.matcher(html);

        while (m.find()) {
            long postId = Long.parseLong(m.group(3));
            String postHTMLContent = m.group(12);
            Date postDate = fromHTMLDate(m.group(8), m.group(7), m.group(6), m.group(9), m.group(10), m.group(11));
            Date lastEditDate = null;
            int quoteCount = 0;
            String author = m.group(4);
            String avatarUrl = m.group(5);
            boolean wasEdited = m.group(14) != null;
            boolean wasQuoted = m.group(13) != null;

            if (wasEdited) {
                lastEditDate = fromHTMLDate(m.group(16), m.group(15), m.group(14), m.group(17), m.group(18), m.group(19));
            }

            if (wasQuoted) {
                quoteCount = Integer.parseInt(m.group(13));
            }

            Matcher userProfileIdMatcher = USER_PROFILE_ID_PATTERN.matcher(m.group(1));
            Integer authorUserId = userProfileIdMatcher.matches() ? Integer.parseInt(userProfileIdMatcher.group(1)) : null;

            Post post = new Post(postId);
            post.setHtmlContent(postHTMLContent);
            post.setAuthor(author);
            post.setAuthorId(authorUserId);
            post.setAvatarUrl(avatarUrl);
            post.setLastEditionDate(lastEditDate);
            post.setPostDate(postDate);
            post.setQuoteCount(quoteCount);

            if (topicPagesCount != UNKNOWN_PAGES_COUNT) {
                post.setTopicPagesCount(topicPagesCount);
            }

            posts.add(post);
        }

        return posts;
    }
}
