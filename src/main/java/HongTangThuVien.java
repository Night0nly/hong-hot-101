import jdk.nashorn.internal.runtime.regexp.joni.exception.ErrorMessages;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class HongTangThuVien implements HongBase{
    private ArrayList<HongHotItem> itemList = new ArrayList<>();
//    LinkedList<HongHotItem> itemList = new LinkedList<>();

    public ArrayList<HongHotItem> getStory(String link){
//    public LinkedList<HongHotItem> getStory(String link){
        Document document = null;
        try {
            Connection connection = Jsoup.connect(link);
            document = connection.get();

            String chapter = document.getElementsByClass("chapter").first().selectFirst("h2").text();
            String content = document.getElementsByClass("box-chap").first().text();
            itemList.add(new HongHotItem(chapter, content));

            Element nextChapElement = document.selectFirst("a.bot-next_chap");

            if (nextChapElement == null){
                System.out.println("Enddddddddddddddddddddddddddd");
            }else {
                System.out.println(nextChapElement.attr("href"));
            }

        } catch (IOException e) {
            System.out.println("Fetch fail cmnr");
            e.printStackTrace();
        }
        return itemList;
    }
}


