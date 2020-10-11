public class Deleted {

    /*private static String generateIDClassCPP(ArrayList<String> xmlStringElements){
        StringBuilder resultCPP= new StringBuilder();
        resultCPP.append("#ifndef FPV_VR_ID_H\n");
        resultCPP.append("#define FPV_VR_ID_H\n\n");
        resultCPP.append("class ID {\n");
        resultCPP.append("public:\n");
        //for(String i:xmlStringElements){
        for(int i=0;i<xmlStringElements.size();i++){
            String s=xmlStringElements.get(i);
            Pair<String,String> tmp=splitIntoNameAndValue(s);
            resultCPP.append("    ");
            resultCPP.append(appendCPP(tmp.getKey(),i,tmp.getValue()));
            resultCPP.append("\n");
        }
        resultCPP.append("};\n");
        resultCPP.append("#endif //FPV_VR_ID_H");
        return resultCPP.toString();
    }*/
    /*private static String generateIDClassCPP(ArrayList<String> xmlStringElements){
        StringBuilder resultCPP= new StringBuilder();
        resultCPP.append("#ifndef FPV_VR_ID_H\n");
        resultCPP.append("#define FPV_VR_ID_H\n\n");
        resultCPP.append("class ID_ {\n");
        resultCPP.append("private:\n" +
                "private:\n" +
                "    struct IDElement{\n" +
                "        IDElement(const int i,const char* s):v1(i),v2(s){}\n" +
                "        const int v1;\n" +
                "        const char* v2;\n" +
                "    };\n");
        resultCPP.append("public:\n");
        //for(String i:xmlStringElements){
        for(int i=0;i<xmlStringElements.size();i++){
            String s=xmlStringElements.get(i);
            Pair<String,String> tmp=splitIntoNameAndValue(s);
            resultCPP.append("    ");
            resultCPP.append(appendCPP(tmp.getKey(),i,tmp.getValue()));
            resultCPP.append("\n");
        }
        resultCPP.append("};\n");
        resultCPP.append("#endif //FPV_VR_ID_H");
        return resultCPP.toString();
    }*/
    //Path currentRelativePath = Paths.get("");
    //        String currPath = currentRelativePath.toAbsolutePath().toString();
    //        System.out.println("Current relative path is: "+currPath);
}
