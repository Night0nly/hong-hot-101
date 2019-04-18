import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class TestHongFromChiasenhac implements HongBase {
    private ArrayList<HongHotItem> itemList = new ArrayList<>();

    @Override
    public ArrayList<HongHotItem> getStory(String link) {
        Document document = null;
        try {
            Connection connection = Jsoup.connect(link);
            document = connection.get();
            String print = document
                    .getElementById("downloadlink2")
                    .select("a")
//                    .select("a")
                    .text();
            System.out.println(print);
        } catch (IOException e) {
            System.out.println("Fetch fail cmnr");
            e.printStackTrace();
        }
        return itemList;
    }
}
