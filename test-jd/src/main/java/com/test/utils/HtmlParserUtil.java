package com.test.utils;

import com.sun.org.glassfish.external.statistics.Statistic;
import com.test.pojo.Product;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlParserUtil {

    public static String LOGIN_URL = "https://github.com/login";
    public static String USER_AGENT = "User-Agent";
    public static String USER_AGENT_VALUE = "Mozilla/5.0 (Linux; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Mobile Safari/537.36";

    public static void main(String[] args) throws Exception {
//        List<Product> products = HtmlParserUtil.parseHtml("java", 1);
//        for (Product product : products) {
//            System.out.println(product);
//        }
//        simulateLogin("619668402@qq.com", "15637195529a");
//        parseBaidu("郭淑娟");
    }

    public static List<Product> parseHtml(String keyword, int page) throws Exception {
        // https://list.tmall.com/search_product.htm?q=java&s=20
        // https://list.tmall.com/search_product.htm?spm=a220m.1000858.0.0.55f257d66OnLcH&s=60&q=java&sort=s&style=g&from=mallfp..pc_1_searchbutton&type=pc#J_Filter
        int pageSize = 60;
        List<Product> products = new ArrayList<>();
        String url = "https://list.tmall.com/search_product.htm?spm=a220m.1000858.0.0.55f257d66OnLcH&s=" + (page * pageSize) + "&q=" + keyword + "&sort=s&style=g&from=mallfp..pc_1_searchbutton&type=pc#J_Filter";
        Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Mobile Safari/537.36").get();
        System.out.println(document);
        Elements elements = document.getElementsByClass("product");
        for (Element element : elements) {
            String price = element.getElementsByClass("productPrice").get(0).getElementsByTag("em").get(0).attr("title");
            String img = element.getElementsByClass("productImg-wrap").get(0).getElementsByTag("img").get(0).attr("data-ks-lazyload");
            String title = element.getElementsByClass("productTitle").get(0).getElementsByTag("a").get(0).attr("title");
            String shop = element.getElementsByClass("productShop").get(0).getElementsByTag("a").get(0).text();
            Product product = new Product();
            product.setImg(img);
            product.setPrice(price);
            product.setShop(shop);
            product.setTitle(title);
            products.add(product);
        }
        return products;
    }

    // 模拟浏览器登录
    public static void simulateLogin(String username, String password) throws Exception {
        Connection conn1 = Jsoup.connect(LOGIN_URL);
        conn1.header(USER_AGENT, USER_AGENT_VALUE);
        Connection.Response response = conn1.execute();
        Document document = Jsoup.parse(response.body());
        Elements form = document.select("form");
        Map<String, String> map = new HashMap<>();
        for (Element element : form.get(0).getAllElements()) {
            if (element.attr("name").equals("login")) {
                element.attr("value", username);
            }
            if (element.attr("name").equals("password")) {
                element.attr("value", password);
            }
            if (element.attr("name").length() > 0) {
                map.put(element.attr("name"), element.attr("value"));
            }
        }
        // 第二次请求
        Connection conn2 = Jsoup.connect("https://github.com/session");
        conn2.header(USER_AGENT, USER_AGENT_VALUE);
        Connection.Response loginResponse = conn2.ignoreContentType(true).followRedirects(true).method(Connection.Method.POST)
                .data(map).cookies(response.cookies()).execute();
        System.out.println(loginResponse.cookies());
    }

    // 爬取CSDN博客
    public static void parseCSDN() throws IOException {
        String url = "https://blog.csdn.net/?spm=1001.2100.3001.4477";
        Connection connection = Jsoup.connect(url).header(USER_AGENT, USER_AGENT_VALUE);
        Connection.Response resp1 = connection.execute();

        // https://blog.csdn.net/api/articles?type=new&category=home&shown_offset=1608879199105048&first_view=true
        Connection connect = Jsoup.connect("https://blog.csdn.net/api/articles?type=new&category=home&shown_offset=1608879199105048&first_view=true");
        Connection.Response response = connect.ignoreContentType(true).followRedirects(true).method(Connection.Method.GET)
                .cookies(resp1.cookies()).headers(resp1.headers()).execute();
        System.out.println(response.body());
    }

    // 爬取百度(暂时只获取title, url(去除广告后的)
    public static void parseBaidu(String keyword) throws Exception {
        String url = "https://www.baidu.com/";
        Connection.Response resp = Jsoup.connect(url).header(USER_AGENT, USER_AGENT_VALUE).execute();
        Thread.sleep(1000);
        Connection.Response response = Jsoup.connect(url + "s").ignoreContentType(true).followRedirects(true).method(Connection.Method.GET)
                .data("wd", keyword).headers(resp.headers()).cookies(resp.cookies()).execute();
        Document document = Jsoup.parse(response.body());
        Element page = document.getElementById("page");
        // 第一页链接
        String first_href ="https://www.baidu.com/" + page.getElementsByTag("a").first().attr("href");
        System.out.println(first_href);
        parseBaiduResult(first_href, response.headers(), response.cookies());
    }

    public static void parseBaiduResult(String url, Map<String, String> headers, Map<String, String> cookies) throws Exception {
        Thread.sleep(3000);
        Connection.Response response = Jsoup.connect(url + "s").ignoreContentType(true).followRedirects(true).method(Connection.Method.GET)
                .headers(headers).cookies(cookies).execute();
        Document document = Jsoup.parse(response.body());
        Element content_left = document.getElementById("content_left");
        Elements result = content_left.getElementsByClass("result");
        for (Element item : result) {
            try {
                String title = item.getElementsByTag("h3").get(0).getElementsByTag("a").get(0).text();
                String href = item.getElementsByTag("h3").get(0).getElementsByTag("a").get(0).attr("href");
                System.out.println(title);
                System.out.println(href);
                System.out.println("---------------------------------");
            } catch (Exception e) {
                continue;
            }
        }
        // 下一页
        Element page = document.getElementById("page");
        String next_href = "https://www.baidu.com" + page.getElementsByTag("a").last().attr("href");
        parseBaiduResult(next_href, response.headers(), response.cookies());
    }
}
