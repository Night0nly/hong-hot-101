import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
//        String link = args[0];
//        String fileOutputName = "SocialBriefing.docx";

//        TestHongFromChiasenhac hong = new TestHongFromChiasenhac();
//        hong.getStory("http://mp3.chiasenhac.vn/mp3/other/o-pop/la-cuna-de-arce~alquimia-de-alberto-rionda~ts373wzqqtw91e_download.html");

        //////// doc truyen
        int count = Integer.parseInt(args[1]);
        while(true) {
            String link = new String(args[0] + "/chuong-"+ count++);
            System.out.println("Fetching from "+link);
            if (args[2].equals("ttv")){
                HongTangThuVien hongBoy = new HongTangThuVien();
                ArrayList<HongHotItem> itemList = hongBoy.getStory(link);
                System.out.println(itemList.get(0).getTitle());
                System.out.println(itemList.get(0).getDescription());
            }else {
                HongTruyenCv hongBoy = new HongTruyenCv();
                ArrayList<HongHotItem> itemList = hongBoy.getStory(link);
                System.out.println(itemList.get(0).getTitle());
                System.out.println(itemList.get(0).getDescription());
            }
            try {
                System.in.read();
            } catch (IOException e) {
                System.out.println("Hinh nhu het chap r");
            }
        }
////////////////////////////////

//        HongHotBoy1 hongHotBoy = new HongHotBoy1();
//        LinkedList<HongHotItem> itemList = hongHotBoy.getStory(link);
//        ArrayList<HongHotItem> itemList = hongHotBoy.getStory(link);
//        Collections.sort(itemList, (a,b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));
//        DocumentExporter exporter = new DocumentExporter();
//        exporter.setFileOutputName(fileOutputName);
//        exporter.createTOC();
//        for(HongHotItem item : itemList){
//            exporter.exportToDocx(item.getTitle(), item.getDescription());
//        }
//
//        EmailSender sender = new EmailSender();
//        sender.sendMail(fileOutputName);

    }
}
