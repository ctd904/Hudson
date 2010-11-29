
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.MalformedURLException;
import java.net.URL;


public class HTMLParser {

	static HTMLTree html=new HTMLTree(""); 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//parse(html,"<html><!--Test--><body><script lanuage=javascript>xxx</script><table><tr><td>12</td><td>34</td><td>12</td><td>999<img src=\"test.jpg\">1234<br><h3>Test</h3></td></tr><tr><td>56</td><td>78</td></tr></table>Hallo !!!</body></html>");
		//try {
			//parsefile(html,"/home/hudson/Dokumente/console_test.html");
			parseFromURL(html,"http://localhost:8080/job/test/2/console/");
	/*	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	public static void parseFromURL(HTMLTree parent,String url){
		String tmp=null,input=null;
		InputStream is=null;
		URL fURL=null;
		try {
			fURL=new URL(url);
			is=fURL.openStream();
		    BufferedReader br=new BufferedReader(new InputStreamReader(is));
		    while((input = br.readLine()) != null) 
			{tmp += input;}
		    is.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parse(html,tmp);
	}
	public static void parsefile(HTMLTree parent,String file) throws  IOException
	{
		File f = new File(file);
		System.out.println((int)f.length());
		String buf = null,input=null;
		BufferedReader fr = new BufferedReader(new FileReader(f));
		
		while((input = fr.readLine()) != null) 
		{buf += input;}
		
		parse(parent,buf);
	}
	private static String dropScript(String arg){return _Drop(arg,"<script","</script>");}
	private static String dropComment(String arg){return _Drop(arg,"<!--","-->");	}
	private static String dropHead(String arg){return _Drop(arg,"<head>","</head>");}
	
	private static String _Drop(String arg,String beginMark,String endMark){
		int comBegin=0,comEnd=0;
		String tmp=arg;
		while(tmp.toLowerCase().indexOf(beginMark,0)>=0){
			comBegin=tmp.toLowerCase().indexOf(beginMark,0);
			if(comBegin>=0){
				if(endMark!=""&&endMark!=null){
					comEnd=tmp.toLowerCase().indexOf(endMark,comBegin);
				}else{
					comEnd=tmp.toLowerCase().indexOf(">",comBegin);
				}
				if(comEnd>0){
					tmp=tmp.substring(0,comBegin)+tmp.substring(comEnd+endMark.length(),tmp.length());
				}
			}
		}
		return tmp;
	}
	
	public static void parse(HTMLTree parent,String arg){
		String tmp="";
		Pattern pat=Pattern.compile("[a-zA-Z]");
		Matcher ma=null;
		int btagBegin=0,btagEnd=0,etagBegin=0,etagEnd=0;
		if((arg.length()<=Integer.MAX_VALUE)){
			if(!arg.isEmpty()){
				//Sripte entfernen
				arg=dropHead(arg);
				//Sripte entfernen
				arg=dropScript(arg);
				//HTML-Kommentare entfernen
				arg=dropComment(arg);
				arg=arg.replace("<br>"," ");		//erstzt Zeilenumbrüche durch einfache Leerzeichen 
				//Begintag
				btagBegin=arg.indexOf("<", 0);
				if(btagBegin>=0){
					ma=pat.matcher(arg.substring(btagBegin+1,btagBegin+2));
					if(btagBegin>0&&ma.matches()){
						//Text vor Tag-Begin
						parent.getChild().add(new HTMLTree(arg.substring(0,btagBegin-1)));
					}
					btagEnd=arg.indexOf(">",btagBegin)+1;
					tmp=arg.substring(btagBegin,btagEnd);
					System.out.println("Tag:"+tmp);
					//Endtag
					etagBegin=arg.indexOf("</"+tmp.substring(1,tmp.length()-1)+">",btagEnd);
					if(etagBegin>0){
						etagEnd=etagBegin+tmp.length()+2;
						HTMLTree tmpTree=new HTMLTree(tmp);
						parent.getChild().add(tmpTree);
						if(etagEnd-1==arg.length()){
							parse(tmpTree,arg.substring(btagEnd,etagBegin));
						}else{
							parse(tmpTree,arg.substring(btagEnd,etagBegin));		//parse zwischen Tag-Begin und -Ende und füge darunter ein
							parse(parent,arg.substring(etagEnd-1,arg.length()));	//parse den Rest nach Tag-Ende und füge auf gleicher Ebene ein
						}
					}else{
						//Kein Ende-Tag vorhanden
						parse(parent,arg.substring(btagEnd,arg.length()));
					}
				}else{
					//Kein Tag vorhanden
					System.out.println(arg);
					parent.getChild().add(new HTMLTree(arg)); //füge Text ein
				}
			}
		}
	}
}
