
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.edtech.curriculum.*;
import org.edtech.curriculum.SchoolType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class main {

    public static void main(String[] args) {
        File  folderForSkolverkData = new File("C:\\curry\\data");
        folderForSkolverkData.mkdirs();
        File folderForParsedData = new File("C:\\curry\\data\\parsed");
        folderForParsedData.mkdirs();
        for(int  i= 0;i< SchoolType.values().length; i++){
            SchoolType schType =  SchoolType.values()[i];
            XmlMapper mapper = new XmlMapper();
            HolderOfSubjects contentHolder = new HolderOfSubjects();
            List<Subject> subjects = new Curriculum(schType, folderForSkolverkData, false).getSubjects();
                contentHolder.subjectList = subjects;
            try {
                String s = mapper.writeValueAsString(contentHolder);
                try (PrintWriter out = new PrintWriter("C:\\curry\\data\\parsed\\"+schType+".txt")) {
                    out.println(s);
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
