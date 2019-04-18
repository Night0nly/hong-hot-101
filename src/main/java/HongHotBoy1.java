import jdk.nashorn.internal.runtime.regexp.joni.exception.ErrorMessages;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class HongHotBoy1 implements HongBase {
    ArrayList<HongHotItem> itemList = new ArrayList<>();
//    LinkedList<HongHotItem> itemList = new LinkedList<>();

    public ArrayList<HongHotItem> getStory(String link){
//    public LinkedList<HongHotItem> getStory(String link){
        Document document = null;
        try {
            Connection connection = Jsoup.connect(link);
            document = connection.get();

            Elements items = document.selectFirst("div.composs-blog-list").getElementsByClass("item-content");
            for(Element item: items){
                String title = item.selectFirst("a").text();
                String url = item.selectFirst("a").attr("href");
                Connection con = Jsoup.connect(url);
                Document doc = con.get();
                String content = doc.getElementsByClass("shortcode-content").get(1).text();
//                System.out.println(content);
                itemList.add(new HongHotItem(title,content));
//                itemList.addFirst(new HongHotItem(title,content));
            }

            if (document.selectFirst("a.next.page-numbers") == null){
                System.out.println("Enddddddddddddddddddddddddddd");
            }else {
                getStory(document.selectFirst("a.next.page-numbers").attr("href"));
            }

        } catch (IOException e) {
            System.out.println("Fetch fail cmnr");
            e.printStackTrace();
        }
        return itemList;
    }
}


