package org.htmlcleaner;

import junit.framework.TestCase;
import org.junit.Test;

public class NestingTest extends TestCase {

    public final static int TOO_DEEP_NESTING = 9999;
    public final static String TOO_DEEP_DOC = _nestedDoc(TOO_DEEP_NESTING, "<div>", "</div>", "");

    public static String _nestedDoc(int nesting, String open, String close, String content) {
        StringBuilder sb = new StringBuilder(nesting * (open.length() + close.length()));
        for (int i = 0; i < nesting; ++i) {
            sb.append(open);
            if ((i & 31) == 0) {
                sb.append("\n");
            }
        }
        sb.append("\n").append(content).append("\n");
        for (int i = 0; i < nesting; ++i) {
            sb.append(close);
            if ((i & 31) == 0) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    @Test
    public void testDeepNesting(){
        HtmlCleaner cleaner = new HtmlCleaner();
        TagNode root = cleaner.clean(TOO_DEEP_DOC);
    }
}
