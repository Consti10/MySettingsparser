
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {

    private static final String DIRECTORY_DEVELOPMENT_FOLDER ="C:\\Users\\geier\\Desktop\\FPV_VR\\Development\\";

    private static final String DIR_FPV_VR= DIRECTORY_DEVELOPMENT_FOLDER + "FPV_VR_OS\\app\\src\\main\\";
    private static final String DIR_TELEMETRYCORE= DIRECTORY_DEVELOPMENT_FOLDER +"LiveVideo10ms\\TelemetryCore\\src\\main\\";
    private static final String DIR_RENDERINGXCORE= DIRECTORY_DEVELOPMENT_FOLDER +"RenderingX\\RenderingXCore\\src\\main\\";
    private static final String DIR_VIDEOCORE= DIRECTORY_DEVELOPMENT_FOLDER +"LiveVideo10ms\\VideoCore\\src\\main\\";

    public static void main(String[] args){
        //convert(DIR_TELEMETRYCORE+"res\\values\\pref_telemetry_ids.xml",DIR_TELEMETRYCORE+"cpp\\Telemetry","IDT");
        //convert(DIR_FPV_VR+"res\\values\\pref_osd_ids.xml", DIR_FPV_VR+"cpp\\Scene\\OSD\\Settings","IDOSD");
        //convert(DIR_FPV_VR+"res\\values\\pref_vr_ids.xml", DIR_FPV_VR+"cpp\\VRSettings","IDVR");
        //convert(DIR_VIDEOCORE+"res\\values\\pref_video_ids.xml",DIR_VIDEOCORE+"cpp","IDV");
    }

    private static void convert(final String pathToSource,final String pathToDestination,final String destinationFileName){
        String fileAsString="TEST";
        try {
            fileAsString=readFile(pathToSource,Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Pair<String,String>> tuplesList=Parser.parse(fileAsString);
        Parser.checkForEquality(tuplesList);

        String s1= ClassGenerator.generateIDClassJAVA(tuplesList,destinationFileName);
        String s2= ClassGenerator.generateIDClassCPP(tuplesList,destinationFileName);

        System.out.println(s1);
        System.out.println(s2);

        writeStringToFile(s2,pathToDestination,destinationFileName+".hpp");
    }


    private static String readFile(final String path,final Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    private static void writeStringToFile(final String input,final String directory,final String filename){
        try {
            //create the directory if not already existing
            final boolean mkdir = new File(directory).mkdir();
            File file = new File(directory+"\\"+filename);
            final boolean newFile = file.createNewFile();
            FileOutputStream fos=new FileOutputStream(file);
            fos.write(input.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
