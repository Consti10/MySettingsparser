import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModifyShaderFile {



    private static final String s1="s.append(";
    private static final String s2=");XX";

    public static void parse(String input){
        /*String tmp=removes1(input);
        System.out.println(tmp);
        tmp=replaces2(tmp);
        System.out.println(tmp);*/
        String lines[] = input.split("\\r?\\n");
        final Pattern MY_PATTERN = Pattern.compile(s1+"(.*?)"+s2);

        for (int i=0;i<lines.length;i++) {
            lines[i]=lines[i]+"XX";
        }

        for (int i=0;i<lines.length;i++) {
            String line=lines[i];
            Matcher m = MY_PATTERN.matcher(line);
            if(!line.contains("//") && m.find()){
                String g0=m.group(0);
                String g1= m.group(1);
                String g2=m.group(2);
                //System.out.println(g1);
                //g1==(was wir bracuhen)
                String valid=g1.substring(1,g1.length()-1);
                System.out.println("s+="+valid+";");
            }else{
                System.out.println(line.substring(0,line.length()-2));
            }
        }

        /*while (m.find()) {
            String s = m.group(1);
            // s now contains "BAR"
            System.out.println(s);
        }*/

        /*for (int i=0;i<lines.length;i++) {
            String line=lines[i];
            if(line.length()<s1.length()){
                continue;
            }
            String cmp1Before=line.substring(0,s1.length());
            String cmp1After=line.substring(s1.length());
            if(cmp1Before.equals(s1)){
                String cmp2Before=cmp1After.substring(0,cmp1After.length()-s2.length());
                String cmp2After=cmp1After.substring(cmp1After.length()-s2.length());
                String split=cmp1Before+""+cmp2After;
                System.out.println(split);
            }
        }*/

        /*if(line.substring(0,s1.length()).equals(s1)){
                if(line.substring(line.length()-s2.length()).equals(s2)){
                    String middle=line.substring(s1.length(),line.length()-s2.length());
                    middle=s1+middle+s2;
                    lines[i]=middle;
                }
            }*/


        //for (String line : lines) {
        //    System.out.println(line);
        //}
    }

    /*private static String finds1__s2(String input){
        for(int i=0;i<input.length()-10;i++){
            if(input.substring(i,i+s1.length()).equals(s1)){
                String tmp=input.substring(i);
                for(int j=0;j<tmp.length();j++){
                    if(tmp.substring(j,j+s2.length()).equals(s1)){
                        String before=input.substring(0,i+s1.length());
                        before="s+="+before;
                        String after=input.substring(j+s2.length());
                        after+="";

                    }
                }
            }
        }
        return input;
    }*/

    private static String removes1(String input){
        for(int i=0;i<input.length()-10;i++){
            if(input.substring(i,i+s1.length()).equals(s1)){
                String before=input.substring(0,i);
                before+="s+=";
                String after=input.substring(i+s1.length());
                input=before+after;
                i=0;
            }
        }
        return input;
    }
    private static String replaces2(String input){
        for(int i=0;i<input.length()-10;i++){
            if(input.substring(i,i+s2.length()).equals(s2)){
                String before=input.substring(0,i);
                before+="\\n\";";
                String after=input.substring(i+s2.length());
                input=before+after;
                i=0;
            }
        }
        return input;
    }


    public static final String file="//GEOMETRY####GEOMETRY####GEOMETRY####GEOMETRY####GEOMETRY####GEOMETRY####GEOMETRY####GEOMETRY####GEOMETRY####GEOMETRY####\n" +
            "static const std::string vs_geometry(){\n" +
            "    std::string s=\"\";\n" +
            "    s.append(\"uniform mat4 uMVPMatrix;\\n\");\n" +
            "    s.append(\"attribute vec4 aPosition;\\n\");\n" +
            "    s.append(\"attribute vec4 aColor;\\n\");\n" +
            "    s.append(\"varying vec4 vColor;\\n\");\n" +
            "    s.append(\"void main(){\\n\");\n" +
            "#ifdef WIREFRAME\n" +
            "    s.append(\"gl_PointSize=15.0;\");\n" +
            "#endif\n" +
            "    s.append(\"vColor = aColor;\\n\");\n" +
            "    s.append(\"gl_Position = uMVPMatrix* aPosition;\\n\");\n" +
            "    s.append(\"}\\n\");\n" +
            "    return s;\n" +
            "}\n" +
            "static const std::string vs_geometry_vddc(const float coeficients[]){\n" +
            "    std::string s=\"\";\n" +
            "    s.append(\"uniform mat4 uMVMatrix;\\n\");\n" +
            "    s.append(\"uniform mat4 uPMatrix;\\n\");\n" +
            "    s.append(\"attribute vec4 aPosition;\\n\");\n" +
            "    s.append(\"attribute vec4 aColor;\\n\");\n" +
            "    s.append(\"varying vec4 vColor;\\n\");\n" +
            "    s.append(\"float r2;\\n\");\n" +
            "    s.append(\"vec4 pos;\\n\");\n" +
            "    s.append(\"float ret;\\n\");\n" +
            "    s.append(\"const float _MaxRadSq=\");\n" +
            "    s.append(tostr(coeficients[0]));\n" +
            "    s.append(\";\\n\");\n" +
            "    //There is no vec6 data type. Therefore, we use 1 vec4 and 1 vec2. Vec4 holds k1,k2,k3,k4 and vec6 holds k5,k6\n" +
            "    s.append(\"const vec4 _Undistortion=vec4(\");\n" +
            "    s.append(tostr(coeficients[1]));\n" +
            "    s.append(\",\");\n" +
            "    s.append(tostr(coeficients[2]));\n" +
            "    s.append(\",\");\n" +
            "    s.append(tostr(coeficients[3]));\n" +
            "    s.append(\",\");\n" +
            "    s.append(tostr(coeficients[4]));\n" +
            "    s.append(\");\\n\");\n" +
            "    s.append(\"const vec2 _Undistortion2=vec2(\");\n" +
            "    s.append(tostr(coeficients[5]));\n" +
            "    s.append(\",\");\n" +
            "    s.append(tostr(coeficients[6]));\n" +
            "    s.append(\");\\n\");\n" +
            "    s.append(\"void main(){\\n\");\n" +
            "    s.append(\"  pos=uMVMatrix * aPosition;\\n\");\n" +
            "    s.append(\"  r2=clamp(dot(pos.xy,pos.xy)/(pos.z*pos.z),0.0,_MaxRadSq);\\n\");\n" +
            "    s.append(\"  ret = 0.0;\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion2.y);\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion2.x);\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion.w);\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion.z);\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion.y);\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion.x);\\n\");\n" +
            "    s.append(\"  pos.xy*=1.0+ret;\\n\");\n" +
            "    s.append(\"  gl_Position=uPMatrix*pos;\\n\");\n" +
            "    s.append(\"  vColor = aColor;\\n\");\n" +
            "#ifdef WIREFRAME\n" +
            "    s.append(\"gl_PointSize=15.0;\");\n" +
            "#endif\n" +
            "    s.append(\"}\\n\");\n" +
            "    return s;\n" +
            "}\n" +
            "static const std::string fs_geometry(){\n" +
            "    std::string s=\"\";\n" +
            "    s.append(\"precision mediump float;\\n\");\n" +
            "    s.append(\"varying vec4 vColor;\\n\");\n" +
            "    s.append(\"void main(){\\n\");\n" +
            "    s.append(\"gl_FragColor = vColor;\\n\");\n" +
            "#ifdef WIREFRAME\n" +
            "    s.append(\"gl_FragColor.rgb=vec3(1.0,1.0,1.0);\\n\");\n" +
            "#endif\n" +
            "    s.append(\"}\\n\");\n" +
            "    s.append(\"\\n\");\n" +
            "    return s;\n" +
            "}\n" +
            "//TEXT###TEXT###TEXT###TEXT###TEXT###TEXT###TEXT###TEXT###TEXT###TEXT###TEXT###TEXT###TEXT###TEXT###TEXT###TEXT###TEXT###\n" +
            "static const std::string vs_text(){\n" +
            "    std::string s=\"\";\n" +
            "    s.append(\"uniform mat4 uMVPMatrix;\\n\");\n" +
            "    s.append(\"attribute vec4 aPosition;\\n\");\n" +
            "    s.append(\"attribute vec2 aTexCoord;\\n\");\n" +
            "    s.append(\"varying vec2 vTexCoord;\\n\");\n" +
            "    s.append(\"attribute vec4 aVertexColor;\\n\");\n" +
            "    s.append(\"varying vec4 vVertexColor;\\n\");\n" +
            "    s.append(\"\\n\");\n" +
            "    s.append(\"void main() {\\n\");\n" +
            "#ifdef WIREFRAME\n" +
            "    s.append(\"gl_PointSize=15.0;\");\n" +
            "#endif\n" +
            "    s.append(\"gl_Position = uMVPMatrix * aPosition;\\n\");\n" +
            "    s.append(\"vTexCoord = aTexCoord;\\n\");\n" +
            "    s.append(\"  vVertexColor = aVertexColor;\\n\");\n" +
            "    s.append(\"}\");\n" +
            "    return s;\n" +
            "}\n" +
            "static const std::string vs_text_vddc(const float coeficients[]){\n" +
            "    std::string s=\"\";\n" +
            "    s.append(\"uniform mat4 uMVMatrix;\\n\");\n" +
            "    s.append(\"uniform mat4 uPMatrix;\\n\");\n" +
            "    s.append(\"attribute vec4 aPosition;\\n\");\n" +
            "    s.append(\"attribute vec2 aTexCoord;\\n\");\n" +
            "    s.append(\"varying vec2 vTexCoord;\\n\");\n" +
            "    s.append(\"attribute vec4 aVertexColor;\\n\");\n" +
            "    s.append(\"varying vec4 vVertexColor;\\n\");\n" +
            "    s.append(\"float r2;\\n\");\n" +
            "    s.append(\"vec4 pos;\\n\");\n" +
            "    s.append(\"float ret;\\n\");\n" +
            "    s.append(\"const float _MaxRadSq=\");\n" +
            "    s.append(tostr(coeficients[0]));\n" +
            "    s.append(\";\\n\");\n" +
            "    //There is no vec6 data type. Therefore, we use 1 vec4 and 1 vec2. Vec4 holds k1,k2,k3,k4 and vec6 holds k5,k6\n" +
            "    s.append(\"const vec4 _Undistortion=vec4(\");\n" +
            "    s.append(tostr(coeficients[1]));\n" +
            "    s.append(\",\");\n" +
            "    s.append(tostr(coeficients[2]));\n" +
            "    s.append(\",\");\n" +
            "    s.append(tostr(coeficients[3]));\n" +
            "    s.append(\",\");\n" +
            "    s.append(tostr(coeficients[4]));\n" +
            "    s.append(\");\\n\");\n" +
            "    s.append(\"const vec2 _Undistortion2=vec2(\");\n" +
            "    s.append(tostr(coeficients[5]));\n" +
            "    s.append(\",\");\n" +
            "    s.append(tostr(coeficients[6]));\n" +
            "    s.append(\");\\n\");\n" +
            "    s.append(\"void main() {\\n\");\n" +
            "    s.append(\"  pos=uMVMatrix * aPosition;\\n\");\n" +
            "    s.append(\"  r2=clamp(dot(pos.xy,pos.xy)/(pos.z*pos.z),0.0,_MaxRadSq);\\n\");\n" +
            "    s.append(\"  ret = 0.0;\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion2.y);\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion2.x);\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion.w);\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion.z);\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion.y);\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion.x);\\n\");\n" +
            "    s.append(\"  pos.xy*=1.0+ret;\\n\");\n" +
            "    s.append(\"  gl_Position=uPMatrix*pos;\\n\");\n" +
            "    s.append(\"  vTexCoord = aTexCoord;\\n\");\n" +
            "    s.append(\"  vVertexColor = aVertexColor;\\n\");\n" +
            "#ifdef WIREFRAME\n" +
            "    s.append(\"gl_PointSize=15.0;\");\n" +
            "#endif\n" +
            "    s.append(\"}\");\n" +
            "    return s;\n" +
            "}\n" +
            "static const std::string fs_text(){\n" +
            "    std::string s=\"\";\n" +
            "    s.append(\"precision mediump float;\\n\");\n" +
            "    s.append(\"varying vec2 vTexCoord;\\n\");\n" +
            "    s.append(\"varying vec4 vVertexColor;\\n\");\n" +
            "    s.append(\"uniform sampler2D sTexture;\\n\");\n" +
            "    s.append(\"uniform vec3 uColorVec3;\\n\");\n" +
            "    s.append(\"void main() {\\n\");\n" +
            "    s.append(\"float tex = texture2D( sTexture, vTexCoord ).a;\\n\");\n" +
            "    s.append(\"gl_FragColor=vec4(vVertexColor.r*tex,vVertexColor.g*tex,vVertexColor.b*tex,tex);\");\n" +
            "#ifdef WIREFRAME\n" +
            "    s.append(\"gl_FragColor.rgb=vec3(1.0,1.0,1.0);\\n\");\n" +
            "#endif\n" +
            "    s.append(\"}\\n\");\n" +
            "    return s;\n" +
            "}\n" +
            "//TEXTURE###TEXTURE###TEXTURE###TEXTURE###TEXTURE###TEXTURE###TEXTURE###TEXTURE###TEXTURE###TEXTURE###TEXTURE###TEXTURE###\n" +
            "static const std::string vs_texture(){\n" +
            "    std::string s;\n" +
            "    s.append(\"uniform mat4 uMVPMatrix;\\n\");\n" +
            "    s.append(\"attribute vec4 aPosition;\\n\");\n" +
            "    s.append(\"attribute vec2 aTexCoord;\\n\");\n" +
            "    s.append(\"varying vec2 vTexCoord;\\n\");\n" +
            "    s.append(\"void main() {\\n\");\n" +
            "#ifdef WIREFRAME\n" +
            "    s.append(\"gl_PointSize=15.0;\");\n" +
            "#endif\n" +
            "    s.append(\"gl_Position = uMVPMatrix * aPosition;\\n\");\n" +
            "    s.append(\"vTexCoord = aTexCoord;\\n\");\n" +
            "    s.append(\"}\\n\");\n" +
            "    return s;\n" +
            "}\n" +
            "static const std::string vs_texture_vddc(const float coeficients[]){\n" +
            "    std::string s=\"\";\n" +
            "    s.append(\"uniform mat4 uMVMatrix;\\n\");\n" +
            "    s.append(\"uniform mat4 uPMatrix;\\n\");\n" +
            "    s.append(\"attribute vec4 aPosition;\\n\");\n" +
            "    s.append(\"attribute vec2 aTexCoord;\\n\");\n" +
            "    s.append(\"varying vec2 vTexCoord;\\n\");\n" +
            "    s.append(\"float r2;\\n\");\n" +
            "    s.append(\"vec4 pos;\\n\");\n" +
            "    s.append(\"float ret;\\n\");\n" +
            "    s.append(\"const float _MaxRadSq=\");\n" +
            "    s.append(tostr(coeficients[0]));\n" +
            "    s.append(\";\\n\");\n" +
            "    //There is no vec6 data type. Therefore, we use 1 vec4 and 1 vec2. Vec4 holds k1,k2,k3,k4 and vec6 holds k5,k6\n" +
            "    s.append(\"const vec4 _Undistortion=vec4(\");\n" +
            "    s.append(tostr(coeficients[1]));\n" +
            "    s.append(\",\");\n" +
            "    s.append(tostr(coeficients[2]));\n" +
            "    s.append(\",\");\n" +
            "    s.append(tostr(coeficients[3]));\n" +
            "    s.append(\",\");\n" +
            "    s.append(tostr(coeficients[4]));\n" +
            "    s.append(\");\\n\");\n" +
            "    s.append(\"const vec2 _Undistortion2=vec2(\");\n" +
            "    s.append(tostr(coeficients[5]));\n" +
            "    s.append(\",\");\n" +
            "    s.append(tostr(coeficients[6]));\n" +
            "    s.append(\");\\n\");\n" +
            "    s.append(\"void main() {\\n\");\n" +
            "    s.append(\"  pos=uMVMatrix * aPosition;\\n\");\n" +
            "    s.append(\"  r2=clamp(dot(pos.xy,pos.xy)/(pos.z*pos.z),0.0,_MaxRadSq);\\n\");\n" +
            "    s.append(\"  ret = 0.0;\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion2.y);\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion2.x);\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion.w);\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion.z);\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion.y);\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion.x);\\n\");\n" +
            "    s.append(\"  pos.xy*=1.0+ret;\\n\");\n" +
            "    s.append(\"  gl_Position=uPMatrix*pos;\\n\");\n" +
            "    s.append(\"  vTexCoord = aTexCoord;\\n\");\n" +
            "#ifdef WIREFRAME\n" +
            "    s.append(\"gl_PointSize=15.0;\");\n" +
            "#endif\n" +
            "    s.append(\"}\\n\");\n" +
            "    return s;\n" +
            "}\n" +
            "static const std::string fs_texture(){\n" +
            "    std::string s;\n" +
            "    s.append(\"precision mediump float;\\n\");\n" +
            "    s.append(\"varying vec2 vTexCoord;\\n\");\n" +
            "    s.append(\"uniform sampler2D sTexture;\\n\");\n" +
            "    s.append(\"void main() {\\n\");\n" +
            "    s.append(\"gl_FragColor = texture2D( sTexture, vTexCoord );\\n\");\n" +
            "#ifdef WIREFRAME\n" +
            "    s.append(\"gl_FragColor.rgb=vec3(1.0,1.0,1.0);\\n\");\n" +
            "#endif\n" +
            "    s.append(\"}\\n\");\n" +
            "    return s;\n" +
            "}\n" +
            "//TEXTUREEXTERNAL###TEXTUREEXTERNAL###TEXTUREEXTERNAL###TEXTUREEXTERNAL###TEXTUREEXTERNAL###TEXTUREEXTERNAL###TEXTUREEXTERNAL###\n" +
            "static const std::string vs_textureExt(){\n" +
            "    std::string s;\n" +
            "    s.append(\"uniform mat4 uMVPMatrix;\\n\");\n" +
            "    s.append(\"attribute vec4 aPosition;\\n\");\n" +
            "    s.append(\"attribute vec2 aTexCoord;\\n\");\n" +
            "    s.append(\"varying vec2 vTexCoord;\\n\");\n" +
            "    s.append(\"void main() {\\n\");\n" +
            "#ifdef WIREFRAME\n" +
            "    s.append(\"gl_PointSize=15.0;\");\n" +
            "#endif\n" +
            "    s.append(\"gl_Position = uMVPMatrix * aPosition;\\n\");\n" +
            "    s.append(\"vTexCoord = aTexCoord;\\n\");\n" +
            "    s.append(\"}\\n\");\n" +
            "    return s;\n" +
            "}\n" +
            "static const std::string vs_textureExt_vddc(const float coeficients[]){\n" +
            "    std::string s;\n" +
            "    s.append(\"uniform mat4 uMVMatrix;\\n\");\n" +
            "    s.append(\"uniform mat4 uPMatrix;\\n\");\n" +
            "    s.append(\"attribute vec4 aPosition;\\n\");\n" +
            "    s.append(\"attribute vec2 aTexCoord;\\n\");\n" +
            "    s.append(\"varying vec2 vTexCoord;\\n\");\n" +
            "    s.append(\"float r2;\\n\");\n" +
            "    s.append(\"vec4 pos;\\n\");\n" +
            "    s.append(\"float ret;\\n\");\n" +
            "    s.append(\"const float _MaxRadSq=\");\n" +
            "    s.append(tostr(coeficients[0]));\n" +
            "    s.append(\";\\n\");\n" +
            "    //There is no vec6 data type. Therefore, we use 1 vec4 and 1 vec2. Vec4 holds k1,k2,k3,k4 and vec6 holds k5,k6\n" +
            "    s.append(\"const vec4 _Undistortion=vec4(\");\n" +
            "    s.append(tostr(coeficients[1]));\n" +
            "    s.append(\",\");\n" +
            "    s.append(tostr(coeficients[2]));\n" +
            "    s.append(\",\");\n" +
            "    s.append(tostr(coeficients[3]));\n" +
            "    s.append(\",\");\n" +
            "    s.append(tostr(coeficients[4]));\n" +
            "    s.append(\");\\n\");\n" +
            "    s.append(\"const vec2 _Undistortion2=vec2(\");\n" +
            "    s.append(tostr(coeficients[5]));\n" +
            "    s.append(\",\");\n" +
            "    s.append(tostr(coeficients[6]));\n" +
            "    s.append(\");\\n\");\n" +
            "    s.append(\"void main() {\\n\");\n" +
            "    s.append(\"  pos=uMVMatrix * aPosition;\\n\");\n" +
            "    s.append(\"  r2=clamp(dot(pos.xy,pos.xy)/(pos.z*pos.z),0.0,_MaxRadSq);\\n\");\n" +
            "    //s.append(\"  r2=dot(pos.xy,pos.xy)/(pos.z*pos.z);\\n\");\n" +
            "    s.append(\"  ret = 0.0;\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion2.y);\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion2.x);\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion.w);\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion.z);\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion.y);\\n\");\n" +
            "    s.append(\"  ret = r2 * (ret + _Undistortion.x);\\n\");\n" +
            "    s.append(\"  pos.xy*=1.0+ret;\\n\");\n" +
            "    s.append(\"  gl_Position=uPMatrix*pos;\\n\");\n" +
            "    s.append(\"  vTexCoord = aTexCoord;\\n\");\n" +
            "#ifdef WIREFRAME\n" +
            "    s.append(\"gl_PointSize=15.0;\");\n" +
            "#endif\n" +
            "    s.append(\"}\\n\");\n" +
            "    return s;\n" +
            "}\n" +
            "static const std::string fs_textureExt(){\n" +
            "    std::string s;\n" +
            "    s.append(\"#extension GL_OES_EGL_image_external : require\\n\");\n" +
            "    s.append(\"precision mediump float;\\n\");\n" +
            "    s.append(\"varying vec2 vTexCoord;\\n\");\n" +
            "    s.append(\"uniform samplerExternalOES sTextureExt;\\n\");\n" +
            "    s.append(\"void main() {\\n\");\n" +
            "    s.append(\"gl_FragColor = texture2D( sTextureExt, vTexCoord );\\n\");\n" +
            "    //s.append(\"gl_FragColor.rgb = vec3(1.0,1.0,1.0);\\n\");\n" +
            "#ifdef WIREFRAME\n" +
            "    s.append(\"gl_FragColor.rgb=vec3(1.0,1.0,1.0);\\n\");\n" +
            "#endif\n" +
            "    s.append(\"}\\n\");\n" +
            "    return s;\n" +
            "}";


}
