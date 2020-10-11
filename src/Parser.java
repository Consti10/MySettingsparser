
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Parser {

    public static ArrayList<Pair<String,String>> parse(final String input){
        ArrayList<String> subEl= extractSubElementsXMLString(input);
        ArrayList<Pair<String,String>> tuplesList=new ArrayList<>();
        for(String s : subEl){
            Pair<String,String> tuple=splitIntoNameAndValue(s);
            tuplesList.add(tuple);
        }
        return  tuplesList;
    }


    public static void checkForEquality(ArrayList<Pair<String,String>> input){
        for(Pair<String,String> tmp:input) {
            if(!tmp.getKey().equals(tmp.getValue())){
                System.out.println(tmp.getKey()+"  "+tmp.getValue());
            }else{
                System.out.println("Okay");
            }
        }
    }

    //Each .xml file contains sub-elements in the form of
    //<string name="xxxVALUExxx">xxxVALUExxx</string>
    //these elements are what we are interested in. In everything else (e.g. comments)
    //we are not interested
    //split file int sub-elements which are in form of
    //xxxVALUExxx">xxxVALUExxx
    private static ArrayList<String> extractSubElementsXMLString(String xmlFile){
        final int l1=14;
        final int l2=9;
        ArrayList<String> ret=new ArrayList<>();
        for(int i=0;i<xmlFile.length()-l1;i++){
            if(xmlFile.substring(i,i+l1).equals("<string name=\"")){
                //we have found the beginning. Now find the end:
                for(int j=i+l1;j<xmlFile.length()-l2;j++){
                    if(xmlFile.substring(j,j+l2).equals("</string>")){
                        //we have found the end
                        String tmp=xmlFile.substring(i+l1,j);
                        //System.out.println(""+tmp);
                        ret.add(tmp);
                        break;
                    }
                }
            }
        }
        return ret;
    }

    //input has to be in form of:
    // xxxVALUExxx">xxxVALUExxx
    private static Pair<String,String> splitIntoNameAndValue(String input){
        final int l1=2;
        String name="";
        String value="";
        for(int i=0;i<input.length()-l1;i++){
            if(input.substring(i,i+l1).equals("\">")){
                name=input.substring(0,i);
                value=input.substring(i+l1,input.length());
            }
        }
        //System.out.println(name+"|"+value);
        //return Pair.create(name,value);
        return new Pair<>(name,value);
    }


}
