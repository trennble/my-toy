package com.trennble.leet.normal;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HtmEntityParser_1410 {

    public static void main(String[] args) {
        String t1 = "&amp; is an HTML entity but &ambassador; is not.";
        String t2 = "and I quote: &quot;...&quot;";
        String t3 = "Stay home! Practice on Leetcode :)";
        String t4 = "x &gt; y &amp;&amp; x &lt; y is always false";

        HtmEntityParser_1410 htmEntityParser1410 = new HtmEntityParser_1410();
        System.out.println(htmEntityParser1410.entityParser(t1));
        System.out.println(htmEntityParser1410.entityParser(t2));
        System.out.println(htmEntityParser1410.entityParser(t3));
        System.out.println(htmEntityParser1410.entityParser(t4));
    }


    public String entityParser(String text) {
        int n = text.length();
        StringBuilder ans = new StringBuilder();
        Map<String, String> parseMap = new HashMap();
        parseMap.put("&quot;", "\"");
        parseMap.put("&apos;", "'");
        parseMap.put("&amp;", "&");
        parseMap.put("&gt;", ">");
        parseMap.put("&lt;", "<");
        parseMap.put("&frasl;", "/");
        int maxLen = 7;
        int lastPos = 0;
        for (int i = 0; i < n; i++) {
            if (text.charAt(i) == '&') {
                int j = i + 1;
                while (j < i + maxLen && j < n && text.charAt(j) != ';') {
                    j++;
                }

                if (j>=n || j>=i+maxLen) {
                    continue;
                }

                if (text.charAt(j) == ';') {
                    String sub = text.substring(i, j + 1);
                    String parsed = parseMap.get(sub);
                    if (parsed != null) {
                        ans.append(text, lastPos, i).append(parsed);
                        i = j;
                        lastPos = i + 1;
                    }
                }
            }
        }

        if (lastPos < n) {
            ans.append(text, lastPos, n);
        }

        return ans.toString();
    }
}
