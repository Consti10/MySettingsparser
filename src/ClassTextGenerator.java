
import java.util.ArrayList;

public class ClassTextGenerator {

    /**
     * Create the text for a JAVA class that contains key-value pairs as public members
     */
    public static String generateIDClassJAVA(final ArrayList<Pair<String,String>> input, final String className){
        StringBuilder resultJAVA= new StringBuilder();
        resultJAVA.append("package constantin.fpv_vr.Settings;\n\n");
        resultJAVA.append("import android.util.Pair;\n\n");
        resultJAVA.append("public final class ");
        resultJAVA.append(className);
        resultJAVA.append("ID {\n");
        for(int i=0;i<input.size();i++){
            Pair<String,String> tmp=input.get(i);
            resultJAVA.append("    ");
            resultJAVA.append(appendJava(tmp.getKey(),i,tmp.getValue()));
            resultJAVA.append("\n");
        }
        resultJAVA.append("}");
        return resultJAVA.toString();
    }

    /**
     * Create the text for a CPP class that contains key-value pairs as public members
     */
    public static String generateIDClassCPP(ArrayList<Pair<String,String>> input,final String className){
        final String INCLUDE_GUARD="CONSTI_10_100_"+className;
        StringBuilder resultCPP= new StringBuilder();
        resultCPP.append("#ifndef ").append(INCLUDE_GUARD).append("\n");
        resultCPP.append("#define ").append(INCLUDE_GUARD).append("\n\n");
        resultCPP.append("class ");
        resultCPP.append(className);
        resultCPP.append("{\n");
        resultCPP.append("public:\n");
        //for(String i:input){
        for (Pair<String, String> tmp : input) {
            resultCPP.append("    ");
            //resultCPP.append(appendCPP(tmp.getKey(),i,tmp.getValue()));
            resultCPP.append(appendCPP2(tmp.getKey(), tmp.getValue()));
            resultCPP.append("\n");
        }
        resultCPP.append("};\n");
        resultCPP.append("\n");
        resultCPP.append("#endif //").append(INCLUDE_GUARD).append("\n");
        return resultCPP.toString();
    }

    private static String appendJava(String name,int key,String value){
        //return "public static final String "+name+"="+"\""+value+"\""+";";
        return "public static final Pair<Integer,String> "+name+"="+"new Pair<>("+key+",\""+value+"\");";
    }

    private static String appendCPP(String name,int key,String value){
        //return "static constexpr const char* "+name+"="+"\""+value+"\""+";";
        //return "const IDElement "+name+"{"+key+",\""+value+"\"};";
        return "static constexpr const int "+name+"="+key+";";
    }

    private static String appendCPP2(String name,String value){
        return "static constexpr const char* "+name+"="+"\""+value+"\""+";";
    }
}
