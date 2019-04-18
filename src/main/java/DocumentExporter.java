import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class DocumentExporter {
    private XWPFDocument doc = new XWPFDocument();
    private String fileOutputName;

    public String getFileOutputName() {
        return fileOutputName;
    }

    public void setFileOutputName(String fileOutputName) {
        this.fileOutputName = fileOutputName;
    }

    public DocumentExporter() {
        XWPFDocument doc = new XWPFDocument();
    }

    public void exportToDocx(String title, String content){

        XWPFParagraph p = doc.createParagraph();

//        CTP ctP = p.getCTP();
//        CTSimpleField toc = ctP.addNewFldSimple();
//        toc.setInstr("TOC \\h");
//        toc.setDirty(STOnOff.TRUE);

        p.setStyle("heading 1");
        p.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r = p.createRun();
        r.setText(title);
        r.setBold(true);
        r.setFontFamily("Times New Roman");
        r.setFontSize(23);
        r.addBreak();

        XWPFParagraph p1 = doc.createParagraph();
        p1.setSpacingBetween(20, LineSpacingRule.EXACT);
        p1.setIndentationFirstLine(600);
        XWPFRun r1 = p1.createRun();
        r1.setText(content);
        r1.setFontFamily("Time New Roman");
        r.setFontSize(15);
        r1.addBreak(BreakType.PAGE);

        try (FileOutputStream out = new FileOutputStream(fileOutputName)) {
            doc.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createTOC(){
        XWPFParagraph p = doc.createParagraph();

        CTP ctP = p.getCTP();
        CTSimpleField toc = ctP.addNewFldSimple();
        toc.setInstr("TOC \\h");
        toc.setDirty(STOnOff.TRUE);

        doc.createTOC();
        addCustomHeadingStyle(doc, "heading 1", 1);

    }

    private void addCustomHeadingStyle(XWPFDocument docxDocument, String strStyleId, int headingLevel) {

        CTStyle ctStyle = CTStyle.Factory.newInstance();
        ctStyle.setStyleId(strStyleId);

        CTString styleName = CTString.Factory.newInstance();
        styleName.setVal(strStyleId);
        ctStyle.setName(styleName);

        CTDecimalNumber indentNumber = CTDecimalNumber.Factory.newInstance();
        indentNumber.setVal(BigInteger.valueOf(headingLevel));

        // lower number > style is more prominent in the formats bar
        ctStyle.setUiPriority(indentNumber);

        CTOnOff onoffnull = CTOnOff.Factory.newInstance();
        ctStyle.setUnhideWhenUsed(onoffnull);

        // style shows up in the formats bar
        ctStyle.setQFormat(onoffnull);

        // style defines a heading of the given level
        CTPPr ppr = CTPPr.Factory.newInstance();
        ppr.setOutlineLvl(indentNumber);
        ctStyle.setPPr(ppr);

        XWPFStyle style = new XWPFStyle(ctStyle);

        // is a null op if already defined
        XWPFStyles styles = docxDocument.createStyles();

        style.setType(STStyleType.PARAGRAPH);
        styles.addStyle(style);

    }

}
